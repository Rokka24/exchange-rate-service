package ru.qiwi.exchangerateservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private String currencyName;
    private String rate;
}
