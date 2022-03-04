/**
 * Класс для установки наименования дисциплины и колв-ва её часов
 */

public class Discipline {
    private String name;                //Поле не может быть null, Строка не может быть пустой
    private Integer practiceHours;      //Поле не может быть null

    /**
     * Конструктор класса, хранящий в себе наименования дисциплины и колв-ва её часов
     * @param name Поле не может быть null, Строка не может быть пустой
     * @param practiceHours Поле не может быть null
     * @throws IncorrectValueException если пользователь вводит некоректное значение
     */

    public Discipline(String name, Integer practiceHours) throws IncorrectValueException
    {
        setName(name);
        setPracticeHours(practiceHours);
    }

    /**
     * Возвращает наименование дисциплины
     * @return наименование дисциплины
     */

    public String getName()
    {
        return name;
    }

    /**
     * Устанавливает наименование дисциплины
     * @param name объект класса Discipline (не может быть null)
     * @throws IncorrectValueException если равно null
     */

    public void setName(String name) throws IncorrectValueException
    {
        if (!name.isEmpty())
        {
            this.name = name;
        }
        else
            {
            throw new IncorrectValueException("Поле имени не может быть null или пустым");
        }
    }

    /**
     * Возвращает количество часов
     * @return количество часов
     */

    public Integer getPracticeHours()
    {
        return practiceHours;
    }

    /**
     * Устанавливает наименование дисциплины
     * @param practiceHours объект класса Discipline (не может быть null)
     * @throws IncorrectValueException если равно null
     */

    public void setPracticeHours(Integer practiceHours) throws IncorrectValueException
    {
        if (practiceHours != 0)
        {
            this.practiceHours = practiceHours;
        }
        else
        {
            throw new IncorrectValueException("Поле имени не может быть null или пустым");
        }
    }
    @Override
    public String toString()
    {
        return name + "," + practiceHours ;
    }
}