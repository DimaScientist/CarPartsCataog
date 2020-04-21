package org.example.Service;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.UUIDTimer;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.example.Tables.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JDBCAnswerUser {
    private List<User> allUsersList;

    String SQL_SELECT_ALL_USERS = "SELECT login, token, lastname, firstname " +
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

                User user = new User();

                String login = resultSet.getString("login");
                String token = resultSet.getString("token");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");

                user.setLastName(lastName);
                user.setFirstName(firstName);
                user.setToken(token);
                user.setUsername(login );

                allUsersList.add(user);

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

    public String InsertUser(String login, String password, String firstName, String lastName){
            if(CountUsersByLogin(login) == 0){

                PostgreSQLSpace postgres = new PostgreSQLSpace();
                String SQL_INSERT_USER = "INSERT INTO public.user(" +
                        "login, password, id_user, lastname, firstname, token ) " +
                        "VALUES (?, ?, ?, ?, ?, ?);";


                try(Connection conn = DriverManager.getConnection(
                        postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
                    PreparedStatement preparedStatement =
                            conn.prepareStatement(SQL_INSERT_USER)){

                    preparedStatement.setString(1, login);
                    preparedStatement.setString(2, password);
                    preparedStatement.setInt(3, MaxIdUsers() + 1);
                    preparedStatement.setString(4, lastName);
                    preparedStatement.setString(5, firstName);

                    String token = UUID.randomUUID().toString() + "-jwt-" + login;

                    //preparedStatement.setString(6, "fake-jwt-token");
                    preparedStatement.setString(6,token);


                    preparedStatement.executeUpdate();

                    return "";


                } catch (Exception e){
                    System.err.println(e.getMessage());
                    return "Error";
                }
            }
            else{
                return String.format("Username %s is already taken",login);
            }
     }

    public List<User> getAllUsersList() {
        createUsersList();
        return allUsersList;
    }


    public User FindUserByLoginAndPassword(String login, String password){
        PostgreSQLSpace postgres = new PostgreSQLSpace();
        String SQL_SELECT_COUNT_USERS_BY_LOGIN_AND_PASSWORD = String.format("SELECT id_user, login, token, lastname, firstname " +
                "FROM public.user WHERE login = '%s' AND password = '%s'", login, password);

        User user = new User();

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery( SQL_SELECT_COUNT_USERS_BY_LOGIN_AND_PASSWORD);

            while (resultSet.next()) {

                Long idUser = resultSet.getLong("id_user");

                String log = resultSet.getString("login");
                String token = resultSet.getString("token");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");

                user.setId(idUser);
                user.setUsername(log);
                user.setToken(token);
                user.setFirstName(firstName);
                user.setLastName(lastName);
            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        return  user;
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


    public User FindUserByLogin(String login){
        PostgreSQLSpace postgres = new PostgreSQLSpace();
        String SELECT_SQL_USERS_BY_LOGIN = String.format("SELECT login, token FROM public.user" +
                " WHERE login = '%s'", login);
        User user = new User();
        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){

            ResultSet resultSet = statement.executeQuery(SELECT_SQL_USERS_BY_LOGIN);

            while (resultSet.next()){
               String log = resultSet.getString("login");
               String token = resultSet.getString("token");

               user.setToken(token);
               user.setUsername(log);
            }
        }
        catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return user;
    }
}
