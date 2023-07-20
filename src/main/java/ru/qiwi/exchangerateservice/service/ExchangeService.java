package ru.qiwi.exchangerateservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.qiwi.exchangerateservice.converter.StringDateConverter;
import ru.qiwi.exchangerateservice.parser.ResponseBodyParser;
import ru.qiwi.exchangerateservice.model.Request;
import ru.qiwi.exchangerateservice.model.Response;


@Component
@Slf4j
@RequiredArgsConstructor
public class ExchangeService {

    private final ResponseBodyParser responseBodyParser;
    private final StringDateConverter stringDateConverter;

    public void sendRequest(Request request) {
        checkIfRequestIsEmpty(request);
        checkIfRequestCodeIsIncorrect(request);
        String date = stringDateConverter.convertDateFormat(request.getDate());

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        String responseBody = responseEntity.getBody();

        String rate = responseBodyParser.parseRate(request, responseBody);
        String currencyName = responseBodyParser.parseForeignCurrencyName(request, responseBody);
        Response response = new Response(currencyName, rate);

        printResponse(request, response);
    }

    private void printResponse(Request request, Response response) {
        System.out.printf("%s (%s): %s \n", request.getCode(), response.getCurrencyName(), response.getRate());
    }

    private void checkIfRequestIsEmpty(Request request) {
        if (request.getCode().isEmpty() || request.getDate().isEmpty()) {
            log.warn("code or date is empty in request - {}", request);
            throw new IllegalArgumentException("Необходимо указать параметры code и date");
        }
    }

    private void checkIfRequestCodeIsIncorrect(Request request) {
        String code = request.getCode();
        if (code.matches("^[A-Z]{3}$")) {
            log.warn("Incorrect code value in request - {}", request);
            throw new IllegalArgumentException("Code должен быть из трёх заглавных английских символов!");
        }
    }
}
