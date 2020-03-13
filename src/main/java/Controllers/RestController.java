package Controllers;

import Service.JDBCAnswer;
import Tables.ResultTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RestController {

    @GetMapping(value = "/db/{partnum,brandtypeId}")
    public @ResponseBody List<ResultTable> getData(
            @PathVariable Integer partnum, @PathVariable Integer brandtypeId){
        JDBCAnswer jdbcAnswer = new JDBCAnswer(brandtypeId, partnum);
        return jdbcAnswer.getResultTableList();
    }
}
