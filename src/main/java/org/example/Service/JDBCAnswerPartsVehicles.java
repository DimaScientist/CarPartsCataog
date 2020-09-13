package org.example.Service;

import org.example.Tables.PartsVehicles;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAnswerPartsVehicles {

    private List<PartsVehicles> partsVehiclesList;
    private static final String SQL_SELECT = "SELECT" +
            " * " +
            " FROM partsvehicles;";


    public JDBCAnswerPartsVehicles(){}


    private void createListPartsVehicles(){
        partsVehiclesList = new ArrayList<>();

        PostgreSQLSpace postgres = new PostgreSQLSpace();

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

                String altpartnum = resultSet.getString("altpartnum");
                String partnum = resultSet.getString("partnum");
                int carid = resultSet.getInt("carid");

                PartsVehicles partsVehicles = new PartsVehicles();
                partsVehicles.setCarId(carid);
                partsVehicles.setPartnum(partnum);
                partsVehicles.setAltPartNum(altpartnum);

                partsVehiclesList.add(partsVehicles);

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    public List<PartsVehicles> getPartsVehiclesList(){
        createListPartsVehicles();
        return partsVehiclesList;
    }
}
