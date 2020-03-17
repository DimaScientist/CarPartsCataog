package Controllers;

import Service.JDBCPrice;
import Tables.Price;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RestControllerPrice {

    @GetMapping(value = "/price")
    public @ResponseBody List<Price> getData(){
        JDBCPrice answer = new JDBCPrice();
        return answer.getPriceList();
    }
}
