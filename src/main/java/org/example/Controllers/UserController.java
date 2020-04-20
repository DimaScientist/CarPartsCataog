package org.example.Controllers;


import org.example.Service.JDBCAnswerUser;
import org.example.Tables.Answer;
import org.example.Tables.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/authorisation")
public class UserController {

    // добавить токен

    @GetMapping("/users")
    public List<User> GetAllUsers(){
        JDBCAnswerUser jdbcAnswerUser = new JDBCAnswerUser();
        return jdbcAnswerUser.getAllUsersList();
    }

    @GetMapping("/add/{login}/{password}/{lastname}/{firstname}")
    public String AddUser(@PathVariable("login") String login, @PathVariable("password") String password,
                          @PathVariable("lastname") String lastname, @PathVariable("firstname") String firstname){
        JDBCAnswerUser jdbcAnswerUser = new JDBCAnswerUser();
        return jdbcAnswerUser.InsertUser(login, password, firstname, lastname);
    }

    @GetMapping("/user/{login}")
    public List<User> GetUserByLogin(@PathVariable("login") String login){
        JDBCAnswerUser jdbcAnswerUser = new JDBCAnswerUser();
        return jdbcAnswerUser.FindUserByLogin(login);
    }

    @GetMapping("/find/{login}/{password}")
    // выдать юзера с токеном
    // { status: 200, body }
    public Answer AuthorisationUser(@PathVariable("login") String login, @PathVariable("password") String password){
        try {
            JDBCAnswerUser jdbcAnswerUser = new JDBCAnswerUser();
            User user = jdbcAnswerUser.FindUserByLoginAndPassword(login, password);
            return new Answer(200, user);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return new Answer(200, null);
        }

    }

    @GetMapping("/delete/{login}")
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
