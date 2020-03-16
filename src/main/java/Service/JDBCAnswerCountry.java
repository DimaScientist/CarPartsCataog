package Service;

import Tables.Country;
import Tables.ResultTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAnswerCountry {

    private List<Country> ListCountries;

    public  JDBCAnswerCountry(){

    }

    private void createListCountries(){
        ListCountries = new ArrayList<>();
        PosgreSQLSpace postgres = new PosgreSQLSpace();


        String SQL_SELECT = "SELECT public.country.countryId AS Id, public.country.Code AS Code, public.country.nameCountry AS nameCountry FROM public.country";

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Integer countryId = resultSet.getInt("  Id");
                String nameCountry = resultSet.getString("nameCountry");
                String Code = resultSet.getString("Code");

                Country country = new Country();
                country.setId(countryId);
                country.setCode(Code);
                country.setNameCountry(nameCountry);

                ListCountries.add(country);

            }

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
        }

    }

    public List<Country> getListCountries(){
        createListCountries();

        return ListCountries;
    }
}
