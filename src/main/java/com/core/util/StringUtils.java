package com.core.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {

    public static boolean isNullOrEmpty(String input) {
        return input == null || input.length() == 0;
    }

    public static String generateInString(Object[] objects) {
        StringBuilder stringBuilder = new StringBuilder();
        if(objects == null || objects.length == 0) {
            stringBuilder.append("''");
            return stringBuilder.toString();
        } else {
            for (Object object : objects) {
                stringBuilder.append("'");
                stringBuilder.append(object.toString());
                stringBuilder.append("'");
                stringBuilder.append(",");
            }
            String toString = stringBuilder.toString();
            return toString.substring(0, toString.length() - 1);
        }
    }

}
