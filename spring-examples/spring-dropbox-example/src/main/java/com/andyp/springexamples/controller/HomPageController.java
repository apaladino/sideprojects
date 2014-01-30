package com.andyp.springexamples.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Author: Andy Paladino Date: 9/6/13 Time: 10:44 AM
 */
@Controller
public class HomPageController
{

    @RequestMapping( value = "/home", method = GET)
    public ModelAndView loadHomePage(){
        ModelAndView mov = new ModelAndView();
        mov.setViewName("home");
        return mov;
    }

}
