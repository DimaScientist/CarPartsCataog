package org.example.Controllers;


import org.example.Service.JDBCAnswerCountry;
import org.example.Tables.Country;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//Контроллер для вывода стран: id, код, название
@RestController
public class RestControllerCountry {

    @GetMapping(value = "/country")
    public List<Country> getData(){
        JDBCAnswerCountry answer = new JDBCAnswerCountry();
        return answer.getListCountries();
    }
}
