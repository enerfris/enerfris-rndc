package com.enerfrisoft.gen;

import java.util.ArrayList;

public class AsyncGen {

    public static String generateClass(ArrayList<String> attributes, String className, String objectName) {
        String myClass = "";
        for (int i = 0; i < attributes.size(); i++) {
            myClass += ""
                    + "String data" + i + " = data.get(\"" + attributes.get(i) + "\");\n"
                    + "System.out.println(data" + i + ");\n"
                    + "this.remesa.set" + attributes.get(i) + "(data" + i + ");\n\n";
        }

        return myClass;
    }

    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<>();
        array.add("INGRESOID");
        array.add("NUMNITEMPRESATRANSPORTE");
        array.add("NUMMANIFIESTOCARGA");
        array.add("CONSECUTIVOINFORMACIONVIAJE");
        array.add("MANNROMANIFIESTOTRANSBORDO");
        array.add("CODOPERACIONTRANSPORTE");
        array.add("FECHAEXPEDICIONMANIFIESTO");
        array.add("CODMUNICIPIOORIGENMANIFIESTO");
        array.add("CODMUNICIPIODESTINOMANIFIESTO");
        array.add("CODIDTITULARMANIFIESTO");
        array.add("NUMIDTITULARMANIFIESTO");
        array.add("NUMPLACA");
        array.add("NUMPLACAREMOLQUE");
        array.add("CODIDCONDUCTOR");
        array.add("NUMIDCONDUCTOR");
        array.add("CODIDCONDUCTOR2");
        array.add("NUMIDCONDUCTOR2");
        array.add("VALORFLETEPACTADOVIAJE");
        array.add("RETENCIONFUENTEMANIFIESTO");
        array.add("RETENCIONICAMANIFIESTOCARGA");
        array.add("VALORANTICIPOMANIFIESTO");
        array.add("CODMUNICIPIOPAGOSALDO");
        array.add("CODRESPONSABLEPAGOCARGUE");
        array.add("CODRESPONSABLEPAGODESCARGUE");
        array.add("FECHAPAGOSALDOMANIFIESTO");
        array.add("OBSERVACIONES");
        array.add("SEGURIDADQR");

        System.out.println(AsyncGen.generateClass(array, "AnularManifiesto", "anularManifiesto"));
    }
}
