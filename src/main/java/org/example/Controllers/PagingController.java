package org.example.Controllers;


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
        JDBCAnswerResultPriceHistory jdbcAnswer = new JDBCAnswerResultPriceHistory(partnum);
        return  PagingDataResul(jdbcAnswer.getResultTableList(), pageId, numData);
    }

    private List<ResultTable> PagingDataResul(List<ResultTable> resultTables, int pageId, int numData){
        try {
            int length = resultTables.size();
            if (numData >= length)
                return resultTables;
            else {
                if(numData < (length - (pageId - 1) * numData))
                    return resultTables.subList((pageId - 1) * numData, pageId * numData);
                else
                    return resultTables.subList((pageId - 1) * numData, length);
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultTables;
    }
}
