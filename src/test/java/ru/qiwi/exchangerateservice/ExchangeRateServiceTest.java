package ru.qiwi.exchangerateservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.qiwi.exchangerateservice.converter.StringDateConverter;
import ru.qiwi.exchangerateservice.model.Request;
import ru.qiwi.exchangerateservice.parser.ResponseBodyParser;
import ru.qiwi.exchangerateservice.service.ExchangeService;


import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class ExchangeRateServiceTest {

    private static final Request EMPTY_REQUEST = new Request("", "");
    private static final Request REQUEST_WITH_INCORRECT_DATE = new Request("USD", "02/03/2022");

    @Test
    void shouldThrowExceptionIfRequestIsEmpty() {
        ExchangeService requestSender = new ExchangeService(new ResponseBodyParser(), new StringDateConverter());

        assertThrows(IllegalArgumentException.class, () -> requestSender.sendRequest(EMPTY_REQUEST));
    }

    @Test
    void shouldThrowExceptionIfRequestDateIsIncorrect() {
        ExchangeService requestSender = new ExchangeService(new ResponseBodyParser(), new StringDateConverter());

        assertThrows(IllegalArgumentException.class, () -> requestSender.sendRequest(REQUEST_WITH_INCORRECT_DATE));
    }
}