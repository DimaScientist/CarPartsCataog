package org.example.Service;

import org.example.Tables.ResultTable;

import java.sql.*;
import java.util.*;

public class JDBCAnswerResultPriceHistory {

    private static List<ResultTable> resultTableList;

    private String brandType;
    private int partnum;


    public JDBCAnswerResultPriceHistory(int brandTypeId, int partnum){
        resultTableList = new ArrayList<>();
        this.brandType = BrandType.getBrandType(brandTypeId);
        this.partnum = partnum;

    }

    private void createResultTableList(){

        PosgreSQLSpace postgres = new PosgreSQLSpace();


        String SQL_SELECT = String.format("SELECT public.parts.brantype AS brandType, " +
                "public.parts.partnum AS partNumber, " +
                "public.partsvehicles.altpartnum AS altPartNum, " +
                "public.parts.brand AS brand, " +
                "public.parts.descr AS description, " +
                "public.price.rrp AS rrp, " +
                "public.price.pridate AS priceDate, " +
                "public.parts.artstat AS articleStatus  " +
                "FROM public.country JOIN public.price " +
                "ON public.country.countryId = public.price.countryId " +
                "JOIN public.parts ON public.price.partnum = public.parts.partnum " +
                "JOIN public.partsvehicles  ON public.parts.faltpartnum = public.parts.altpartnum " +
                "JOIN public.vehicles ON public.partsvehicles.carid = public.vehicles.carid " +
                "WHERE public.parts.partnum = %d AND public.parts.brandrype = %s", this.partnum, this.brandType);

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

                String brandType = resultSet.getString("brandType");
                String partNumber = resultSet.getString("partNumber");
                String altPartNum = resultSet.getString("altPartNum");
                String brand = resultSet.getString("brand");
                String description = resultSet.getString("description");
                double rrp = resultSet.getDouble("rrp");
                String priceDate = resultSet.getString("priceDate");
                int numberOfReferences = resultSet.getInt("numOfRef");
                int numberOfVehiclesLinkings = resultSet.getInt("numOfVehLinks");
                String articleStatus = resultSet.getString("articleStatus");

                ResultTable resultTable = new ResultTable();
                resultTable.setBrandType(brandType);
                resultTable.setPartNumber(partNumber);
                resultTable.setAltPartNum(altPartNum);
                resultTable.setBrand(brand);
                resultTable.setDescription(description);
                resultTable.setRrp(rrp);
                resultTable.setPriceDate(priceDate);
                resultTable.setNumberOfReferences(numberOfReferences);
                resultTable.setNumberOfVehiclesLinkings(numberOfVehiclesLinkings);
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
