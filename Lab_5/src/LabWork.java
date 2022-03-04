import java.time.LocalDateTime;

/**
 * Класс лабораторная работа
 */

public class LabWork {
    private int id;                            //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name;                       //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates;           //Поле не может быть null
    private LocalDateTime creationDate;        //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double minimalPoint;               //Поле может быть null, Значение поля должно быть больше 0
    private Integer maximumPoint;              //Поле может быть null, Значение поля должно быть больше 0
    private float personalQualitiesMaximum;    //Значение поля должно быть больше 0
    private Difficulty difficulty;             //Поле не может быть null
    private Discipline discipline;             //Поле не может быть null
    private static int  lastId = 0;


    /**
     * Конструктор класса лабораторной работы
     * @param name имя лабораторной работы (не может быть null и пустым)
     * @param coordinates координаты  (не может быть null, Строка не может быть пустой)
     * @param minimalPoint минимальный балл (Поле может быть null, Значение поля должно быть больше 0)
     * @param maximumPoint максимальный балл (Поле может быть null, Значение поля должно быть больше 0)
     * @param personalQualitiesMaximum максимальное количество личных достижений (Значение поля должно быть больше 0)
     * @param difficulty Уровень сложности (Поле не может быть null)
     * @param discipline Название предмета (Поле не может быть null)
     * @throws IncorrectValueException если нарушены условия инициализации имени, координат, дисциплины и др.
     */

    public LabWork(
            String name,
            Coordinates coordinates,
            Double minimalPoint,
            Integer maximumPoint,
            Float personalQualitiesMaximum,
            Difficulty difficulty,
            Discipline discipline) throws IncorrectValueException
    {
            setName(name);
            setCoordinates(coordinates);
            setMinimalPoint(minimalPoint);
            setMaximumPoint(maximumPoint);
            setPersonalQualitiesMaximum(personalQualitiesMaximum);
            setDifficulty(difficulty);
            setDiscipline(discipline);
            lastId++;
            id = lastId; // Мы смогли создать объект, значит можем присвоить ID
            creationDate = LocalDateTime.now();
    }

    /**
     * Возвращает ID работы
     * @return ID работы
     */

    public long getId()
    {
        return id;
    }

    /**
     * Возвращает имя работы
     * @return имя работы
     */

    public String getName()
    {
        return name;
    }

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
     * Возвращает координаты работы
     * @return объект класса Coordinates
     */

    public Coordinates getCoordinates()
    {
        return coordinates;
    }

    /**
     * Устанавливает координаты работы
     * @param coordinates объект класса Coordinates (не может быть null)
     * @throws IncorrectValueException если координаты null
     */

    public void setCoordinates(Coordinates coordinates) throws IncorrectValueException
    {
        if (coordinates != null)
        {
            this.coordinates = coordinates;
        }
        else
        {
            throw new IncorrectValueException("Поле координат не может быть null");
        }
    }

    /**
     * Возвращает минимальные баллы
     * @return minimalPoint
     */

    public Double getMinimalPoint()
    {
        return minimalPoint;
    }

    /**
     * Устанавливает минимальные баллы
     * @param minimalPoint  (не может быть null, Значение поля должно быть больше 0)
     * @throws IncorrectValueException если неудовлетворяет требованиям
     */

    public void setMinimalPoint(Double minimalPoint) throws IncorrectValueException
    {
        if (minimalPoint > 0)
        {
            this.minimalPoint = minimalPoint;
        }
        else
            {
            throw new IncorrectValueException("   ");
        }
    }

    /**
     * Возвращает максимальные баллы
     * @return maximumPoint
     */

    public Integer getMaximumPoint()
    {
        return maximumPoint;
    }

    /**
     * Устанавливает максимальные баллы
     * @param maximumPoint  (не может быть null, Значение поля должно быть больше 0)
     * @throws IncorrectValueException если неудовлетворяет требованиям
     */

    public void setMaximumPoint(Integer maximumPoint) throws IncorrectValueException
    {
        if (maximumPoint > 0)
        {
            this.maximumPoint = maximumPoint;
        }
        else
            {
            throw new IncorrectValueException("   ");
        }
    }

    /**
     * Возвращает максимальные личные качества
     * @return personalQualitiesMaximum
     */

    public float getPersonalQualitiesMaximum()
    {
        return personalQualitiesMaximum;
    }

    /**
     * Устанавливает максимальные баллы
     * @param personalQualitiesMaximum  (Значение поля должно быть больше 0)
     * @throws IncorrectValueException если неудовлетворяет требованиям
     */

    public void setPersonalQualitiesMaximum(Float personalQualitiesMaximum) throws IncorrectValueException
    {
        if (personalQualitiesMaximum > 0)
        {
            this.personalQualitiesMaximum = personalQualitiesMaximum;
        }
        else
            {
            throw new IncorrectValueException("Некоректное значение!");
            }
    }

    /**
     * Возвращает выбранную дисциплину
     * @return discipline
     */

    public Discipline getDiscipline()
    {
        return discipline;
    }

    /**
     * Возвращает уровень сложности работы
     * @return difficulty
     */

    public Difficulty getDifficulty()
    {
        return difficulty;
    }

    /**
     * Устанавливает максимальные баллы
     * @param difficulty  (не может быть null)
     */

    public void setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }

    /**
     * Устанавливает максимальные баллы
     * @param discipline  (не может быть null)
     */

    public void setDiscipline(Discipline discipline)
    {
        this.discipline = discipline;
    }

    /**
     * Стандартный компаратор, сравнивающий кол-во максимальных качеств персоны
     * @return результат сравнения кол-ва качеств
     */

    public long comparePersonalQualitiesMaximum(long personalQualitiesMaximum)
    {
        return Float.compare(getPersonalQualitiesMaximum(), personalQualitiesMaximum);
    }

    @Override
    public String toString()
    {
        return
                "ID: " + id + ", " +
                        "Имя: " + name + ", "
                        + "Координаты (x; y): (" + coordinates.getX() + "; " + coordinates.getY() + "), "
                        + "Время создания: " + creationDate + ", "
                        + "\nМинимальный балл: " + minimalPoint + ", "
                        + "Максимальный балл: " + maximumPoint + ", "
                        + "Максимум личных качеств: " + personalQualitiesMaximum + ", "
                        + "Сложность: " + ", "  + difficulty + ", "
                        + "Дисциплина (кол-во часов): " + discipline;
    }
}