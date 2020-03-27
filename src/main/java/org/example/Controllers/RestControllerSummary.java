package org.example.Controllers;


import org.example.Service.JDBCAnswerSummary;
import org.example.Tables.Summary;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/summary")
public class RestControllerSummary {

    @GetMapping(value = "/{partnum}", produces = "application/json")
    public Summary getData(@PathVariable String partnum){
        JDBCAnswerSummary jdbcAnswerSummary = new JDBCAnswerSummary(partnum);
        return jdbcAnswerSummary.getListSummary();
    }
}
