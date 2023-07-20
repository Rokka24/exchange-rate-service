package ru.qiwi.exchangerateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.qiwi.exchangerateservice.model.Request;
import ru.qiwi.exchangerateservice.parser.ResponseBodyParser;
import ru.qiwi.exchangerateservice.service.ExchangeService;

@SpringBootApplication
public class ExchangeRateServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(ExchangeRateServiceApplication.class, args);

        Request request = new Request("USD", "02/03/2022");
        Request request2 = new Request("CAD", "02/03/2022");

        ResponseBodyParser responseBodyParser = new ResponseBodyParser();
        ExchangeService exchangeService = new ExchangeService(responseBodyParser);

        exchangeService.sendRequest(request);
        exchangeService.sendRequest(request2);
    }

}
