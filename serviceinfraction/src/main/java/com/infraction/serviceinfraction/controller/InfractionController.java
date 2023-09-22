package com.infraction.serviceinfraction.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infraction.serviceinfraction.dto.InfractionDTO;
import com.infraction.serviceinfraction.entity.InfractionEntity;
import com.infraction.serviceinfraction.service.InfractionService;

@Controller
@RequestMapping("/infraction")
public class InfractionController {

    

    
    @Autowired
    private InfractionService infractionService;

    @GetMapping("/date/{date}")
    public ResponseEntity<List<InfractionEntity>> getInfractionByDate(@PathVariable("date") String dateString) {
        try {
            List<InfractionEntity> infractions = infractionService.getInfractionsInDate(dateString);
    
            return ResponseEntity.ok().body(infractions);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal server error
        }
    }

    
    

}