package ru.itmo.mega.cinema.agregator.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Table(name = "director")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
public class Director {

    @Id
    private Long id;

    @Column(nullable = false)
    private String fio;

}
