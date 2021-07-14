package com.hieudz.mapstruct.service;

import com.hieudz.mapstruct.dto.TicketRequest;
import com.hieudz.mapstruct.dto.TicketResponse;

import java.util.List;


public interface TicketService {

    List<TicketResponse> getAllTicket(String search);

    void save(TicketRequest ticketRequest);

    void deleteById(Long id);

    void deleteAll();
}
