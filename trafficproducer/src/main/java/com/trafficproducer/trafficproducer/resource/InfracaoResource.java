package com.trafficproducer.trafficproducer.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trafficproducer.trafficproducer.service.InfracaoService;

@RestController
@RequestMapping("/infracao")
public class InfracaoResource {
    

    @Autowired
    InfracaoService infracaoService;

    @PostMapping
     public ResponseEntity<String> enviarMensagem(@RequestBody String mensagem){
        infracaoService.sendMessage(mensagem);
        return ResponseEntity.ok().body("Infração enviada com sucesso: " + mensagem);
    }

   
        
}
