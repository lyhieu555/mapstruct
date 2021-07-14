package com.hieudz.mapstruct.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerName;

    private String customerPhone;

    private String customerReview;

    private Instant dayReception;

    private String content;

    private Boolean status;

    private Date processTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_department", nullable = false)
    private Department department;
}
