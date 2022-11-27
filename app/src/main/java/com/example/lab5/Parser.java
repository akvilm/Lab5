package com.example.lab5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Parser {
    public static String getRateFromECB (InputStream stream, String currencyCode) throws IOException{
        String result = "";
        try {
            DocumentBuilderFactory xmlDocFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDocBuilder = xmlDocFactory.newDocumentBuilder();
            Document doc = xmlDocBuilder.parse(stream);

            NodeList rateNodes = doc.getElementsByTagName("Cube");
            for (int i = 0; i < rateNodes.getLength(); ++i) {
                Element rate = (Element) rateNodes.item(i);
                String currencyName = rate.getAttribute("currency");
                if (currencyName.equals(currencyCode)) {
                    result = rate.getAttribute("rate");
                    break;
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return result;
    }
}
