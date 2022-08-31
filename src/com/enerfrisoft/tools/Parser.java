/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enerfrisoft.tools;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;

public class Parser {

    private static Document parse(String answer) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            StringReader reader = new StringReader(answer);
            InputSource in = new InputSource(reader);
            Document document = builder.parse(in);

//            DOMSource domSource = new DOMSource(document);
//            StringWriter writer = new StringWriter();
//            StreamResult result = new StreamResult(writer);
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer transformer = tf.newTransformer();
//            transformer.transform(domSource, result);
            return document;
        } catch (Exception e) {
            return null;
        }

    }

    public static ArrayList<String> verify(String xmlAnswer) {
        try {
            String autorizacion = parse(xmlAnswer).getElementsByTagName("ingresoid").item(0).getTextContent();
            ArrayList<String> data = new ArrayList<>();
            data.add(autorizacion);
            try {
                String seguridadqr = parse(xmlAnswer).getElementsByTagName("seguridadqr").item(0).getTextContent();
                data.add(seguridadqr);
            } catch (Exception e) {
                return data;
            }
            
            return data;
        } catch (Exception e) {
            ArrayList<String> out = new ArrayList<>();
            out.add("error");
            return out;
        }

    }
    
    public static String getErrors(String xmlAnswer){
        try {
            return parse(xmlAnswer).getElementsByTagName("ErrorMSG").item(0).getTextContent();
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String answer = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?> <root> <ingresoid>12</ingresoid> <seguridadqr>testing_word</seguridadqr></root>";
        System.out.println(verify(answer).get(0));
    }

}
