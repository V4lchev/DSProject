package XMLParser;

/**
 * Команда за записване на текущия XML файл.
 */
public class SaveCommand extends Command {

    public SaveCommand(ProgramData data) {
        super(data);
    }

    @Override
    public boolean execute(String command) {

        if (!data.isFileOpen) {

            System.out.println("Няма отворен файл.");

            return false;
        }

        data.fileText = data.writer.build(data.currentFile);

        data.fileManager.writeFile(data.openedFileName, data.fileText);

        System.out.println("Successfully saved " + data.openedFileName);

        return false;
    }
}