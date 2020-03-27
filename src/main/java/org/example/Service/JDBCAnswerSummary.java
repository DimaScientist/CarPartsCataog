package org.example.Service;

import org.example.Tables.Price;
import org.example.Tables.Summary;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class JDBCAnswerSummary {
    private Summary summary;
    private PosgreSQLSpace postgres;

    public Summary getListSummary() {
        createSummary();
        return summary;
    }

    public  JDBCAnswerSummary(){
        postgres = new PosgreSQLSpace();
        summary = new Summary();
    }

    private void createSummary(){

        summary.setIamParts(getIamParts());
        summary.setOeParts(getOeParts());
        summary.setRrpAvg(getRrpAvg());

        summary.setRrpRange(getRrpRange());
        summary.setWholesaler(getWholesaler());
    }

    private int getOeParts(){
        int oeParts = 0;
        String SQL_SELECT= "SELECT COUNT(brantype)\n" +
                "\tFROM public.parts\n" +
                "\tWHERE brantype = '{OE}';";

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

               oeParts = resultSet.getInt("count");

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        return oeParts;

    }

    private int getIamParts(){
        int iamParts = 0;
        String SQL_SELECT= "SELECT COUNT(brantype)\n" +
                "\tFROM public.parts\n" +
                "\tWHERE brantype = '{IAM}';";

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

                iamParts = resultSet.getInt("count");

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        return iamParts;


    }

    private int getWholesaler(){
        int wholesaler = 0;
        String SQL_SELECT= "SELECT COUNT(brantype)\n" +
                "\tFROM public.parts\n" +
                "\tWHERE brantype = '{Wholesalers}';";

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

                wholesaler = resultSet.getInt("count");

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return wholesaler;

    }


    private String getRrpAvg(){
        double rrpAvg = 0;
        String SQL_SELECT = "SELECT AVG(rrp)\n" +
                "\tFROM public.price;";

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

                rrpAvg = resultSet.getInt("avg");

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        DecimalFormat df = new DecimalFormat(".##");
        return df.format(rrpAvg);
    }

    private double getRrpRange(){
        double rrpRange = 0;
        return rrpRange;

    }

}
