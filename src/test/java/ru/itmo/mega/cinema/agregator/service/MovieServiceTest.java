package ru.itmo.mega.cinema.agregator.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itmo.mega.cinema.agregator.exception.EntityNotFoundException;
import ru.itmo.mega.cinema.agregator.model.entity.Movie;
import ru.itmo.mega.cinema.agregator.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    private static final Long ID = 1L;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private DirectorService directorService;

    @InjectMocks
    private MovieService movieService;

    @Test
    void getAllMoviesTest() {
        when(movieRepository.findAll()).thenReturn(List.of());

        assertDoesNotThrow(() -> movieService.getAllMovies());
    }

    @Test
    void getMovieByIdTest() {
        when(movieRepository.findById(ID)).thenReturn(Optional.of(new Movie()));

        assertDoesNotThrow(() -> movieService.getMovieById(ID));
    }

    @Test
    void getMovieByIdThrowsNotFoundExceptionTest() {
        when(movieRepository.findById(ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> movieService.getMovieById(ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

}