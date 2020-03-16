package Service;

import Tables.PartsVehicles;
import Tables.Price;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPrice {

    private List<Price> priceList;

    public JDBCPrice(){
    }

    private void createPriceList(){
        priceList = new ArrayList<>();

        PosgreSQLSpace postgres = new PosgreSQLSpace();


        String SQL_SELECT = "SELECT public.price.pridate AS pridate, public.price.rrp AS rrp, " +
                "public.price.countryid AS countrid, public.price.partnum AS partnum" +
                " FROM public.price";

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)){
            ResultSet resultSet = preparedStatement.executeQuery();

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

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    public List<Price> getPriceList(){
        createPriceList();
        return priceList;
    }

}
