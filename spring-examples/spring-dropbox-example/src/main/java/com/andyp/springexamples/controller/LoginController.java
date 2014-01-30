package com.andyp.springexamples.controller;

import java.lang.String;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Author: Andy Paladino Date: 8/31/13 Time: 8:54 PM
 */
@Controller
public class LoginController {


    @RequestMapping(value="/login", method = GET)
    public ModelAndView handleLogin(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

}
