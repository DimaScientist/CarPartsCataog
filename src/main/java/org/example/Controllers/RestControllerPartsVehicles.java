package org.example.Controllers;


import org.example.Service.JDBCAnswerPartsVehicles;
import org.example.Tables.PartsVehicles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//Контроллер для partsvehicles: id машины и alpartnum
@RestController
public class RestControllerPartsVehicles {

    @GetMapping(value = "/partsvehicles")
    public List<PartsVehicles> getData(){
        JDBCAnswerPartsVehicles answer = new JDBCAnswerPartsVehicles();
        return answer.getPartsVehiclesList();
    }
}
