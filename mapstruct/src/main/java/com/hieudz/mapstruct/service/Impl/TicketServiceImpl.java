package com.hieudz.mapstruct.service.Impl;

import com.hieudz.mapstruct.dto.TicketRequest;
import com.hieudz.mapstruct.dto.TicketResponse;
import com.hieudz.mapstruct.entities.Department;
import com.hieudz.mapstruct.entities.Ticket;
import com.hieudz.mapstruct.exception.SpringException;
import com.hieudz.mapstruct.mapper.TicketMapper;
import com.hieudz.mapstruct.repository.DepartmentRepository;
import com.hieudz.mapstruct.repository.TicketRepository;
import com.hieudz.mapstruct.service.TicketService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger logger = Logger.getLogger(TicketServiceImpl.class);

    public static final String PHONE = "((09|03|07|08|05)+([0-9]{8})\\b)";

    private final DepartmentRepository departmentRepository;
    private final TicketMapper ticketMapper;
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(DepartmentRepository departmentRepository, TicketMapper ticketMapper, TicketRepository ticketRepository) {
        this.departmentRepository = departmentRepository;
        this.ticketMapper = ticketMapper;
        this.ticketRepository = ticketRepository;
    }


    @Override
    public List<TicketResponse> getAllTicket(String search) {

        if (search != null) {
            List<TicketResponse> ticketResponses = ticketRepository.findByAll(search)
                    .stream()
                    .map(ticketMapper::entityToDto)
                    .collect(Collectors.toList());
            if (ticketResponses.size() > 0) {
                logger.info("getAllTicket:: " + ticketResponses.size());
                return ticketResponses;
            } else {
                logger.info("getAllTicket:: null");
                return null;
            }
        }
        List<TicketResponse> ticketResponses = ticketRepository.findAll()
                .stream()
                .map(ticketMapper::entityToDto)
                .collect(Collectors.toList());
        if (ticketResponses.size() > 0) {
            logger.info("getAllTicket:: " + ticketResponses.size());
            return ticketResponses;
        } else {
            logger.info("getAllTicket:: null");
            return null;
        }
    }


    @Override
    public void save(TicketRequest ticketRequest) {
        Optional<Ticket> findByCustomerPhone = ticketRepository.findByCustomerPhone(ticketRequest.getCustomerPhone());

        Department department = departmentRepository.findByName(ticketRequest.getDepartmentName())
                .orElseThrow(() ->
                        new SpringException("Không tìm thấy tên phòng ban "
                                + ticketRequest.getDepartmentName()));

        Ticket ticket = new Ticket();

        ticket.setCustomerName(ticketRequest.getCustomerName());
        ticket.setCustomerPhone(ticketRequest.getCustomerPhone());
        ticket.setCustomerReview(ticketRequest.getCustomerReview());
        ticket.setDayReception(Instant.now());
        ticket.setContent(null);
        ticket.setStatus(false);
        ticket.setProcessTime(null);
        ticket.setDepartment(department);

        if(findByCustomerPhone.isPresent()){
            logger.error("Số điện thoại đã tồn tại !");
            throw new SpringException("Số điện thoại đã tồn tại !");
        } else if(!ticketRequest.getCustomerPhone().matches(PHONE)){
            logger.error("số điện thoại không hợp lệ !");
            throw new SpringException("số điện thoại không hợp lệ !");
        } else{
            ticketRepository.save(ticketMapper.dtoToEntity(ticketRequest, department));
        }
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        ticketRepository.deleteAll();
    }
}
