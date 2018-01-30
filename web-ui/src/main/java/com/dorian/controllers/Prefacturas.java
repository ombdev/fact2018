package com.dorian.controllers;

import com.dorian.db.Cfdi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes({"user"})
@RequestMapping("/prefacturas/")
public class Prefacturas {

    @Autowired
    @Qualifier("db_cfdi")
    private Cfdi dbCfdi;  

    
}
