package com.icore.winvaz.javaee.mybatiss;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * Mybatis技术内幕 21页
 * @Author wdq
 * @Create 2021/8/13 15:01
 * @Version 1.0.0
 */
public class XPathTest {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        // 开启验证
        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setNamespaceAware(false);
        documentBuilderFactory.setIgnoringComments(true);
        documentBuilderFactory.setCoalescing(false);
        documentBuilderFactory.setExpandEntityReferences(true);

        // 创建DocumentBuilder
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        // 设置异常处理对象
        builder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println("error:" + exception.getMessage());
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.out.println("fatalError:" + exception.getMessage());
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println("WARN:" + exception.getMessage());
            }
        });

        // 将文档加载到一个Document对象中
        InputStream inputStream = XPathTest.class.getResourceAsStream("/inventory.xml");
        Document doc = builder.parse(inputStream);
        // 创建XPathFactory
        XPathFactory factory = XPathFactory.newInstance();
        // 创建XPath对象
        XPath xPath = factory.newXPath();
        // 编译XPath表达式
        XPathExpression expr = xPath.compile("//book[author='Neal Stephenson']/title/text()");
        // 通过XPath表达式得到结果,第一个参数指定了XPath表达式进行查询的上下文节点,也就是在指定
        // 节点下查找符合XPath的节点。
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        System.out.println("查询作者为Neal Stephenson的图书标题:");
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
    }
}
