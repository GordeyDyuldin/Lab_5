/**
 * Вспомогательные утилиты
 */

public class Utils {

    /**
     * Преобразование строки в Difficulty
     * @param difficultyName имя организации
     * @return объект перечисления Difficulty или null
     */

    public static Difficulty StrToType(String difficultyName)
    {
        try
        {
            return Difficulty.valueOf(difficultyName.toUpperCase());
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }
}