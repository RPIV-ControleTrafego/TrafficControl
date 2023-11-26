package com.traffic.traffic.dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.traffic.traffic.dto.AccidentDTO;
import com.traffic.traffic.dto.AllTraficDTO;
import com.traffic.traffic.dto.InfractionDTO;
import com.traffic.traffic.dto.TrafficDto;
import com.traffic.traffic.entity.TrafficEntity;

@Mapper
public interface TrafficMapper {

    TrafficMapper INSTANCE = Mappers.getMapper(TrafficMapper.class);

    TrafficEntity mapCarDtoToEntity(TrafficDto trafficDto);

    TrafficDto mapCarEntityToDTO(TrafficEntity trafficEntity);

    InfractionDTO mapCarDtoToInfractionDTO(TrafficDto trafficDto);

    AllTraficDTO mapToAllTraficDTO(TrafficDto trafficDto, InfractionDTO infractionDto, AccidentDTO accidentDto);

    AccidentDTO mapAllTrafficToAccident(AllTraficDTO trafficInfo);
}