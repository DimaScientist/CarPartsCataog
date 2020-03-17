package Controllers;

import Service.JDBCAnswerResultPriceHistory;
import Tables.ResultTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RestControllerPriceHistory {

    @GetMapping(value = "/db/{partnum,brandtypeId}")
    public @ResponseBody List<ResultTable> getData(
            @PathVariable Integer partnum, @PathVariable Integer brandtypeId){
        JDBCAnswerResultPriceHistory jdbcAnswer = new JDBCAnswerResultPriceHistory(brandtypeId, partnum);
        return jdbcAnswer.getResultTableList();
    }
}
