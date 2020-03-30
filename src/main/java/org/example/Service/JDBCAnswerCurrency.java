package org.example.Service;

import org.example.Tables.Currency;
import org.example.Tables.PartsVehicles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCAnswerCurrency {
    private List<Currency> currencyList;
    private final String SQL_SELECT = "SELECT id_cur, " +
            "name_cur " +
            "FROM currency";


    public JDBCAnswerCurrency(){}

    private void createCurrencyList(){
        currencyList = new ArrayList<>();

        PosgreSQLSpace postgres = new PosgreSQLSpace();

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

                String name_cur = resultSet.getString("name_cur");
                int id_cur = resultSet.getInt("id_cur");

                Currency currency = new Currency();
                currency.setId_cur(id_cur);
                currency.setNamecurrency(name_cur);

                currencyList.add(currency);

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public List<Currency> getCurrencyList(){
        createCurrencyList();
        return currencyList;
    }

}
