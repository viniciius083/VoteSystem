package com.vinicius.vs.swagger;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class SwaggerController {
    @RequestMapping("/")
    public String getRedirectUrl() {
        return "redirect:swagger-ui/";
    }

}
