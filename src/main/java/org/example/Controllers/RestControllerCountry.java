package org.example.Controllers;


import org.example.Service.JDBCAnswerCountry;
import org.example.Tables.Country;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//Контроллер для вывода стран: id, код, название
@CrossOrigin
@RestController
@RequestMapping("/country")
public class RestControllerCountry {

    @GetMapping
    public List<Country> getData(){
        JDBCAnswerCountry answer = new JDBCAnswerCountry();
        return answer.getListCountries();
    }
}
