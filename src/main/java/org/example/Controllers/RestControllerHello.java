package org.example.Controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class RestControllerHello {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
    public List<String> getData(){
        List<String> list = new ArrayList<>();
        list.add("Hello");
        return list;
    }
}
