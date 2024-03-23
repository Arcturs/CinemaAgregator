package ru.itmo.mega.cinema.agregator.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalTime;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
public class Movie {

    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Long director;

    @Column(nullable = false)
    private LocalTime length;

    @Column(nullable = false)
    private Integer rating;

}
