package org.example.Controllers;


import org.example.Service.JDBCAnswerSummary;
import org.example.Tables.Summary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/summary")
public class RestControllerSummary {

    @GetMapping
    public Summary getData(){
        JDBCAnswerSummary jdbcAnswerSummary = new JDBCAnswerSummary();
        return jdbcAnswerSummary.getListSummary();
    }
}
