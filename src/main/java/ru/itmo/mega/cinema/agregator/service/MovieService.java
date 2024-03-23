package ru.itmo.mega.cinema.agregator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.mega.cinema.agregator.exception.EntityAlreadyExistsException;
import ru.itmo.mega.cinema.agregator.exception.EntityNotFoundException;
import ru.itmo.mega.cinema.agregator.exception.IdConflictException;
import ru.itmo.mega.cinema.agregator.model.entity.Movie;
import ru.itmo.mega.cinema.agregator.model.request.MovieRequest;
import ru.itmo.mega.cinema.agregator.model.response.GetAllMoviesResponse;
import ru.itmo.mega.cinema.agregator.repository.MovieRepository;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
    private final DirectorService directorService;

    public GetAllMoviesResponse getAllMovies() {
        return new GetAllMoviesResponse(repository.findAll());
    }

    public Movie getMovieById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Movie with following id %s was not found".formatted(id)));
    }

    @Transactional
    public Movie createMovie(MovieRequest request) {
        var movieOpt = repository.findById(request.getId());
        if (movieOpt.isPresent()) {
            throw new EntityAlreadyExistsException(
                    "Movie with following id %s already exists".formatted(request.getId()));
        }
        var director = directorService.getDirectorById(request.getDirector());
        return repository.save(mapRequestToEntity(request));
    }

    @Transactional
    public Movie updateMovie(Long id, MovieRequest request) {
        var movie = getMovieById(id);
        if (!movie.getId().equals(request.getId())) {
            throw new IdConflictException(
                    "Passed id %s and movie's id %s are not equal".formatted(id, movie.getId()));
        }
        var director = directorService.getDirectorById(request.getDirector());
        return repository.save(mapRequestToEntity(request));
    }

    @Transactional
    public void deleteMovie(Long id) {
        var movie = getMovieById(id);
        repository.delete(movie);
    }

    private static Movie mapRequestToEntity(MovieRequest request) {
        return new Movie()
                .setId(request.getId())
                .setYear(request.getYear())
                .setDirector(request.getDirector())
                .setTitle(request.getTitle())
                .setRating(request.getRating())
                .setLength(request.getLength());
    }

}
