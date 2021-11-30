package ch.webe.rollthedice.webeproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TwoDiceController {

    @GetMapping("/api/v1/2dice")
    public String getTwoDice(Model model){
        return "two-dice-match";
    }


}
