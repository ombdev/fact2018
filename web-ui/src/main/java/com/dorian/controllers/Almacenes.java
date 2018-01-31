/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dorian.controllers;

import com.dorian.session.UserSessionData;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


public class Almacenes extends Master{

    @RequestMapping(value = "/display.do")
    public ModelAndView display(HttpServletRequest request,
            HttpServletResponse response,
            @ModelAttribute("user") UserSessionData user) throws ServletException, IOException {
        return super.drawClassic(request, response, user);
    }
}
