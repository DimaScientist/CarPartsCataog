package org.example.Service;

import org.example.Tables.User;

import java.sql.*;
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

        int count = 0;

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

        int count = 0;

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
                String SQL_INSERT_USER = "INSERT INTO public.user(" +
                        "login, password, id_user) " +
                        "VALUES (?, ?, ?);";

                try(Connection conn = DriverManager.getConnection(
                        postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
                    PreparedStatement preparedStatement =
                            conn.prepareStatement(SQL_INSERT_USER)){

                    preparedStatement.setString(1, login);
                    preparedStatement.setString(2, password);
                    preparedStatement.setInt(3, MaxIdUsers() + 1);

                    preparedStatement.executeUpdate();

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

        int count = 0;

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
        String SQL_DELETE_USER_BY_LOGIN = "DELETE FROM public.user " +
                "WHERE login = ?;";

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            PreparedStatement preparedStatement =
                    conn.prepareStatement(SQL_DELETE_USER_BY_LOGIN)){

            preparedStatement.setString(1, login );
            preparedStatement.executeUpdate();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }


    public List<User> FindUserByLogin(String login){
        PostgreSQLSpace postgres = new PostgreSQLSpace();
        String SELECT_SQL_USERS_BY_LOGIN = String.format("SELECT login, password FROM public.user" +
                " WHERE login = '%s'", login);
        List<User> listUsers = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){

            ResultSet resultSet = statement.executeQuery(SELECT_SQL_USERS_BY_LOGIN);

            while (resultSet.next()){
               String log = resultSet.getString("login");
               String password = resultSet.getString("password");
               User user = new User();
               user.setPassword(password);
               user.setUsername(log);
               listUsers.add(user);
            }
        }
        catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return listUsers;
    }
}
