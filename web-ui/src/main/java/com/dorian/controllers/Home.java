package com.dorian.controllers;

import com.dorian.session.UserSessionData;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/home/")
public class Home extends Master {

    @RequestMapping(value = "/display.do")
    public ModelAndView startUp(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String username = this.getUserNameFromContext();

        UserSessionData user = null;

        return this.drawClassic(request, response, user,
                new HashMap<String, String>(), "Inicio", "home");
    }
}
