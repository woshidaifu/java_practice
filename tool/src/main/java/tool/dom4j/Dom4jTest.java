package tool.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Dom4jTest {

    /**
     * 传入xml解析
     * 
     * @param document
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static void parseXML(Document document) throws ParserConfigurationException, SAXException, IOException {
        // 获取根元素
        Element root = document.getRootElement();
        System.out.println("Root: " + root.getName());

        // 获取所有子元素
        List<Element> childList = root.elements();
        System.out.println("total child count: " + childList.size());

        // 获取特定名称的子元素
        List<Element> childList2 = root.elements("hello");
        System.out.println("hello child: " + childList2.size());

        // 获取名字为指定名称的第一个子元素
        Element firstWorldElement = root.element("world");
        // 输出其属性
        System.out.println("first World Attr: " + firstWorldElement.attribute(0).getName() + "="
                + firstWorldElement.attributeValue("name"));

        System.out.println("-------------迭代输出--------------");
        // 迭代输出
        for (Iterator<Element> iter = root.elementIterator(); iter.hasNext();) {
            Element e = (Element) iter.next();
            System.out.println(e.attributeValue("name"));

        }

    }

}