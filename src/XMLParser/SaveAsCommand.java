package XMLParser;

/**
 * Команда за записване под ново име.
 */
public class SaveAsCommand extends Command {

    public SaveAsCommand(ProgramData data) {
        super(data);
    }

    @Override
    public boolean execute(String command) {

        if (!data.isFileOpen) {

            System.out.println("Няма отворен файл.");

            return false;
        }

        try {

            String newFileName = command.substring(8);

            String newText = data.writer.build(data.currentFile);

            data.fileManager.writeFile(newFileName, newText);

            System.out.println("Successfully saved " + newFileName);

        }
        catch (Exception e) {

            System.out.println("Грешка при запис.");
        }

        return false;
    }
}