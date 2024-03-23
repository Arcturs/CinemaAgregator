package ru.itmo.mega.cinema.agregator.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {

    @NotNull(message = "Field 'id' is required")
    private Long id;

    @NotBlank(message = "Field 'title' is required")
    @Size(max = 100, message = "Field 'title' should be less than 100")
    private String title;

    @NotNull(message = "Field 'year' is required")
    @Max(value = 2100, message = "Field 'year' should be less than 2100")
    @Min(value = 1900, message = "Field 'year' should be more than 1900")
    private Integer year;

    @NotNull(message = "Field 'director' is required")
    private Long director;

    @NotNull(message = "Field 'length' is required")
    private LocalTime length;

    @NotNull(message = "Field 'rating' is required")
    @Max(value = 10, message = "Field 'rating' should be less than 2100")
    @Min(value = 0, message = "Field 'rating' should be more than 1900")
    private Integer rating;

}
