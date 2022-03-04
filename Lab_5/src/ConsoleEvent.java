import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс, предзначенный для ввода и проверки корректности ввода значений
 */

public class ConsoleEvent
{
    /**
     * Временная переменная для проверки значений
     */

    private static float checkTheValues;

    /**
     * Контекстное меню для обновление поля, которое задается пользователем
     * @return корректное значение поля, которое пользователь хочет изменить
     */

    public static String update()
    {
        Scanner input = new Scanner(System.in);
        String name;
        while(true)
        {
            System.out.print("Укажите имя поля, которое вы хотите изменить: ");
            name = (input.nextLine()).toLowerCase();
            if (name.equals("name")
                    || name.equals("coordinates")
                    || name.equals("minimalpoint")
                    || name.equals("maximumpoint")
                    || name.equals("personalqualitiesmaximum")
                    || name.equals("difficulty")
                    || name.equals("discipline")
                    || name.equals("exit"))
            {
                return name;
            }
            else if (name.equals("help"))
            {
                System.out.println("Помощь по полям:\nname - изменить имя\ncoordinates - изменить координаты\nminimalPoint - изменить минимальное значение\nmaximumPoint - изменить максимальное значение\npersonalQualitiesMaximum - изменить максимальное количество умений\ndifficulty - изменить сложность\ndiscipline - изменить дисциплину\nДля выхода введите exit");
            }
            else
            {
                System.out.println("Неверное имя поля! Вызовите help для помощи!");
            }
        }
    }

    /**
     * Контекстное меню для получение наименования работы, которое задается пользователем
     * @return корректное наименования работы, заданное пользователем
     */

