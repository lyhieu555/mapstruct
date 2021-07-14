package com.hieudz.mapstruct.repository;

import com.hieudz.mapstruct.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT p FROM Ticket p WHERE " + "CONCAT(p.id, p.customerPhone, p.customerName, p.department, p.dayReception) " + "LIKE %?1%")
    List<Ticket> findByAll(String search);

    Optional<Ticket> findByCustomerPhone(String customerPhone);
}
