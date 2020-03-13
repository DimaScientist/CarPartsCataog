package Tables;

public class Country {

    private int id;

    private char[] code;

    private String nameCountry;


    public Country(int id, char[] code,  String nameCountry) {
        this.id = id;
        this.code = code;
        this.nameCountry = nameCountry;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public char[] getCode() {
        return code;
    }

    public void setCode(char[] code) {
        this.code = code;
    }


}
