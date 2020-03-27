package org.example.Controllers;


import org.example.Service.JDBCAnswerSummary;
import org.example.Tables.Summary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/summary")
public class RestControllerSummary {

    @GetMapping(value = "/{partnum}", produces = "application/json")
    public Summary getData(@PathVariable String partnum){
        JDBCAnswerSummary jdbcAnswerSummary = new JDBCAnswerSummary(partnum);
        return jdbcAnswerSummary.getListSummary();
    }
}
