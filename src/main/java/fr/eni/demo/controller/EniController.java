package fr.eni.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eniecole")
public class EniController {

    @GetMapping
    public String welcome() {
        return "welcome sur mon API";
    }
}
