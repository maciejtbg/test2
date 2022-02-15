package com.company;


import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BazaDanych{
    public static void zaladujDaneDoBazy(String nazwaBazy, int cenaSprzadazy, int cenaKupna) throws SQLException {
        System.out.println("Loading driver...");

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("Driver loaded!");
//        } catch (ClassNotFoundException e) {
//            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
//        }
        String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11470672";
        String username = "sql11470672";
        String password = "ySC7iFZf6P";

        System.out.println("Connecting database...");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");


            Statement stmt=connection.createStatement();

        DatabaseMetaData md;
        md = connection.getMetaData();
        System.out.println(
                md.getDatabaseProductName()+"\n"+
        md.getDatabaseProductVersion()+"\n"+
        md.getDriverName()+"\n"+
        md.getURL()+"\n"+
        md.getUserName()+"\n"+
        md.supportsAlterTableWithAddColumn()+"\n"+
        md.supportsAlterTableWithDropColumn()+"\n"+
        md.supportsANSI92FullSQL()+"\n"+
        md.supportsBatchUpdates()+"\n"+
        md.supportsMixedCaseIdentifiers()+"\n"+
        md.supportsMultipleTransactions()+"\n"+
        md.supportsPositionedDelete()+"\n"+
        md.supportsPositionedUpdate()+"\n"+
        md.supportsSchemasInDataManipulation()+"\n"+
        md.supportsTransactions()+"\n"+
        md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)+"\n"+
        md.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)+"\n"+
        md.insertsAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE)+"\n"+
        md.updatesAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));
        stmt.executeUpdate("CREATE TABLE Tibia_Coins ("+"id INT(64) NOT NULL AUTO_INCREMENT,"+"czas DATETIME,"+"cena_sell INT," +"cena_buy INT,"+"PRIMARY KEY(id))");
//        String myTableName = "CREATE TABLE AgentDetail ("+ "idNo INT(64) NOT NULL AUTO_INCREMENT,"+ "initials VARCHAR(2),"+ "agentDate DATE,"+ "agentCount INT(64), "+ "PRIMARY KEY(idNo))";

//        ResultSet rs;
//        Date nowDate = new Date();
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dataDoZapisuwPliku = sdf2.format(nowDate);//tuuuu
//        System.out.println("Sprawdzam czy jest baza danych o nazwie..."+nazwaBazy);
//        stmt.executeUpdate("INSERT INTO Tibia_Coins(czas, cena_sell, cena_buy) VALUES ('"+dataDoZapisuwPliku+"','"+cenaSprzadazy+"','"+cenaKupna+"')");
//        System.out.println("Załadowałem dane: CENA ");
//        System.out.println("Dane aktualnie w bazie:");
//            rs=stmt.executeQuery("SELECT * FROM Tibia_Coins");
//            while(rs.next())
//                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getString(4));
//            connection.close();

    }
    public static void sprawdzBazeDanych() throws SQLException {
        System.out.println("Loading driver...");


        String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11470672";
        String username = "sql11470672";
        String password = "ySC7iFZf6P";

        System.out.println("Connecting database...");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");


        Statement stmt=connection.createStatement();

        DatabaseMetaData md;
        md = connection.getMetaData();
        System.out.println(
                md.getDatabaseProductName()+"\n"+
                        md.getDatabaseProductVersion()+"\n"+
                        md.getDriverName()+"\n"+
                        md.getURL()+"\n"+
                        md.getUserName()+"\n"+
                        md.supportsAlterTableWithAddColumn()+"\n"+
                        md.supportsAlterTableWithDropColumn()+"\n"+
                        md.supportsANSI92FullSQL()+"\n"+
                        md.supportsBatchUpdates()+"\n"+
                        md.supportsMixedCaseIdentifiers()+"\n"+
                        md.supportsMultipleTransactions()+"\n"+
                        md.supportsPositionedDelete()+"\n"+
                        md.supportsPositionedUpdate()+"\n"+
                        md.supportsSchemasInDataManipulation()+"\n"+
                        md.supportsTransactions()+"\n"+
                        md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)+"\n"+
                        md.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)+"\n"+
                        md.insertsAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE)+"\n"+
                        md.updatesAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE));

    }
    public static void utworzTablicezPliku() throws SQLException, IOException {
        System.out.println("Loading driver...");
        String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11470672";
        String username = "sql11470672";
        String password = "ySC7iFZf6P";
        System.out.println("Connecting database...");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");
        Statement stmt=connection.createStatement();
        Items itemki = new Items();
        for (int iterator = 0; iterator < itemki.items.size(); iterator++) {
            String nazwaItemuBezZmiany = itemki.items.get(iterator);
            String nazwaBezSpacjiiApostrofow = nazwaItemuBezZmiany.replace(' ','_').replace('\'','_');

            System.out.println(nazwaBezSpacjiiApostrofow);
            //Czytanie z pliku CSS i dobieranie wielkosci INTa do wielkosci ceny.

            //Olać bo czasem cena boy jest 0. Wszystko ustawiam na medium int.
            /*String rozmiarInta;
            int maksymalnaCena = PobieraczCeny.sprawdzMaxCenezCSV(itemki.items.get(iterator))*3;
            if (0 <= maksymalnaCena &&  maksymalnaCena <255){
                    rozmiarInta="TINYINT";
                }else if (255 <= maksymalnaCena &&  maksymalnaCena <65535){
                rozmiarInta="SMALLINT";
            }else if (65535<= maksymalnaCena &&  maksymalnaCena <16777215){
                rozmiarInta="MEDIUMINT";
            } else
                rozmiarInta="INT";


            stmt.executeUpdate("CREATE TABLE "+nazwaBezSpacji+" (id "+rozmiarInta+" NOT NULL AUTO_INCREMENT,czas DATETIME,cena_sell INT,cena_buy INT,PRIMARY KEY(id))");
        */
            stmt.executeUpdate("CREATE TABLE "+nazwaBezSpacjiiApostrofow+" (id MEDIUMINT NOT NULL AUTO_INCREMENT,czas DATETIME,cena_sell MEDIUMINT,cena_buy MEDIUMINT,PRIMARY KEY(id))");

        }

        connection.close();
    }

    public static void zaladujPrzygotowaneZapytanieDoBazy(String zapytanie) throws SQLException, IOException {
        System.out.println("Loading driver...");
        String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11470672";
        String username = "sql11470672";
        String password = "ySC7iFZf6P";
        System.out.println("Connecting database...");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");
        Statement stmt=connection.createStatement();

        stmt.executeUpdate(zapytanie);
        connection.close();
        }




    public static void main(String[] args) throws SQLException, IOException {
        utworzTablicezPliku();





    }
}