package Service;

import Tables.Country;
import Tables.PartsVehicles;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAnswerPartsVehicles {

    private List<PartsVehicles> partsVehiclesList;
    private static final String SQL_SELECT = "SELECT public.partsvehicles.carid" +
            " AS carId, public.partsvehicles.altpartnum " +
            "AS altpartnum" +
            " FROM public.partsvehicles";


    public JDBCAnswerPartsVehicles(){}


    private void createListPartsVehicles(){
        partsVehiclesList = new ArrayList<>();

        PosgreSQLSpace postgres = new PosgreSQLSpace();

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

                String altpartnum = resultSet.getString("  altpartnum");
                int carid = resultSet.getInt("carId");

                PartsVehicles partsVehicles = new PartsVehicles();
                partsVehicles.setCarId(carid);
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