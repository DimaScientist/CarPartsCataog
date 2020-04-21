package org.example.Controllers;


import org.example.Service.JDBCAnswerUser;
import org.example.Tables.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/authorisation")
public class UserController {

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
    
    @GetMapping(value = "/find/{login}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    // выдать юзера с токеном
    public User AuthorisationUser(@PathVariable("login") String login, @PathVariable("password") String password){

        User user = new User();
        try {
            JDBCAnswerUser jdbcAnswerUser = new JDBCAnswerUser();

            user = jdbcAnswerUser.FindUserByLoginAndPassword(login, password);

        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return user;
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
