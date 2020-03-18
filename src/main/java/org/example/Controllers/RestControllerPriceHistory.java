package org.example.Controllers;

import org.example.Service.JDBCAnswerResultPriceHistory;
import org.example.Tables.ResultTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*Контроллер для PriceHistory:

    brandType;
    partNumber;
    altPartNum;
    brand;
    description;
    rrp;
    riceDate;
    numberOfReferences;
    numberOfVehiclesLinkings;
    articleStatus;
 */
@RestController
@RequestMapping("/db")
public class RestControllerPriceHistory {

    @GetMapping(value = "/{partnum,brandtypeId}", produces = "application/json")
    public List<ResultTable> getData(
            @PathVariable Integer partnum, @PathVariable Integer brandtypeId){
        JDBCAnswerResultPriceHistory jdbcAnswer = new JDBCAnswerResultPriceHistory(brandtypeId, partnum);
        return jdbcAnswer.getResultTableList();
    }
}
