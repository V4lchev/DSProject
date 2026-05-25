package XMLParser;

/**
 * Команда за извеждане на текста на XML елемент.
 */
public class TextCommand extends Command {

    public TextCommand(ProgramData data) {
        super(data);
    }

    @Override
    public boolean execute(String command) {

        if (!data.isFileOpen) {

            System.out.println("Няма отворен файл.");

            return false;
        }

        String[] parts = command.split(" ");

        if (parts.length < 2) {

            System.out.println("Използване: text <id>");

            return false;
        }

        String searchedId = parts[1];

        XmlElement element = findElementById(data.currentFile.getRootElement(), searchedId);

        if (element == null) {

            System.out.println("Няма елемент с такова id.");

            return false;
        }

        System.out.println("========== TEXT ==========");

        for (int i = 0; i < element.getData().size(); i++) {

            XmlData currentData = element.getData().get(i);

            System.out.println(currentData.getName() + " : " + currentData.getValue());
        }

        for (int i = 0; i < element.getElements().size(); i++) {

            XmlElement child = element.getElements().get(i);

            System.out.println(child.getTagName() + " : " + child.getText());
        }

        System.out.println("==========================");

        return false;
    }

    private XmlElement findElementById(XmlElement element, String id) {

        if (element == null) {
            return null;
        }

        for (int i = 0; i < element.getData().size(); i++) {

            XmlData currentData = element.getData().get(i);

            if (currentData.getName().equals("id") && currentData.getValue().equals(id)) {

                return element;
            }
        }

        for (int i = 0; i < element.getElements().size(); i++) {

            XmlElement found = findElementById(element.getElements().get(i), id);

            if (found != null) {

                return found;
            }
        }

        return null;
    }
}