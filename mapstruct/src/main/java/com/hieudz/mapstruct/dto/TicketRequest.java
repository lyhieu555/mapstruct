package com.hieudz.mapstruct.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;


@Getter
@Setter
public class TicketRequest{

    private String customerName;

    private String customerPhone;

    private String customerReview;

    private String departmentName;

    private Instant dayReception = Instant.now();

    private String content;

    private Boolean status = false;

    private Date processTime;
}
