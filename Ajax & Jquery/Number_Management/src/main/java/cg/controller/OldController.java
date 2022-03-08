package cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OldController {
    @GetMapping("")
    public String showIndex() {
        return "index";
    }
}
