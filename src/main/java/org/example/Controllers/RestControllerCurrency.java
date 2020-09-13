package org.example.Controllers;


import org.example.Service.JDBCAnswerCurrency;
import org.example.Tables.Currency;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/currency")

public class RestControllerCurrency {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
    public List<Currency> getData(){
        JDBCAnswerCurrency answer = new JDBCAnswerCurrency();
        return answer.getCurrencyList();
    }
}
