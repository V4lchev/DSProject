package XMLParser;

/**
 * Клас, който пази текущото състояние на програмата.
 */
public class ProgramData {

    public boolean isFileOpen;
    public String openedFileName;
    public String fileText;

    public FileManager fileManager;
    public XmlFile currentFile;
    public XmlReader reader;
    public XmlWriter writer;

    public ProgramData() {

        isFileOpen = false;
        openedFileName = "";
        fileText = "";

        fileManager = new FileManager();
        currentFile = null;
        reader = new XmlReader();
        writer = new XmlWriter();
    }
}