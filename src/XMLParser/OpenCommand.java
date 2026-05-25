package XMLParser;

/**
 * Команда за отваряне на XML файл.
 */
public class OpenCommand extends Command {

    public OpenCommand(ProgramData data) {
        super(data);
    }

    @Override
    public boolean execute(String command) {

        if (data.isFileOpen) {
            System.out.println("Има вече отворен файл.");
            return false;
        }

        try {

            data.openedFileName = command.substring(5);

            data.fileText = data.fileManager.readFile(data.openedFileName);

            data.currentFile = data.reader.parse(data.fileText);

            data.isFileOpen = true;

            System.out.println("Successfully opened " + data.openedFileName);

        }
        catch (Exception e) {

            System.out.println("Грешка при отваряне на файла.");
        }

        return false;
    }
}