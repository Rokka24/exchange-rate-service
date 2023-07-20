package ru.qiwi.exchangerateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.qiwi.exchangerateservice.converter.StringDateConverter;
import ru.qiwi.exchangerateservice.model.Request;
import ru.qiwi.exchangerateservice.parser.ResponseBodyParser;
import ru.qiwi.exchangerateservice.service.ExchangeService;

@SpringBootApplication
public class ExchangeRateServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(ExchangeRateServiceApplication.class, args);

        Request request = new Request("USD", "2022-03-02");
        Request request2 = new Request("CAD", "2022-03-02");

        ResponseBodyParser responseBodyParser = new ResponseBodyParser();
        StringDateConverter stringDateConverter = new StringDateConverter();
        ExchangeService exchangeService = new ExchangeService(responseBodyParser, stringDateConverter);

        exchangeService.sendRequest(request);
        exchangeService.sendRequest(request2);
    }

}
