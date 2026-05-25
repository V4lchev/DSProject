package XMLParser;

/**
 * Команда за извеждане на XML информацията.
 */
public class PrintCommand extends Command {

    public PrintCommand(ProgramData data) {
        super(data);
    }

    @Override
    public boolean execute(String command) {

        if (!data.isFileOpen) {

            System.out.println("Няма отворен файл.");

            return false;
        }

        String text = data.writer.build(data.currentFile);

        System.out.println(text);

        return false;
    }
}