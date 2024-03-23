package ru.itmo.mega.cinema.agregator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.mega.cinema.agregator.exception.EntityAlreadyExistsException;
import ru.itmo.mega.cinema.agregator.exception.EntityNotFoundException;
import ru.itmo.mega.cinema.agregator.exception.IdConflictException;
import ru.itmo.mega.cinema.agregator.model.entity.Director;
import ru.itmo.mega.cinema.agregator.model.request.DirectorRequest;
import ru.itmo.mega.cinema.agregator.model.response.GetAllDirectorsResponse;
import ru.itmo.mega.cinema.agregator.repository.DirectorRepository;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository repository;

    public GetAllDirectorsResponse getAllDirectors() {
        return new GetAllDirectorsResponse(repository.findAll());
    }

    public Director getDirectorById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Director with following id %s was not found".formatted(id)));
    }

    @Transactional
    public Director createDirector(DirectorRequest request) {
        var directorOpt = repository.findById(request.getId());
        if (directorOpt.isPresent()) {
            throw new EntityAlreadyExistsException(
                    "Director with following id %s already exists".formatted(request.getId()));
        }
        return repository.save(mapRequestToEntity(request));
    }

    @Transactional
    public Director updateDirector(Long id, DirectorRequest request) {
        var director = getDirectorById(id);
        if (!director.getId().equals(request.getId())) {
            throw new IdConflictException(
                    "Passed id %s and director's id %s are not equal".formatted(id, director.getId()));
        }
        return repository.save(mapRequestToEntity(request));
    }

    @Transactional
    public void deleteDirector(Long id) {
        var director = getDirectorById(id);
        repository.delete(director);
    }

    private static Director mapRequestToEntity(DirectorRequest request) {
        return new Director()
                .setId(request.getId())
                .setFio(request.getFio());
    }

}
