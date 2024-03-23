package ru.itmo.mega.cinema.agregator.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DirectorRequest {

    @NotNull(message = "Field 'id' is required")
    private Long id;

    @NotBlank(message = "Field 'fio' is required")
    @Size(max = 100, message = "Field 'fio' should be less than 100")
    private String fio;

}
