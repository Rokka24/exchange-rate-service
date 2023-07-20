package ru.qiwi.exchangerateservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.qiwi.exchangerateservice.model.Request;
import ru.qiwi.exchangerateservice.parser.ResponseBodyParser;
import ru.qiwi.exchangerateservice.service.ExchangeService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class ExchangeRateServiceApplicationTests {

    private static final Request EMPTY_REQUEST = new Request("", "");

    @Test
    void shouldThrowExceptionIfRequestIsEmpty() {
        ExchangeService requestSender = new ExchangeService(new ResponseBodyParser());

        assertThrows(IllegalArgumentException.class, () -> {
            requestSender.sendRequest(EMPTY_REQUEST);
        });
    }
}