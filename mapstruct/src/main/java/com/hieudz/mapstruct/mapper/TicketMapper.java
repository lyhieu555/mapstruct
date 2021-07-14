package com.hieudz.mapstruct.mapper;

import com.hieudz.mapstruct.dto.TicketRequest;
import com.hieudz.mapstruct.dto.TicketResponse;
import com.hieudz.mapstruct.entities.Department;
import com.hieudz.mapstruct.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    @Mapping(target = "department.id", source = "department.id")
    Ticket dtoToEntity(TicketRequest ticketRequest, Department department);

    @Mapping(target = "departmentName", source = "ticket.department.name")
    TicketResponse entityToDto(Ticket ticket);
}
