package XMLParser;

/**
 * Команда за затваряне на файл.
 */
public class CloseCommand extends Command {

    public CloseCommand(ProgramData data) {
        super(data);
    }

    @Override
    public boolean execute(String command) {

        if (!data.isFileOpen) {

            System.out.println("Няма отворен файл.");

            return false;
        }

        System.out.println("Successfully closed " + data.openedFileName);

        data.isFileOpen = false;
        data.openedFileName = "";
        data.fileText = "";
        data.currentFile = null;

        return false;
    }
}