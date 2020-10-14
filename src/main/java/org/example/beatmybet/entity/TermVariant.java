package org.example.beatmybet.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TermVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Term term;

    private String title;
}
