package ch.webe.rollthedice.webeproject.controller;

import ch.webe.rollthedice.webeproject.model.AppUser;
import ch.webe.rollthedice.webeproject.model.Dice;
import ch.webe.rollthedice.webeproject.model.User;
import ch.webe.rollthedice.webeproject.services.AppUserService;
import ch.webe.rollthedice.webeproject.services.DiceService;
import ch.webe.rollthedice.webeproject.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UserSessionController {

    User user;
    AppUserService appUserService;

    @Autowired
    public UserSessionController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/account")
    public ModelAndView viewUserAccountForm(@AuthenticationPrincipal AppUser appUser, Model model){
        String userEmail = appUser.getEmail();
        AppUser user = appUser;

        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Account Details");

        return new ModelAndView("lobby");
    }

/*
    @CrossOrigin(origins = "*")
    @GetMapping("/account1")
    public String getAccountInformation1(Model model){
        model.addAttribute("user", new User());
        return "lobby";
    }
*/

    @CrossOrigin(origins = "localhost:8084/account")
    @GetMapping("/account1")
    public String getAccountInformation(Model model){
        User currentUser = this.user;
        System.out.println("++++++++++++++" + currentUser.getEmail() + "-- " + currentUser.getId());
        model.addAttribute("user", currentUser);
        return "lobby";
    }

    @CrossOrigin(origins = "localhost:8084/account")
    @PostMapping("/account1")
    public String getAccountInformation2(@RequestBody User user, Model model){
        this.user = new User(user.getFirstname(), user.getLastname(), user.getEmail());
        System.out.println(">>>>>>>>>>>>>>>>>>>" + user.getEmail() + "-- " + model);
        model.addAttribute("user", user);
        System.out.println(user.getFirstname());
        return "lobby";
    }

    @CrossOrigin(origins = "localhost:8084/")
    @GetMapping("/account/{email}")
    public ResponseEntity<AppUser> getUserInformation(@PathVariable String email){

        return appUserService.getByEmail(email);
    }


}
