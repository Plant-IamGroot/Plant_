package Plant.IamGroot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/ex")
    public String hello(){
        return "thymeleafEx/thymeleafEx07";
    }

}
