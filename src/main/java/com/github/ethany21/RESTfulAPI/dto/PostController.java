package com.github.ethany21.RESTfulAPI.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {

    @RequestMapping({"/", "/index"})
    public String indexPage(){
        return "homepage";
    }

    @GetMapping("/savePage")
    public String savePage(){
        return "Customer/addCustomer";
    }
}
