package org.example.Service;

import org.example.Tables.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAnswerCountry {

    private List<Country> ListCountries;
    private static  final String SQL_SELECT = "SELECT countryid," +
            " code," +
            " namecountry" +
            " FROM country;";

    public  JDBCAnswerCountry(){

    }

    private void createListCountries(){
        ListCountries = new ArrayList<>();
        PosgreSQLSpace postgres = new PosgreSQLSpace();

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

                int countryId = resultSet.getInt("countryid");
                String nameCountry = resultSet.getString("namecountry");
                String Code = resultSet.getString("code");

                Country country = new Country();
                country.setId(countryId);
                country.setCode(Code);
                country.setNameCountry(nameCountry);

                ListCountries.add(country);

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    public List<Country> getListCountries(){
        createListCountries();

        return ListCountries;
    }
}
