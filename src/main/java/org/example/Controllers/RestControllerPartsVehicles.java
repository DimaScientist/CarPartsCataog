package org.example.Controllers;


import org.example.Service.JDBCAnswerPartsVehicles;
import org.example.Tables.PartsVehicles;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//Контроллер для partsvehicles: id машины и alpartnum
@RestController
@RequestMapping("/partsvehicles")
public class RestControllerPartsVehicles {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
    public List<PartsVehicles> getData(){
        JDBCAnswerPartsVehicles answer = new JDBCAnswerPartsVehicles();
        return answer.getPartsVehiclesList();
    }
}
