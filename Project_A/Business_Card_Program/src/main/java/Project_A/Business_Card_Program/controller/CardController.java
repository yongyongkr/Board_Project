package Project_A.Business_Card_Program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardController {

    @GetMapping("create")
    public String create(Model model){
        model.addAttribute("data", "create");
        return "CreateTemp";
    }
}
