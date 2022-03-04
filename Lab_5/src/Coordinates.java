/**
 * Класс, хранящий в себе координаты организации
 */

public class Coordinates {
    private Integer x;                  //Значение поля должно быть больше -204
    private Double  y;                  //Поле не может быть null

    /**
     * Конструктор класса, хранящий в себе координаты организации
     * @param x принимает значение больше -204
     * @param y принимает любое значение, кроме 'null'
     * @throws IncorrectValueException если пользователь вводит некоректное значение
     */

    public Coordinates(Integer x, Double  y) throws IncorrectValueException
    {
        setX(x);
        setY(y);
    }

    /**
     * Возвращает значение координаты Y
     * @return значение координаты y
     */

    public int getX()
    {
        return x;
    }

    /**
     * Возвращает значение координаты Y
     * @return значение координаты y
     */

    public double getY()
    {
        return y;
    }

    /**
     * Устанавливает координату x
     * @param x принимает значение больше -204
     * @throws IncorrectValueException если пользователь вводит некоректное значение
     */

    public void setX(Integer x) throws IncorrectValueException
    {
        if(x > -204)
        {
            this.x = x;
        }
        else
            {
            throw new IncorrectValueException("Максимальное значение координаты x = -204");
        }
    }

    /**
     * Устанавливает координату Y
     * @param y принимает любое значение, кроме 'null'
     */

    public void setY(Double y) throws IncorrectValueException
    {
        if (y != null)
        {
            this.y = y;
        }
        else
            {
            throw new IncorrectValueException("y не может быть равен 0");
        }
    }

    @Override
    public String toString()
    {
        return "(" + x + "; " + y + ")";
    }
}