package ru.qiwi.exchangerateservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Request {

    private String code;
    private String date;
}
