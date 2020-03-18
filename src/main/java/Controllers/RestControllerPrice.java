package Controllers;

import Service.JDBCPrice;
import Tables.Price;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Контроллер для price: id страны, partnum, дата сделки, rrp
@RestController
public class RestControllerPrice {

    @GetMapping(value = "/price")
    public @ResponseBody List<Price> getData(){
        JDBCPrice answer = new JDBCPrice();
        return answer.getPriceList();
    }
}
