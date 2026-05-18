package Writer;

import Elements.XmlData;
import Elements.XmlElement;
import Files.XmlFile;
/**
 * XML writer клас.
 * Преобразува XML обектите в текст.
 */
public class XmlWriter {

    public String build(XmlFile file) {

        String result = "";

        result += "<people>\n";

        for (int i = 0; i < file.getRootElement().getElements().size(); i++) {

            XmlElement person = file.getRootElement().getElements().get(i);

            result += "<person ";

            for (int j = 0; j < person.getData().size(); j++) {

                XmlData data = person.getData().get(j);

                result += data.getName() + "=\"" + data.getValue() + "\" ";
            }

            result += ">\n";

            for (int j = 0; j < person.getElements().size(); j++) {

                XmlElement element = person.getElements().get(j);

                result += "<" + element.getTagName() + ">";

                result += element.getText();

                result += "</" + element.getTagName() + ">\n";
            }

            result += "</person>\n";
        }

        result += "</people>";

        return result;
    }
}