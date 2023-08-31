package com.trafficconsumer.trafficconsumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trafficconsumer.trafficconsumer.Repository.InfracaoRepository;
import com.trafficconsumer.trafficconsumer.model.InfracaoModel;

@Controller
public class InfracaoController {
    

    @Autowired
    private InfracaoRepository infracaoRepository;


   @GetMapping("/infracao")
    public ModelAndView getInfracao(Model model) {

        List<InfracaoModel> infracoes = infracaoRepository.findAll();
       
        model.addAttribute("infracoes", infracoes);
        ModelAndView modelAndView = new ModelAndView("infracao");
        modelAndView.addObject("model", model);
        return modelAndView;
    }

}
