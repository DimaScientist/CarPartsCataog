package org.example.Service;

import org.example.Tables.ResultTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCAnswerPagingResultPriceHistory {
    private static List<ResultTable> resultTableList;

    private final String partnum;
    private final int start;
    private final int num;


    public JDBCAnswerPagingResultPriceHistory(String partnum, int start, int num){

        this.partnum = partnum;
        this.num = num;
        this.start = start;

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
                        "WHERE public.parts.partnum = '%s' " +
                        "LIMIT %d " +
                        "OFFSET %d;", this.partnum, this.num, this.start);

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
