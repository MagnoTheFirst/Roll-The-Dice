package ch.webe.rollthedice.webeproject.controller;

import ch.webe.rollthedice.webeproject.model.Session;
import ch.webe.rollthedice.webeproject.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class ActiveSessionController {

    ArrayList<Session> activeSessions;

    @Autowired
    public ActiveSessionController(SessionService sessionService){
        this.activeSessions = new ArrayList<Session>();
    }





}
