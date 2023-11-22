package com.oneteam.dormease;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@Controller
public class HomeController {
    @RequestMapping(value = {"", "/"})
    public String home() {
        log.info("home");

        return "Home";
    }
}
