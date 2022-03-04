import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;
import java.time.ZonedDateTime;
import java.nio.charset.StandardCharsets;

/**
 * Класс для работы с коллекцией LinkedList
 */

public class Collection
{
    LinkedList<LabWork> collection = new LinkedList<>();
    ZonedDateTime collCreation = ZonedDateTime.now();

    /**
     * Конструктор класса для работы с коллекцией LinkedList ", который инициализирует коллекцию
     * @param path путь к CSV файлу коллекции
     */

    public Collection(String path) throws IncorrectValueException{
        load(path);
        Sortir();
    }

    /**
     * Метод для стандартной сортировки коллекции
     */
    public void Sortir()
    {
        collection.sort((o1, o2) -> o2.getDiscipline().getPracticeHours().compareTo(o1.getDiscipline().getPracticeHours()));
    }

    /**
     * Загрузка коллекции из файла и добавление загруженных элементов в коллекцию
     * @param path путь к CSV файлу коллекции
     */

    public void load(String path) throws IncorrectValueException
    {
        try
        {
            FileReader in = new FileReader(path);
            CSVParser records = CSVFormat.RFC4180.withHeader
                    ("name",
                     "cordX",
                     "cordY",
                     "minimalPoint",
                     "maximumPoint",
                     "personalQualitiesMaximum",
                     "difficulty",
                     "disciplineName",
                     "disciplinePracticeHours").parse(in);
            int i = 0;
            for (CSVRecord record : records)
            {
                if (i == 0)
                {
                    i++;
                    continue;
                }
                try
                {
                    String name = record.get("name");
                    Integer cordX = Integer.parseInt(record.get("cordX"));
                    Double cordY = Double.parseDouble(record.get("cordY"));
                    Double minimalPoint = Double.parseDouble(record.get("minimalPoint"));
                    Integer maximumPoint = Integer.parseInt(record.get("maximumPoint"));
                    float personalQualitiesMaximum = Float.parseFloat(record.get("personalQualitiesMaximum"));
                    Difficulty difficulty = Difficulty.valueOf(record.get("difficulty"));
                    String disciplineName = record.get("disciplineName");
                    Integer disciplinePracticeHours = Integer.parseInt(record.get("disciplinePracticeHours"));

                    LabWork newCollectionElement = new LabWork(name, new Coordinates(cordX, cordY), minimalPoint, maximumPoint,
                            personalQualitiesMaximum, difficulty, new Discipline(disciplineName, disciplinePracticeHours));
                    collection.add(newCollectionElement);

                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Неверный синтаксис CSV!");
                }
            }
            if (collection.size() > 0)
            {
                System.out.println("Коллекция успешно подгружена из файла!");
            }
            else
            {
                System.out.println("Файл пуст!");
            }
            in.close();
        } catch (IOException e)
        {
            System.out.println("Неправильно указан путь к файлу!");
        }
    }

    /**
     * Вывод информации о коллекции в консоль
     */

    public void info()
    {
        System.out.println
                ("Тип коллекции: " + collection.getClass().getSimpleName()
                 + ", дата инициализации: " + collCreation
                 + ", количество элементов " + collection.size());
    }

    /**
     * Вывод всех элементов коллекции, если коллекция не пуста
     */

    public void show()
    {
        if(!collection.isEmpty())
        {
            for(LabWork o: collection)
            {
                System.out.println(o);
            }
            System.out.println();       //отступ для красивого ввода :)
        }
        else
            {
            System.err.println("Невозможно вывести элементы, так как коллекция пуста!");
            }
    }

    /**
     * Контекстное меню для добавления нового элемента в коллекцию (с заполнением полей) и вывода информации о новом элементе
     */

