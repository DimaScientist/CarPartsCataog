package org.example.Controllers;

import org.example.Service.JDBCAnswerResultPriceHistory;
import org.example.Tables.ResultTable;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
@RestController
@RequestMapping("/db")
public class RestControllerPriceHistory {

    @GetMapping(value = "/{partnum}", produces = "application/json")
    public List<ResultTable> getData(
            @PathVariable String partnum){
        JDBCAnswerResultPriceHistory jdbcAnswer = new JDBCAnswerResultPriceHistory(partnum);
        return jdbcAnswer.getResultTableList();
    }
}
