package com.unla.primerproyecto.controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.unla.primerproyecto.helpers.ViewRouteHelper;
import com.unla.primerproyecto.models.DegreeModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/degree")
    public String degree (Model model) {
        model.addAttribute("degree", new DegreeModel());
        return ViewRouteHelper.DEGREE;
    }

    @PostMapping("/newdegree")
    public ModelAndView newdegree (@Valid @ModelAttribute("degree") DegreeModel degree, BindingResult bindingResult) {
        ModelAndView mV = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mV.setViewName(ViewRouteHelper.DEGREE);
        } else {
            mV.setViewName(ViewRouteHelper.NEW_DEGREE);
            mV.addObject("degree", degree);
        }
        return mV;
    }

}