    public void add()
    {
        System.out.println("Для добавления нового элемента в коллекцию заполните значения полей:");
        try
        {
            LabWork lab = new LabWork(
                    ConsoleEvent.getName(),
                    ConsoleEvent.getCoordinates(),
                    ConsoleEvent.getMinimalPoint(),
                    ConsoleEvent.getMaximumPoint(),
                    ConsoleEvent.getPersonalQualitiesMaximum(),
                    ConsoleEvent.getDifficulty(),
                    ConsoleEvent.getDiscipline());
            collection.add(lab);
            System.out.println("Коллекция добавлена:");
            System.out.println(lab);
            System.out.println();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Вызов контекстного меню для обновление поля, которое задается пользователем
     * @param id ID организации (должен быть больше 0)
     */

    public void update(int id)
    {
        if(!collection.isEmpty())
        {
            if (id > 0)
            {
                for (LabWork o : collection)
                {
                    if (o.getId() == id)
                    {
                        switch (ConsoleEvent.update()) {
                            case "name":
                                try {
                                    o.setName(ConsoleEvent.getName());
                                } catch (IncorrectValueException e) {
                                    System.err.println(e.getMessage());
                                }
                                System.out.println("Поле имени обновлено!");
                                break;
                            case "coordinates":
                                try {
                                    o.setCoordinates(ConsoleEvent.getCoordinates());
                                } catch (IncorrectValueException e) {
                                    System.err.println(e.getMessage());
                                }
                                System.out.println("Поле координат обновлено!");
                                break;

                            case "minimalpoint":
                                try {
                                    o.setMinimalPoint(ConsoleEvent.getMinimalPoint());
                                } catch (IncorrectValueException e) {
                                    System.err.println(e.getMessage());
                                }
                                System.out.println("Поле минимальных баллов обновлено!");
                                break;
                            case "maximumpoint":
                                try {
                                    o.setMaximumPoint(ConsoleEvent.getMaximumPoint());
                                } catch (IncorrectValueException e) {
                                    System.err.println(e.getMessage());
                                }
                                System.out.println("Поле максимальных баллов обновлено!");
                                break;
                            case "personalqualitiesmaximum":
                                try {
                                    o.setPersonalQualitiesMaximum(ConsoleEvent.getPersonalQualitiesMaximum());
                                } catch (IncorrectValueException e) {
                                    System.err.println(e.getMessage());
                                }
                                System.out.println("Поле максимальных личных качеств обновлено!");
                                break;
                            case "difficulty":
                                o.setDifficulty(ConsoleEvent.getDifficulty());
                                System.out.println("Поле уровня сложности обновлено!");
                                break;
                            case "discipline":
                                o.setDiscipline(ConsoleEvent.getDiscipline());
                                System.out.println("Поле дисциплины обновлено!");
                                break;
                            case "exit": System.out.println("Обновление поля было отменено пользователем!");
                                break;
                            default: System.err.println("Произошла ошибка при обновлении поля!");
                                break;
                        }
                        return;
                    }
                }
            }
            else
            {
                System.err.println("ID должен быть > 0!");
                return;
            }
            System.err.println("Элемент с указанным ID (" + id + ") не найден! Попробуйте ввести команду еще раз!");
        }
        else
        {
            System.err.println("Невозможно обновить элементы, так как коллекция пуста!");
        }
    }

    /**
     * Удаление элемента из коллекции с указанным ID организации
     * @param id ID организации (должен быть больше 0)
     */

    public void remove_by_id(int id)
    {
        if(!collection.isEmpty())
        {
            if (id > 0)
            {
                for (LabWork o : collection)
                {
                    if (o.getId() == id)
                    {
                        collection.remove(o);
                        System.out.println("Элемент коллекции успешно удален!");
                        return;
                    }
                }
            }
            else
                {
                System.err.println("ID должен быть > 0!");
                return;
            }
            System.err.println("Элемент с указанным ID (" + id + ") не найден! Попробуйте ввести команду еще раз!");
        }
        else
            {
            System.err.println("Невозможно вывести элементы, так как коллекция пуста!");
            }
    }

    /**
     * Очистка коллекции и вывод сообщения о том, что коллекция очищена
     */

    public void clear()
    {
        collection.clear();
        System.out.println("Коллекция очищена!");
    }

    /**
     * Сохранение коллекции по указанному пользователю пути
     * @param path путь к CSV, в котором будет сохраанена коллекция
     * @throws IOException если произошла ошибка при попытке сохранить файл
     */

    public void save(String path) throws IOException
    {
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw); CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader
                ("name",
                        "cordX",
                        "cordY",
                        "minimalPoint",
                        "maximumPoint",
                        "personalQualitiesMaximum",
                        "difficulty",
                        "disciplineName",
                        "practiceHours")))
        {
            for(LabWork o: collection)
            {
                csvPrinter.printRecord(o.getName(),
                        o.getCoordinates().getX(),
                        o.getCoordinates().getY(),
                        o.getMinimalPoint(),
                        o.getMaximumPoint(),
                        o.getPersonalQualitiesMaximum(),
                        o.getDifficulty(),
                        o.getDiscipline().getName(),
                        o.getDiscipline().getPracticeHours());
            }
            csvPrinter.flush();
            System.out.println("CSV-коллекция сохранена: " + path);
        }
    }

    /**
     * Команда завершения программ
     */

    public void exit()
    {
        System.out.println("Вызвана команда выхода из программы без сохранения!");
        System.exit(0);

    }

    /**
     * Команда удаления первого элемента из коллекции
     */

    public void remove_first()
    {
        if(!collection.isEmpty())
        {
            collection.remove(0);
            System.out.println("Первый элемент коллекции успешно удалён!");
        }
        else
        {
            System.err.println("Невозможно удалить первый элемент, так как коллекция пуста!");
        }
    }

    /**
     * Удаление элемента коллекции, превышающее указанное пользователем значение
     */

    public void remove_greater()
    {
        if(!collection.isEmpty())
        {
            float comparable = ConsoleEvent.getPersonalQualitiesMaximum();
            int sizeBefore = collection.size();
            collection.removeIf(p -> p.comparePersonalQualitiesMaximum((long) comparable) == 1);
            int calc = sizeBefore - collection.size();
            System.out.println("Из коллекции удалено " + calc + " элементов, превышающиих максимальное значение " + comparable);
        }
        else
        {
            System.err.println("Невозможно удалить элементы, так как коллекция пуста!");
        }
    }

    /**
     * Вывод последних 14 команд введенных пользователем
     *
     * Если объект в коллекции равен null, то он не выводится
     */

    public void history()
    {
        int normalI;
        ArrayList<String> time = new ArrayList<>(Arrays.asList(Console.GetHistory()).subList(0, 14));
        int timVal = time.size();

        for(int i = 0; i < timVal; i++)
        {
            if(time.get(i) != null)
            {
                normalI = i + 1;
                System.out.println("Команда №" + normalI + ": " + time.get(i));
            }
        }

    }

    /**
     * Команда вывода кол-ва максимальных качеств, которые совпадают с заданным
     */

    public void filter_by_personal_qualities_maximum()
    {
        if(!collection.isEmpty())
        {
            float comparable = ConsoleEvent.getPersonalQualitiesMaximum();
            for (LabWork o : collection)
            {
                if(o.getPersonalQualitiesMaximum() == comparable)
                {
                    System.out.println("ID элемента равного заданному количеству максимальных качеств: " + o.getId());
                }
            }
        }
        else
        {
            System.err.println("Невозможно удалить элементы, так как коллекция пуста!");
        }
    }

    /**
     * Команда вывода ID объекта, чьё имя совпало с заданным
     */

    public void filter_contains_name()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите имя элемента, который хотите получить!");
        String checkingForContent = input.nextLine();
        try
        {
            for (LabWork o : collection)
            {
                if (o.getName().equals(checkingForContent))
                {
                    System.out.println("ID элемента: " + o.getId());
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Введены неправильные данные!");
        }
    }

    /**
     * Команда вывода объектов типа "discipline" в порядке убывания
     */

    public void print_field_descending_discipline()
    {
        if(!collection.isEmpty())
        {
            int i = 0;
            for (LabWork o : collection)
            {
                i++;
                System.out.println("№" + i + ": " + o.getDiscipline().getName() +" "+ o.getDiscipline().getPracticeHours());
            }
        }
        else
        {
            System.err.println("Невозможно вывести \"discipline\", так как коллекция пуста!");
        }
    }
}