    public static String getName()
    {
        Scanner input = new Scanner(System.in);
        String name;
        while(true)
        {
            try
            {
                System.out.print("Введите имя: ");
                name = input.nextLine();
                if(name.isEmpty())
                {
                    throw new IncorrectValueException("Введено некорректное значение, повторите попытку!");
                }
                else
                {
                    return name;
                }
            }
            catch (IncorrectValueException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Контекстное меню для получение координат,задающихся пользователем
     * @return корректное значение объекта класса Coordinates с заданными пользователем координатами организации
     */

    public static Coordinates getCoordinates()
    {
        Scanner input = new Scanner(System.in);
        int x;
        double y;
        String timeValue;
        while(true)
        {
            try
            {
                System.out.print("Введите координату X: ");
                timeValue = input.nextLine();
                if(timeValue.isEmpty())
                {
                    throw new IncorrectValueException("Введено некорректное значение, повторите попытку!");
                }
                x = Integer.parseInt(timeValue);
                if(x < -204)
                {
                    throw new IncorrectValueException("Значение поля должно быть больше -204!");
                }
                else
                    {
                    while (true)
                    {
                        try
                        {
                            System.out.print("Введите координату Y: ");
                            timeValue = input.nextLine();
                            if(timeValue.isEmpty())
                            {
                                throw new IncorrectValueException("Введено некорректное значение, повторите попытку!");
                            }
                            y = Double.parseDouble(timeValue);
                            return new Coordinates(x, y);
                        }
                        catch (Exception e)
                        {
                            System.out.println("Введено некорректное значение, повторите попытку!");
                        }
                    }
                }
            }
            catch (IncorrectValueException e)
            {
                System.out.println(e.getMessage());
            }
            catch (InputMismatchException e)
            {
                System.out.println("Введено некорректное значение, повторите попытку!");
                input.nextLine();
            }
            catch (Exception e)
            {
                System.out.println("Введено некорректное значение, повторите попытку!");
            }
        }
    }

    /**
     * Контекстное меню для получение максимальных персональных ачивментов
     * @return корректное значение максимальных персональных ачивментов, заданное пользователем
     */

    public static float getPersonalQualitiesMaximum()
    {
        Scanner input = new Scanner(System.in);
        float personalQualitiesMaximum;
        while(true)
        {
            try
            {
                System.out.print("Введите количество личных качеств: ");
                String temporary_value;
                temporary_value = input.nextLine();
                personalQualitiesMaximum = Float.parseFloat(temporary_value);
                if(personalQualitiesMaximum <= 0)
                {
                    throw new IncorrectValueException("Введено некорректное значение, повторите попытку! Значение должно быть больше 0!");
                }
                else
                {
                    return personalQualitiesMaximum;
                }
            }
            catch (IncorrectValueException e)
            {
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e)
            {
                System.out.println(e.getMessage());
                System.out.println("Значение должно быть цифровым!");
            }
        }
    }

    /**
     * Контекстное меню для установки минимальных баллов
     * @return корректное значение минимальных баллов, заданное пользователем
     */

    public static double getMinimalPoint()
    {
        Scanner input = new Scanner(System.in);
        double minimalPoint;
        while(true)
        {
            try
            {
                System.out.print("Введите минимальное значение: ");
                String temporary_value;
                temporary_value = input.nextLine();
                checkTheValues = Float.parseFloat(temporary_value);
                minimalPoint = Double.parseDouble(temporary_value);
                if(minimalPoint <= 0) {
                    throw new IncorrectValueException("Введено некорректное значение, повторите попытку! Значение должно быть больше 0!");
                }
                else
                {
                    return minimalPoint;
                }
            }
            catch (IncorrectValueException e)
            {
                System.out.println(e.getMessage());
            }
            catch (Exception e)
            {
                System.out.println("Введено некорректное значение, повторите попытку!");
            }
        }
    }

    /**
     * Контекстное меню для установки максимальных баллов
     * @return корректное значение максимальных баллов, заданное пользователем
     */

    public static int getMaximumPoint()
    {
        Scanner input = new Scanner(System.in);
        int maximumPoint;
        while(true)
        {
            try
            {
                System.out.print("Введите максимальное значение: ");
                String temporary_value;
                temporary_value = input.nextLine();
                maximumPoint = Integer.parseInt(temporary_value);
                if(maximumPoint <= 0)
                {
                    throw new IncorrectValueException("Введено некорректное значение, повторите попытку!");
                }
                else if(maximumPoint <= checkTheValues){
                    throw new IncorrectValueException("Максимальне значение не может быть меньше либо равно минимальному, повторите попытку!");
                }
                else
                    {
                    return maximumPoint;
                }
            }
            catch (IncorrectValueException e)
            {
                System.out.println(e.getMessage());
            }
            catch (Exception e)
            {
                System.out.println("Введено некорректное значение, повторите попытку!");
            }
        }
    }

    /**
     * Контекстное меню для установки сложности работы
     * @return корректное значение сложности работы, заданное пользователем
     */

    public static Difficulty getDifficulty()
    {
        Scanner input = new Scanner(System.in);
        Difficulty type;
        String lvlName;
        System.out.println("Доступные уровень сложности: " + Stream.of(Difficulty.values()).
                map(Difficulty::name).
                collect(Collectors.joining(", ")));
        while(true)
        {
            try
            {
                System.out.print("Введите уровень сложности: ");
                lvlName = input.nextLine();
                type = Utils.StrToType(lvlName);
                if (!lvlName.isEmpty() && type == null)
                {
                    throw new IncorrectValueException("Проверьте значение уровня сложности! Если такое поле null - оставьте строку пустой!");
                }
                else
                    {
                        return type;
                    }
            }
            catch (IncorrectValueException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Контекстное меню для установки дисциплины
     * @return корректное значение дисциплины, заданное пользователем
     */

    public static Discipline getDiscipline()
    {
        Scanner input = new Scanner(System.in);
        String NameOfDiscipline;
        int practiceHours;
        while(true)
        {
            try
            {
                System.out.print("Введите наименование дисциплины: ");
                NameOfDiscipline = input.nextLine();
                if(NameOfDiscipline.isEmpty()) {
                    throw new IncorrectValueException("Введено некорректное наименование дисциплины или количество часов практики, повторите попытку!");
                }
                else
                    {
                    while(true)
                    {
                        try
                        {
                            System.out.print("Введите кол-во часов практики: ");
                            practiceHours = input.nextInt();
                            if (practiceHours <= 0)
                            {
                                throw new IncorrectValueException("Введено некорректное значение, повторите попытку!");
                            }
                            return new Discipline(NameOfDiscipline, practiceHours);
                        }
                        catch (IncorrectValueException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            catch (IncorrectValueException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

}