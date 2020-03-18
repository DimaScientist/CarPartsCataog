package org.example.Controllers;

import org.example.Service.JDBCPrice;
import org.example.Tables.Price;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Контроллер для price: id страны, partnum, дата сделки, rrp
@RestController
public class RestControllerPrice {

    @GetMapping(value = "/price")
    public List<Price> getData(){
        JDBCPrice answer = new JDBCPrice();
        return answer.getPriceList();
    }
}
