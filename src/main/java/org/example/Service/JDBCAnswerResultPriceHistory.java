package org.example.Service;

import org.example.Tables.ResultTable;

import java.sql.*;
import java.util.*;

public class JDBCAnswerResultPriceHistory {

    private static List<ResultTable> resultTableList;

    private String partnum;


    public JDBCAnswerResultPriceHistory(String partnum){

        this.partnum = partnum;

    }

    private void createResultTableList(){
        resultTableList = new ArrayList<>();

        PostgreSQLSpace postgres = new PostgreSQLSpace();


        String SQL_SELECT = String.format(
                "SELECT " +
                "brantype, " +
                "parts.partnum, " +
                "altpartnum, " +
                "parts.brand, " +
                "descr, " +
                "rrp, " +
                "pridate, " +
                "parts.arstat  " +
                "FROM country JOIN price " +
                "ON country.countryId = price.countryId " +
                "JOIN parts ON price.partnum = parts.partnum " +
                "JOIN partsvehicles  ON parts.faltpartnum = partsvehicles.altpartnum " +
                "JOIN vehicles ON partsvehicles.carid = vehicles.carid " +
                "WHERE public.parts.partnum = '%s';", this.partnum);

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

                String brandType = resultSet.getString("brantype");
                String partNumber = resultSet.getString("partnum");
                String altPartNum = resultSet.getString("altpartnum");
                String brand = resultSet.getString("brand");
                String description = resultSet.getString("descr");
                double rrp = resultSet.getDouble("rrp");
                String priceDate = resultSet.getString("pridate");
                String articleStatus = resultSet.getString("arstat");

                ResultTable resultTable = new ResultTable();
                resultTable.setBrandType(brandType);
                resultTable.setPartNumber(partNumber);
                resultTable.setAltPartNum(altPartNum);
                resultTable.setBrand(brand);
                resultTable.setDescription(description);
                resultTable.setRrp(rrp);
                resultTable.setPriceDate(priceDate);
                resultTable.setArticleStatus(articleStatus);

                resultTableList.add(resultTable);

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public List<ResultTable> getResultTableList(){
        createResultTableList();
        return resultTableList;
    }



}
