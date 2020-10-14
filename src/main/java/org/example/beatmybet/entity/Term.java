package org.example.beatmybet.entity;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(exclude = {"variants"})
@Entity
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String title;

    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL)
    private List<TermVariant> variants;
}
