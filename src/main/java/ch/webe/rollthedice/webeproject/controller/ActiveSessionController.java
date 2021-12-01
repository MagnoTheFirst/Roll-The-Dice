package ch.webe.rollthedice.webeproject.controller;

import ch.webe.rollthedice.webeproject.model.Session;
import ch.webe.rollthedice.webeproject.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ActiveSessionController {

    SessionService sessionService;

    @Autowired
    public ActiveSessionController(SessionService sessionService){
        this.sessionService = sessionService;
    }

    //TODO[] activeSessions mit getActiveSessions anpassen
    @GetMapping("/api/v1/activeSessions")
    public ArrayList<Session> getActiveSessions(){
        return sessionService.getActiveSessions();
    }



}
