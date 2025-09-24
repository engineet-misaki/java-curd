package com.store_sample.store.app.controller;

import com.store_sample.store.domain.hello.model.Hello;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

  @GetMapping("/hello")
  public Hello hello(@RequestParam("name") Optional<String> name) {

    String resName = name.orElse("world!");

    return new Hello("Hello, " + resName);
  }

  @Controller
  public static class HomeController {

    @RequestMapping("/")
    public String index() {
      return "index.html";
    }
  }
}
