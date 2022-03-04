
/**
 * Класс для запуска программы с методом main
 *
 * @author Dyuldin Gordey P3118
 */

public class Main {

    /**
     * Стандартный метод для запуска программы
     */

    public static void main(String[] args) throws IncorrectValueException {
        String path = "C:\\Users\\Boss\\IdeaProjects\\OldProj\\Lab_5\\src\\data.csv";
        Console console = new Console(path);
        console.run();
    }
}