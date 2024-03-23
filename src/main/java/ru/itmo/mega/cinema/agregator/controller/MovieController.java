package ru.itmo.mega.cinema.agregator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.mega.cinema.agregator.model.request.MovieRequest;
import ru.itmo.mega.cinema.agregator.service.MovieService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
@Tag(name = "Movie Controller")
public class MovieController {
    
    private final MovieService movieService;

    @GetMapping("")
    @Operation(operationId = "getAllMovies", summary = "Получение всех фильмов")
    public ResponseEntity<?> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getMovie", summary = "Получение фильма по его ИД")
    public ResponseEntity<?> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping("")
    @Operation(operationId = "createMovie", summary = "Создание фильма")
    public ResponseEntity<?> createMovie(@RequestBody @Valid MovieRequest request) {
        return ResponseEntity.ok(movieService.createMovie(request));
    }


    @PatchMapping("/{id}")
    @Operation(operationId = "updateMovie", summary = "Обновление фильма по его ИД")
    public ResponseEntity<?> updateMovie(@PathVariable Long id,
                                            @RequestBody @Valid MovieRequest request) {
        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(operationId = "deleteMovie", summary = "Удаление фильма по его ИД")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.accepted().build();
    }
    
}
