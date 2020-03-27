package org.example.Controllers;

import org.example.Service.JDBCPartNumber;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Контроллер для price: id страны, partnum, дата сделки, rrp
@CrossOrigin
@RestController
@RequestMapping("/partnum")
public class RestControllerPartnum {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
    public List<String> getData(){
        JDBCPartNumber answer = new JDBCPartNumber();
        return answer.getPartNumber();
    }
}
