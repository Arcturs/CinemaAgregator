package ru.itmo.mega.cinema.agregator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.mega.cinema.agregator.model.request.DirectorRequest;
import ru.itmo.mega.cinema.agregator.service.DirectorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/directors")
@Tag(name = "Director Controller")
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping("")
    @Operation(operationId = "getAllDirectors", summary = "Получение всех режиссеров")
    public ResponseEntity<?> getAllDirectors() {
        return ResponseEntity.ok(directorService.getAllDirectors());
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getDirector", summary = "Получение режиссера по его ИД")
    public ResponseEntity<?> getDirector(@PathVariable Long id) {
        return ResponseEntity.ok(directorService.getDirectorById(id));
    }

    @PostMapping("")
    @Operation(operationId = "createDirector", summary = "Создание режиссера")
    public ResponseEntity<?> createDirector(@RequestBody @Valid DirectorRequest request) {
        return ResponseEntity.ok(directorService.createDirector(request));
    }


    @PatchMapping("/{id}")
    @Operation(operationId = "updateDirector", summary = "Обновление режиссера по его ИД")
    public ResponseEntity<?> updateDirector(@PathVariable Long id,
                                            @RequestBody @Valid DirectorRequest request) {
        return ResponseEntity.ok(directorService.updateDirector(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(operationId = "deleteDirector", summary = "Удаление режиссера по его ИД")
    public ResponseEntity<?> deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
        return ResponseEntity.accepted().build();
    }

}
