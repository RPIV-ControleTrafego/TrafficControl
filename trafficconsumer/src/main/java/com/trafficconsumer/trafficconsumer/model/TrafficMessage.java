package com.trafficconsumer.trafficconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrafficMessage {
    private String timestamp;
  
    private String violationType;
    private String location;
    private int fineAmount;
}
