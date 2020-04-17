package org.example.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCAnswerUser {
    private List<String> allUsersList;

    String SQL_SELECT_ALL_USERS = "SELECT login " +
            "FROM public.user";


    public JDBCAnswerUser(){}

    private void createUsersList(){
        allUsersList = new ArrayList<>();

        PostgreSQLSpace postgres = new PostgreSQLSpace();

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);

            while (resultSet.next()){

                String login = resultSet.getString("login");

                allUsersList.add(login);

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private Integer CountUsersByLogin(String login){
        PostgreSQLSpace postgres = new PostgreSQLSpace();
        String SQL_SELECT_COUNT_USERS_BY_LOGIN = String.format("SELECT count(id_user) " +
                "FROM public.user WHERE login = '%s'", login);

        Integer count = 0;

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery( SQL_SELECT_COUNT_USERS_BY_LOGIN);

            while (resultSet.next()){

                count = resultSet.getInt("count");
            }


        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        return  count;
    }

    private Integer MaxIdUsers(){
        PostgreSQLSpace postgres = new PostgreSQLSpace();
        String SQL_SELECT_COUNT_USERS_BY_LOGIN = "SELECT Max(id_user) " +
                "FROM public.user";

        Integer count = 0;

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery( SQL_SELECT_COUNT_USERS_BY_LOGIN);

            while (resultSet.next()){

                count = resultSet.getInt("max");
            }


        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        return  count;
    }

    public String InsertUser(String login, String password){
            if(CountUsersByLogin(login) == 0){

                PostgreSQLSpace postgres = new PostgreSQLSpace();
                String SQL_INSERT_USER = String.format("INSERT INTO public.user(\n" +
                        "\tlogin, password, id_user)\n" +
                        "\tVALUES ('%s', '%s', %d);", login, password, MaxIdUsers() + 1);

                try(Connection conn = DriverManager.getConnection(
                        postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
                    Statement statement = conn.createStatement()){
                    statement.executeQuery(SQL_INSERT_USER);

                    return "Succeseful registration";


                } catch (Exception e){
                    System.err.println(e.getMessage());
                    return "Failled registration";
                }
            }
            else{
                return "Failled registration";
            }
     }

    public List<String> getAllUsersList() {
        createUsersList();
        return allUsersList;
    }


    private Integer CountUsersByLoginAndPassword(String login, String password){
        PostgreSQLSpace postgres = new PostgreSQLSpace();
        String SQL_SELECT_COUNT_USERS_BY_LOGIN_AND_PASSWORD = String.format("SELECT count(id_user) " +
                "FROM public.user WHERE login = '%s' AND password = '%s'", login, password);

        Integer count = 0;

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery( SQL_SELECT_COUNT_USERS_BY_LOGIN_AND_PASSWORD);

            while (resultSet.next()){

                count = resultSet.getInt("count");
            }


        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        return  count;
    }

    public String FindUserByLoginAndPassword(String login, String password){
        Integer count = CountUsersByLoginAndPassword(login, password);
        if(count != 0) return "Succeseful authorisation";
        else return "Failled authorisation";
    }

    public void DeleteUser(String login){
        PostgreSQLSpace postgres = new PostgreSQLSpace();
        String SQL_DELETE_USER_BY_LOGIN = String.format("DELETE FROM public.user " +
                "WHERE login = '%S'", login);

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){

            statement.executeQuery(SQL_DELETE_USER_BY_LOGIN);


        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
