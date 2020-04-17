package org.example.Controllers;


import org.example.Service.JDBCAnswerUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/authorisation")
public class UserController {


    @GetMapping("/users")
    public List<String> GetAllUsers(){
        JDBCAnswerUser jdbcAnswerUser = new JDBCAnswerUser();
        return jdbcAnswerUser.getAllUsersList();
    }

    @GetMapping("/add/{login}/{password}")
    public String AddUser(@PathVariable("login") String login, @PathVariable("password") String password){
        JDBCAnswerUser jdbcAnswerUser = new JDBCAnswerUser();
        return jdbcAnswerUser.InsertUser(login, password);
    }

    @GetMapping("/find/{login}/{password}")
    public String AuthorisationUser(@PathVariable("login") String login, @PathVariable("password") String password){
        JDBCAnswerUser jdbcAnswerUser = new JDBCAnswerUser();
        return jdbcAnswerUser.FindUserByLoginAndPassword(login, password);
    }

    @DeleteMapping("/delete/{login}"., m)
    public ResponseEntity<HttpStatus> DeleteUser(@PathVariable("login") String login){
        try{
            JDBCAnswerUser jdbcAnswerUser = new JDBCAnswerUser();
            jdbcAnswerUser.DeleteUser(login);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

}
