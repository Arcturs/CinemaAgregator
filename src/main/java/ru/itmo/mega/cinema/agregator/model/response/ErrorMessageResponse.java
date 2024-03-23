package ru.itmo.mega.cinema.agregator.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageResponse {

    private int status;
    private String reason;

}
