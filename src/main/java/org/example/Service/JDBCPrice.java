package org.example.Service;

import org.example.Tables.Price;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPrice {

    private List<Price> priceList;
    private static final String SQL_SELECT = "SELECT public.price.pridate " +
            "AS pridate, public.price.rrp AS rrp, " +
            "public.price.countryid AS countrid, public.price.partnum AS partnum" +
            " FROM public.price";

    public JDBCPrice(){
    }

    private void createPriceList(){
        priceList = new ArrayList<>();

        PosgreSQLSpace postgres = new PosgreSQLSpace();

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

               String pridate = resultSet.getString("pridate");
               double rrp = resultSet.getDouble("rrp");
               int countryid = resultSet.getInt("countryid");
               String partnum = resultSet.getString("partnum");

               Price price = new Price();
               price.setPriceDate(pridate);
               price.setRrp(rrp);
               price.setPartNum(partnum);
               price.setCountryId(countryid);


               priceList.add(price);

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public List<Price> getPriceList(){
        createPriceList();
        return priceList;
    }

}
