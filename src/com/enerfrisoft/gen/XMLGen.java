package com.enerfrisoft.gen;

import java.util.ArrayList;

public class XMLGen {

    public static String generateCode(ArrayList<String> atributes, String className, String objectName, String tipo, String procesoId) {
        String out = "";
        out = "package com.Empresa1." + objectName + ";\n"
                + "import java.io.StringWriter;\n"
                + "import javax.xml.parsers.DocumentBuilder;\n"
                + "import javax.xml.parsers.DocumentBuilderFactory;\n"
                + "import javax.xml.parsers.ParserConfigurationException;\n"
                + "import javax.xml.transform.Transformer;\n"
                + "import javax.xml.transform.TransformerException;\n"
                + "import javax.xml.transform.TransformerFactory;\n"
                + "import javax.xml.transform.dom.DOMSource;\n"
                + "import javax.xml.transform.stream.StreamResult;\n"
                + "import org.w3c.dom.DOMException;\n"
                + "import org.w3c.dom.Document;\n"
                + "import org.w3c.dom.Element;\n"
                + "public class XML" + className + " {\n"
                + "public static String grabar(" + className + " entity){\n"
                + "Document request;\n"
                + "try{\n"
                + "DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();\n"
                + "DocumentBuilder builder = factory.newDocumentBuilder();\n"
                + "request = builder.newDocument();\n"
                + "Element root = request.createElement(\"root\");\n"
                + "request.appendChild(root);\n"
                + "Element acceso = request.createElement(\"acceso\");\n"
                + "root.appendChild(acceso);\n"
                + "Element username = request.createElement(\"username\");\n"
                + "username.appendChild(request.createTextNode(entity.getUsuario()));\n"
                + "acceso.appendChild(username);\n"
                + "Element password = request.createElement(\"password\");\n"
                + "password.appendChild(request.createTextNode(entity.getPassword()));\n"
                + "acceso.appendChild(password);\n"
                + "Element solicitud = request.createElement(\"solicitud\");\n"
                + "root.appendChild(solicitud);\n"
                + "Element tipo = request.createElement(\"tipo\");\n"
                + "tipo.appendChild(request.createTextNode(\"1\"));\n"
                + "solicitud.appendChild(tipo);\n"
                + "Element procesoid = request.createElement(\"procesoid\");\n"
                + "procesoid.appendChild(request.createTextNode(\"3\"));\n"
                + "solicitud.appendChild(procesoid);\n"
                + "Element variables = request.createElement(\"variables\");\n"
                + "root.appendChild(variables);\n\n";

        for (String atrib : atributes) {
            out
                    += "Element " + atrib + " = request.createElement(\"" + atrib + "\");\n"
                    + atrib + ".appendChild(request.createTextNode(entity.get" + atrib + "()));\n"
                    + "if(!entity.get" + atrib + "().equals(\"\")){\n"
                    + "\tvariables.appendChild(" + atrib + ");\n"
                    + "}";
        }
        out
                += "\n\n"
                + "DOMSource domSource = new DOMSource(request);\n"
                + "StringWriter writer = new StringWriter();\n"
                + "StreamResult result = new StreamResult(writer);\n"
                + "TransformerFactory tf = TransformerFactory.newInstance();\n"
                + "Transformer transformer = tf.newTransformer();\n"
                + "transformer.transform(domSource, result);\n"
                + "return writer.toString();\n"
                + "}catch(ParserConfigurationException | TransformerException | DOMException ex){\n"
                + "System.out.println(ex.getLocalizedMessage());\n"
                + "}\n"
                + "return null;\n"
                + "}\n"
                + "}\n";

        return out;
    }

    public static void main(String[] args) {
        //Code Generator
        ArrayList<String> array = new ArrayList<>();

        
        array.add("NUMNITEMPRESATRANSPORTE");
        array.add("NUMMANIFIESTOCARGA");
        array.add("MOTIVOANULACIONMANIFIESTO");
        

        System.out.print(XMLGen.generateCode(array, "AnularManifiesto", "anularManifiesto", "1", "32"));

    }
}
