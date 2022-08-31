package com.enerfrisoft.gen;

import java.util.ArrayList;

public class QueryGen {

    public static String generateClass(ArrayList<String> attributes, String className, String objectName) {
        String myClass = ""
                + "package com.enerfrisoft." + objectName + ";\n"
                + "\n"
                + "import java.sql.Connection;\n"
                + "import java.sql.SQLException;\n"
                + "import java.sql.Statement;\n"
                + "import java.util.ArrayList;\n"
                + "\n"
                + "public class Query" + className + " implements Runnable {\n"
                + "\n"
                + "private " + className + " " + objectName + ";\n"
                + "private ArrayList<String> values;\n"
                + "private ArrayList<String> attributes;\n"
                + "\n"
                + "public Query" + className + "(" + className + " " + objectName + ") {\n"
                + "this." + objectName + " = " + objectName + ";\n"
                + "this.values = new ArrayList<>();\n"
                + "this.attributes = new ArrayList<>();\n"
                + "\n"
                + "getAttributes();\n"
                + "}\n"
                + "\n"
                + "private void getAttributes() {\n";

        for (int i = 0; i < attributes.size(); i++) {
            myClass += "values.add(" + objectName + ".get";
            myClass += attributes.get(i);
            myClass += "());\n";
        }
        myClass += ""
                + "}\n"
                + "\n"
                + "private String buildQueryWithAtributes() {\n";
        for (int i = 0; i < attributes.size(); i++) {
            myClass += "attributes.add(\"";
            myClass += attributes.get(i);
            myClass += "\");\n";
        }

        myClass += ""
                + "\n"
                + "\n"
                + "ArrayList<Integer> seleccion = new ArrayList<>();\n"
                + "for (int i = 0; i < values.size(); i++) {\n"
                + "if(!values.get(i).equals(\"\")){\n"
                + "seleccion.add(i);\n"
                + "}\n"
                + "}\n"
                + "\n"
                + "String query = \"replace into " + objectName + "(\";\n"
                + "\n"
                + "for (int i = 0; i < seleccion.size(); i++) {\n"
                + "query += attributes.get(seleccion.get(i));\n"
                + "if (i == seleccion.size() - 1) {\n"
                + "query += \") \";\n"
                + "} else {\n"
                + "query += \",\";\n"
                + "}\n"
                + "}\n"
                + "\n"
                + "query += \"values(\";\n"
                + "\n"
                + "for (int i = 0; i < seleccion.size(); i++) {\n"
                + "query += \"'\";\n"
                + "query += values.get(seleccion.get(i));\n"
                + "if (i == seleccion.size() - 1) {\n"
                + "query += \"');\";\n"
                + "} else {\n"
                + "query += \"',\";\n"
                + "}\n"
                + "}\n"
                + "return query;\n"
                + "}\n"
                + "\n"
                + "public void query() {\n"
                + "try {\n"
                + "com.enerfrisoft.dao.DataSourceClass fachadaBD = new com.enerfrisoft.dao.DataSourceClass();\n"
                + "Connection conexion = fachadaBD.conectar();\n"
                + "Statement sentencia = conexion.createStatement();\n"
                + "String query = \"\";\n"
                + "\n"
                + "query = buildQueryWithAtributes();\n"
                + "System.out.println(query);\n"
                + "sentencia.execute(query);\n"
                + "\n"
                + "fachadaBD.closeConection(conexion);\n"
                + "sentencia.close();\n"
                + "\n"
                + "} catch (SQLException e) {\n"
                + "System.out.println(e.getLocalizedMessage());\n"
                + "} catch (Exception e) {\n"
                + "System.out.println(e.getLocalizedMessage());\n"
                + "}\n"
                + "}\n"
                + "\n"
                + "@Override\n"
                + "public void run() {\n"
                + "query();\n"
                + "}\n"
                + "}\n"
                + "";

        return myClass;
    }

    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<>();
        array.add("NUMMANIFIESTOCARGA ");
        array.add("MOTIVOANULACIONMANIFIESTO ");
        array.add("OBSERVACIONES ");
        array.add("userLog ");
        array.add("dateLog ");
        array.add("estado ");
        array.add("ingresoId ");
        
        System.out.println(QueryGen.generateClass(array, "AnularManifiesto", "anularManifiesto"));
    }
}
