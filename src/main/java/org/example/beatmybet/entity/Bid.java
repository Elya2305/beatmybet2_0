package org.example.beatmybet.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Double coefficient;

    private BigDecimal balance;

//    private BigDecimal sold ??

    @ManyToOne
    private TermVariant termVariant;

    private Status status;

    public enum Status {
        ACTIVE, ARCHIVED
    }
}
