package org.example.Controllers;


import org.example.Service.JDBCAnswerPagingResultPriceHistory;
import org.example.Service.JDBCAnswerResultPriceHistory;
import org.example.Tables.ResultTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/page")
public class PagingController {

    @GetMapping(value = "/{pageId}/{numData}/{partnum}", produces = "application/json")
    public List<ResultTable> getData(
            @PathVariable Integer pageId,
            @PathVariable Integer numData,
            @PathVariable String partnum){
        JDBCAnswerPagingResultPriceHistory jdbc =
                new JDBCAnswerPagingResultPriceHistory(partnum, pageId - 1, numData);
        return  jdbc.getResultTableList();
    }
}
