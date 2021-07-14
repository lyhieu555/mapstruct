package com.hieudz.mapstruct.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class TicketResponse{

    private Long id;

    private String customerName;

    private String customerPhone;

    private String customerReview;

    private Instant dayReception;

    private String content;

    private Boolean status;

    private Date processTime;

    private String departmentName;
}
