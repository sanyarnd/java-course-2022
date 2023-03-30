package com.example.demo.controller;

//import com.example.demo.api.HelloApi;
//import com.example.model.Hello;
import com.example.demo.api.HelloApi;
import com.example.model.Hello;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/contract-first")
@RestController
public class HelloController implements HelloApi {

    @Override
    public ResponseEntity<Hello> hello() {
        var response = new Hello();
        response.setText("Hello from contsdgshdfhjdtjher erhsthdfgh  eract-first project");
        return ResponseEntity.ok(response);
    }
}
