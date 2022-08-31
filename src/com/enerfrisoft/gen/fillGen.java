package com.enerfrisoft.gen;

import com.enerfrisoft.dao.DataSourceClass;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class fillGen {
    
    public static String gen(ArrayList<String> data){
        String out = "";
        for (int i = 0; i < data.size(); i++) {
            out += "Items.setValue(" + data.get(i) + ", data.get(\"" + data.get(i) + "\"));\n";
        }
        return out;
    }
    
    public static void buildTable(String query,int server) {
        try {
            DataSourceClass fachadaBD = new DataSourceClass();
            Connection conexion = fachadaBD.conectar(server);
            Statement sentencia = conexion.createStatement();

            ResultSet result = sentencia.executeQuery(query);

            int columns = result.getMetaData().getColumnCount();

            //Table's data
            ArrayList<ArrayList<String>> data = new ArrayList<>();

            int rows = 0;
            for (int i = 0; i < columns; i++) {
                data.add(new ArrayList<>());
                while (result.next()) {
                    data.get(i).add(result.getString(i + 1));
                    if (i == 0) {
                        rows++;
                    }
                }
                result.beforeFirst();
            }

            ArrayList<String> columnNames = new ArrayList<>();

            String out = "";
            
            for (int i = 1; i <= columns; i++) {
                columnNames.add(result.getMetaData().getColumnName(i));
                out += result.getMetaData().getColumnName(i) + ",\n";
            }

            System.out.println(out);
            
            DefaultTableModel tableModel = new DefaultTableModel() {
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            };

            Vector column;
            for (int i = 0; i < columns; i++) {
                column = new Vector();
                for (int j = 0; j < rows; j++) {
                    column.add(data.get(i).get(j));
                }
                tableModel.addColumn(columnNames.get(i), column);
            }

//            table.setModel(tableModel);
//            jScrollPane1.setViewportView(table);

            fachadaBD.closeConection(conexion);
            sentencia.close();
            result.close();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getStackTrace()[0].getLineNumber());
        }
    }
    
    public static void main(String[] args) {
        
        ArrayList<String> data = new ArrayList<>();
        
        data.add("CODTIPOEMPAQUE");
        data.add("CONSECUTIVOCARGADIVIDIDA");

        
        data.add("CODNATURALEZACARGA");
        data.add("PERMISOCARGAEXTRA");
        data.add("DESCRIPCIONCORTAPRODUCTO");
        data.add("CLASIFICACIONCARGA");
        data.add("NOMBRECARGA");
        data.add("BUSCARNOMBRECARGA");
        data.add("MERCANCIAREMESA");
        data.add("CANTIDADCARGADA");
        data.add("PESOCONTENEDORVACIO");
        data.add("UNIDADMEDIDACAPACIDAD");

        data.add("TIPOIDREMITENTE");
        data.add("NUMIDREMITENTE");

        data.add("FECHACITAPACTADADESCARGUE");
        data.add("HORACITAPACTADADESCARGUEREMESA");
        data.add("NUMIDGPS");

        data.add("OBSERVACIONES");

        data.add("TIPOIDPROPIETARIO");
        data.add("NUMIDPROPIETARIO");

        data.add("DUENOPOLIZA");
        data.add("NUMPOLIZATRANSPORTE");
        data.add("COMPANIASEGURO");
        data.add("FECHAVENCIMIENTOPOLIZACARGA");
        data.add("BUSCARASEGURADORA");
        
        data.add("TIPOIDDESTINATARIO");
        data.add("NUMIDDESTINATARIO");

        data.add("FECHACITAPACTADACARGUE");
        data.add("HORACITAPACTADACARGUE");
        data.add("FECHALLEGADACARGUE");
        data.add("HORALLEGADACARGUEREMESA");
        data.add("FECHAENTRADACARGUE");
        data.add("HORAENTRADACARGUEREMESA");
        data.add("FECHASALIDACARGUE");
        data.add("HORASALIDACARGUEREMESA");
        data.add("HORASPACTOCARGA");
        data.add("MINUTOSPACTOCARGA");
        data.add("HORASPACTODESCARGUE");
        data.add("MINUTOSPACTODESCARGUE");
        
//        for (int i = 0; i < data.size(); i++) {
//            System.out.println("remesa."+data.get(i)+",");
//        }
//        buildTable(""
//                + "select * from remesa", 1);
        String a = "asdasd";
        System.out.println(a.toString().toString());
    }
}
