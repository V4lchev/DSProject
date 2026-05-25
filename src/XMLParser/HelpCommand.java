package XMLParser;

/**
 * Команда за извеждане на помощно меню.
 */
public class HelpCommand extends Command {

    public HelpCommand(ProgramData data) {
        super(data);
    }

    @Override
    public boolean execute(String command) {

        System.out.println("========== HELP MENU ==========");

        System.out.println();

        System.out.println("open <file>");
        System.out.println("Отваря XML файл.");

        System.out.println();

        System.out.println("close");
        System.out.println("Затваря текущия файл.");

        System.out.println();

        System.out.println("save");
        System.out.println("Записва промените в текущия файл.");

        System.out.println();

        System.out.println("save as <file>");
        System.out.println("Записва файла под ново име.");

        System.out.println();

        System.out.println("print");
        System.out.println("Извежда цялото XML съдържание.");

        System.out.println();

        System.out.println("select <id> <key>");
        System.out.println("Извежда стойност по id и key.");

        System.out.println();

        System.out.println("set <id> <key> <value>");
        System.out.println("Променя стойност в XML елемент.");

        System.out.println();

        System.out.println("children <id>");
        System.out.println("Показва всички вложени елементи.");

        System.out.println();

        System.out.println("child <id> <n>");
        System.out.println("Показва конкретен child елемент.");

        System.out.println();

        System.out.println("text <id>");
        System.out.println("Показва текста и данните на елемента.");

        System.out.println();

        System.out.println("delete <id> <key>");
        System.out.println("Изтрива стойност или елемент.");

        System.out.println();

        System.out.println("help");
        System.out.println("Показва списък с всички команди.");

        System.out.println();

        System.out.println("exit");
        System.out.println("Изход от програмата.");

        System.out.println();

        System.out.println("================================");

        return false;
    }
}