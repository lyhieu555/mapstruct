package com.hieudz.mapstruct.controller;

import com.hieudz.mapstruct.dto.TicketRequest;
import com.hieudz.mapstruct.dto.TicketResponse;
import com.hieudz.mapstruct.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<TicketResponse> getAllTicket(@RequestParam(required = false) String search) {
        return ticketService.getAllTicket(search);
    }

    @PostMapping
    public void save(@RequestBody TicketRequest ticketRequest) {
        ticketService.save(ticketRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        ticketService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        ticketService.deleteAll();
    }
}
