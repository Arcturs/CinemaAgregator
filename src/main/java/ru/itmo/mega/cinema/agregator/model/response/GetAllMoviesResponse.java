package ru.itmo.mega.cinema.agregator.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.mega.cinema.agregator.model.entity.Movie;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllMoviesResponse {

    private List<Movie> list;

}
