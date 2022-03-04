import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import static java.lang.Integer.parseInt;

import java.util.Scanner;

/**
 * Класс для работы консольного приложения и инициализаации коллекции
 */

public class Console {
    private String command = "";
    private final Collection collection;
    private final String path;
    public static String[] history = new String[15];
    private int i = 0;

    /**
     * Конструктор для инициализации коллекции
     * @param path путь к CSV файлу коллекции
     */

    public Console(String path) throws IncorrectValueException{
        this.path = path;
        collection = new Collection(path);
    }

    /**
     * Метод для выхода из программы
     * Имеет возможности по выводу информации о доступных командах, информации о коллекции, информации о элементах коллекции и др.
     */

    public void execute(String command)
    {
        String[] trimmedCommand = command.trim().split(" ", 2);
        try
        {
            if (i < 14)
            {
                history[i] = trimmedCommand[0];
                i++;
            }
            else
            {
                history[14] = trimmedCommand[0];
                for (i = 0; i < 14; i++)
                {
                    history[i] = history[i + 1];
                }
            }

            switch (trimmedCommand[0])
            {
                case "":
                    break;
                case "help":
                    println("help - вывести справку по доступным командам\n" +
                            "info - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                            "show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                            "add {element} - добавить новый элемент в коллекцию\n" +
                            "update_id {element} - обновить значение элемента коллекции, id которого равен заданному\n" +
                            "remove_by_id {id} - удалить элемент из коллекции по его id\n" +
                            "clear - очистить коллекцию\n" +
                            "save - сохранить коллекцию в файл\n" +
                            "execute_script file_name - считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                            "exit - завершить программу (без сохранения в файл)\n" +
                            "remove_first : удалить первый элемент из коллекции\n" +
                            "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                            "history : вывести последние 14 команд (без их аргументов)\n" +
                            "filter_by_personal_qualities_maximum personalQualitiesMaximum : вывести элементы, значение поля personalQualitiesMaximum которых равно заданному\n" +
                            "filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку\n" +
                            "print_field_descending_discipline : вывести значения поля discipline всех элементов в порядке убывания\n");
                    break;
                case "info":
                    collection.info();
                    break;
                case "show":
                    collection.show();
                    break;
                case "add":
                    collection.add();
                    break;
                case "update_id":
                    try
                    {
                        collection.update(parseInt(trimmedCommand[1]));
                    }
                    catch (NumberFormatException e)
                    {
                        printer("Ошибка при вводе аргумента. ID должен быть натуральным числом");
                    }
                    break;
                case "remove_by_id":
                    try
                    {
                        collection.remove_by_id(parseInt(trimmedCommand[1]));
                    }
                    catch (NumberFormatException e)
                    {
                        printer("Ошибка при вводе аргумента. ID должен быть натуральным числом");
                    }
                    break;
                case "clear":
                    collection.clear();
                    break;
                case "save":
                    collection.save(path);
                    break;
                case "execute_script":
                    execute_script();
                    break;
                case "exit":
                    collection.exit();
                    println("Вызвана команда выхода из программы без сохранения");
                    break;
                case "remove_first":
                    collection.remove_first();
                    break;
                case "remove_greater":
                    collection.remove_greater();
                    break;
                case "history":
                    collection.history();
                    break;
                case "filter_by_personal_qualities_maximum":
                    collection.filter_by_personal_qualities_maximum();
                    break;
                case "filter_contains_name":
                    collection.filter_contains_name();
                    break;
                case "print_field_descending_discipline":
                    collection.print_field_descending_discipline();
                    break;
                default:
                    printer("Несуществующая команда. Введите help для справки.");
                    break;
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            printer("Отсутствует аргумент команды.");
        }
        catch (IOException e)
        {
            printer("Ошибка при записи файла!");
        }
    }

    /**
     * Выполнение консольных команд из файла
     */

    private void execute_script() throws IOException   //   ././src/newFile.csv
    {
        try
        {
            Scanner input = new Scanner(System.in);
            println("Введи имя файла, который хотите запустить!");

            FileReader in1 = new FileReader(input.nextLine());
            CSVParser records = CSVFormat.RFC4180.withHeader("command").parse(in1);

            for (CSVRecord record : records)
            {
                try
                {
                    String fileCommand = record.get("command");
                    if(fileCommand.equals("command"))
                        continue;
                    else if (fileCommand.equals("execute_script"))
                        continue;
                    System.out.println("\n" + fileCommand);
                    execute(fileCommand);

                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Неверный синтаксис CSV! " + e.getMessage());
                }
            }
            in1.close();
        }
        catch (IOException e)
        {
            printer(e.getMessage());
            printer("Введены неправильные данные!");
        }
    }

    /**
     * Запуск консоли, ожидающей ввода команды
     * При вызове команды exit, останавливается
     */

    public void run() {
        println("Консоль запущена...");
        try (Scanner scanner = new Scanner(System.in)) {
            while (!command.equals("exit")) {
                command = scanner.nextLine();
                execute(command);
            }
        }
    }

    /**
     * Возвращает последние 14 команд вводимых в консоль
     * @return history
     */

    public static String[] GetHistory()
    {
        return history;
    }

    /**
     * Вывод текста в консоль
     * @param text текст, который будет выведен в консоль
     */

    public void println(String text) {
        System.out.println(text);
    }

    /**
     * Вывод ошибки в консоль
     * @param error ошибка, которая будет выведена в консоль
     */

    public void printer(String error) {
        System.err.println(error);
    }
}