package org.example.Service;

public class PostgreSQLSpace {

    private final String passsword = "D69917";
    private final String postgresUser = "postgres";
    private final String urlAdress = "jdbc:postgresql://127.0.0.1:5432/catalogzapchasty";


    public String getPasssword() {
        return passsword;
    }

    public String getPostgresUser() {
        return postgresUser;
    }

    public String getUrlAdress() {
        return urlAdress;
    }

}
