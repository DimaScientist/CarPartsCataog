package org.example.Service;

import org.example.Tables.Summary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

public class JDBCAnswerSummary {
    private Summary summary;
    private PostgreSQLSpace postgres;
    private String partnum;

    public Summary getListSummary() {
        createSummary();
        return summary;
    }

    public  JDBCAnswerSummary(String partnum){
        postgres = new PostgreSQLSpace();
        this.partnum = partnum;
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
        String SQL_SELECT= String.format("SELECT COUNT(brantype)\n" +
                "\tFROM public.parts\n" +
                "\tWHERE brantype = 'OE' AND " +
                "partnum = '%s';", this.partnum);

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
        String SQL_SELECT= String.format("SELECT COUNT(brantype)\n" +
                "\tFROM public.parts\n" +
                "\tWHERE brantype = 'IAM' " +
                "AND partnum = '%s';", this.partnum);

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
        String SQL_SELECT= String.format("SELECT COUNT(brantype)\n" +
                "\tFROM public.parts\n" +
                "\tWHERE brantype = 'Wholesalers' " +
                "AND partnum = '%s';", this.partnum);

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
        String SQL_SELECT = String.format("SELECT AVG(rrp)\n" +
                "\tFROM public.price" +
               "\tWHERE price.partnum = '%s';", this.partnum);

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

                rrpAvg = resultSet.getDouble("avg");

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        DecimalFormat df = new DecimalFormat(".##");
        return df.format(rrpAvg);
    }

    private String getRrpRange(){
        double rrpRange = 0;
        String SQL_SELECT = String.format("SELECT AVG(rrp)\n" +
                "\tFROM public.price" +
                "\tWHERE price.partnum != '%s';", this.partnum);

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

                rrpRange = resultSet.getDouble("avg");

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        DecimalFormat df = new DecimalFormat(".##");

        return df.format(rrpRange);
    }

}
