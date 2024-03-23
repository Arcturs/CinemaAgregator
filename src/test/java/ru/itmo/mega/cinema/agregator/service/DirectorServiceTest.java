package ru.itmo.mega.cinema.agregator.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itmo.mega.cinema.agregator.exception.EntityAlreadyExistsException;
import ru.itmo.mega.cinema.agregator.exception.EntityNotFoundException;
import ru.itmo.mega.cinema.agregator.exception.IdConflictException;
import ru.itmo.mega.cinema.agregator.model.entity.Director;
import ru.itmo.mega.cinema.agregator.model.request.DirectorRequest;
import ru.itmo.mega.cinema.agregator.repository.DirectorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DirectorServiceTest {

    private static final Long ID = 1L;
    private static final String FIO = "FIO";
    private static final String PREVIOUS_FIO = "prevFIO";

    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private DirectorService directorService;

    @Test
    void getAllDirectorsTest() {
        when(directorRepository.findAll()).thenReturn(List.of());

        assertDoesNotThrow(() -> directorService.getAllDirectors());
    }

    @Test
    void getDirectorTest() {
        when(directorRepository.findById(ID)).thenReturn(Optional.of(createEntity()));

        assertDoesNotThrow(() -> directorService.getDirectorById(ID));
    }

    @Test
    void getDirectorThrowsNotFoundExceptionTest() {
        when(directorRepository.findById(ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> directorService.getDirectorById(ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void createDirectorTest() {
        var request = createRequest();
        var entity = createEntity();
        when(directorRepository.findById(ID)).thenReturn(Optional.empty());
        when(directorRepository.save(entity)).thenReturn(entity);

        var result = directorService.createDirector(request);

        assertNotNull(result);
        assertEquals(FIO, result.getFio());
        assertEquals(ID, result.getId());
    }

    @Test
    void createDirectorThrowsEntityAlreadyExistsTest() {
        var request = createRequest();
        when(directorRepository.findById(ID)).thenReturn(Optional.of(new Director()));

        assertThatThrownBy(() -> directorService.createDirector(request))
                .isInstanceOf(EntityAlreadyExistsException.class);
    }

    @Test
    void updateDirectorTest() {
        var request = createRequest();
        var entity = createEntity();
        var prevEntity = new Director()
                .setId(ID)
                .setFio(PREVIOUS_FIO);
        when(directorRepository.findById(ID)).thenReturn(Optional.of(prevEntity));
        when(directorRepository.save(entity)).thenReturn(entity);

        var result = directorService.updateDirector(ID, request);

        assertNotNull(result);
        assertEquals(FIO, result.getFio());
        assertEquals(ID, result.getId());
    }

    @Test
    void updateDirectorThrowsNotFoundExceptionTest() {
        var request = createRequest();
        when(directorRepository.findById(ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> directorService.updateDirector(ID, request))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void updateDirectorThrowsIdConflictExceptionTest() {
        var request = createRequest()
                .setId(10L);
        var prevEntity = new Director()
                .setId(ID)
                .setFio(PREVIOUS_FIO);
        when(directorRepository.findById(ID)).thenReturn(Optional.of(prevEntity));

        assertThatThrownBy(() -> directorService.updateDirector(ID, request))
                .isInstanceOf(IdConflictException.class);
    }

    @Test
    void deleteDirectorTest() {
        var entity = createEntity();
        when(directorRepository.findById(ID)).thenReturn(Optional.of(entity));
        doNothing().when(directorRepository).delete(entity);

        assertDoesNotThrow(() -> directorService.deleteDirector(ID));
    }

    @Test
    void deleteDirectorThrowsNotFoundExceptionTest() {
        when(directorRepository.findById(ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> directorService.deleteDirector(ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

    private static Director createEntity() {
        return new Director()
                .setId(ID)
                .setFio(FIO);
    }

    private static DirectorRequest createRequest() {
        return new DirectorRequest()
                .setId(ID)
                .setFio(FIO);
    }

}