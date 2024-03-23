package ru.itmo.mega.cinema.agregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.mega.cinema.agregator.model.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
