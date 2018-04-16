package pri.ryan.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ProxyXml {
    private static Document doc;
    static{
        SAXReader reader = new SAXReader();
        try {
            doc = reader.read(ProxyXml.class.getResourceAsStream("/config.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public static String readClass(String name){
        String nodePath = "//beans/bean[@id='" + name + "']";
        Element n = (Element) doc.selectSingleNode(nodePath);
        return n.attributeValue("class");
    }
}
