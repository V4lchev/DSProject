package XMLParser;

import java.util.ArrayList;
/**
 * Представя XML елемент.
 */
public class XmlElement {

    private String TagName;

    private String Text;

    private ArrayList<XmlData> Data;

    private ArrayList<XmlElement> Elements;

    public XmlElement(String TagName) {

        this.TagName = TagName;

        Text = "";

        Data = new ArrayList<>();

        Elements = new ArrayList<>();
    }

    public String getTagName() {

        return TagName;
    }

    public void setText(String Text) {

        this.Text = Text;
    }

    public String getText() {

        return Text;
    }

    public void addData(XmlData NewData) {

        Data.add(NewData);
    }

    public void addElement(XmlElement NewElement) {

        Elements.add(NewElement);
    }

    public ArrayList<XmlData> getData() {

        return Data;
    }

    public ArrayList<XmlElement> getElements() {

        return Elements;
    }
}