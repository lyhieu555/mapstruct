package com.hieudz.mapstruct.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class DepartmentRequest{

    private Long id;

    private String name;

    private Instant createAt = Instant.now();
}
