package org.example.Service;

public class PosgreSQLSpace {

    private final String passsword = "1234";
    private final String postgresUser = "postgres";
    private final String passwordUser = "D69917";
    private final String urlAdress = "jdbc:postgresql://127.0.0.1:6776/catalogzapchasty";


    public String getPasssword() {
        return passsword;
    }

    public String getPostgresUser() {
        return postgresUser;
    }

    public String getUrlAdress() {
        return urlAdress;
    }

    public String getPasswordUser(){return passwordUser;}

}
