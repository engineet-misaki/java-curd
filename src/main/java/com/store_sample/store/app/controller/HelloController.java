package com.store_sample.store.app.controller;

import com.store_sample.store.domain.hello.model.Hello;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Hello hello (@RequestParam("name") Optional<String> name) {

        String resName = name.orElse("world!");

        return new Hello("Hello, " + resName);
    }
}
