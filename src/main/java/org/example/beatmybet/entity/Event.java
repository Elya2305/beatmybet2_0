package org.example.beatmybet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category")
    private Category category;

    @CreationTimestamp
    private LocalDateTime date;

    @ManyToOne
    private User user;

    private String title;

    private LocalDateTime dateStop;

    private LocalDateTime dateEnd;

//    private Long totalBid;

    @JsonBackReference
    @ToString.Exclude
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    List<Term> terms;

    public enum Order{
        date, popular
    }
}
