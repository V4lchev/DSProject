package XMLParser;

/**
 * XML writer клас.
 * Преобразува XML обектите в текст.
 */
public class XmlWriter {

    public String build(XmlFile file) {

        if (file == null || file.getRootElement() == null) {
            return "";
        }

        return buildElement(file.getRootElement(), 0);
    }

    private String buildElement(XmlElement element, int level) {

        String result = "";

        String spaces = "";

        for (int i = 0; i < level; i++) {
            spaces += "    ";
        }

        result += spaces + "<" + element.getTagName();

        for (int i = 0; i < element.getData().size(); i++) {

            XmlData data = element.getData().get(i);

            result += " " + data.getName() + "=\"" + data.getValue() + "\"";
        }

        result += ">";

        if (element.getElements().size() == 0) {

            result += element.getText();

            result += "</" + element.getTagName() + ">\n";
        }
        else {

            result += "\n";

            for (int i = 0; i < element.getElements().size(); i++) {

                result += buildElement(element.getElements().get(i), level + 1);
            }

            result += spaces + "</" + element.getTagName() + ">\n";
        }

        return result;
    }
}