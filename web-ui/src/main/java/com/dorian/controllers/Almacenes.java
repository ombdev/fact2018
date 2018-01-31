/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dorian.controllers;

import com.dorian.session.UserSessionData;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes({"user"})
@RequestMapping("/almacenes/")
public class Almacenes extends Master {

    @RequestMapping(value = "/display.do")
    public ModelAndView display(HttpServletRequest request,
            HttpServletResponse response,
            @ModelAttribute("user") UserSessionData user) throws ServletException, IOException {

        HashMap<String,String> tableCaptions = new HashMap<String,String>();
        
        tableCaptions.put("id", "Acciones:90");
        tableCaptions.put("titulo", "Nombre:300");
        tableCaptions.put("tipo", "Tipo:110");
        tableCaptions.put("reporteo", "Rep.:30");
        tableCaptions.put("ventas", "Vta.:30");
        tableCaptions.put("compras", "Com.:30");
        tableCaptions.put("traspaso", "Tras.:30");
        tableCaptions.put("reabastecimiento", "Rea.:30");
        tableCaptions.put("garantias", "Gar.:30");
        tableCaptions.put("consignacion", "Con.:30");
        tableCaptions.put("recepcion_mat", "R.Mat.:30");
        tableCaptions.put("explosion_mat", "E.Mat.:30");
        
        return this.drawClassic(request, response, user, tableCaptions,
                "Catalogo de Almacenes", "almacenes");
    }
}
