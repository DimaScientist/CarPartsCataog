package org.example.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPartNumber {

    private List<String> partnumList;
    private static final String SQL_SELECT = "SELECT "+
            "partnum " +
            "FROM public.parts;";

    public JDBCPartNumber(){
    }

    private void createPriceList(){
        partnumList = new ArrayList<>();

        PostgreSQLSpace postgres = new PostgreSQLSpace();

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

               String partnum = resultSet.getString("partnum");

                partnumList.add(partnum);

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public List<String> getPartNumber(){
        createPriceList();
        return partnumList;
    }

}
