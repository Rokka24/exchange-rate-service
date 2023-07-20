package ru.qiwi.exchangerateservice.sevice;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.qiwi.exchangerateservice.parser.ResponseBodyParser;
import ru.qiwi.exchangerateservice.model.Request;
import ru.qiwi.exchangerateservice.model.Response;


@Component
@Slf4j
@RequiredArgsConstructor
public class ExchangeService {

    private final ResponseBodyParser responseBodyParser;

    public Response sendRequest(Request request) {
        if (request.getCode().isEmpty() || request.getDate().isEmpty()) {
            log.warn("Необходимо указать параметры code и date");
            throw new IllegalArgumentException("Необходимо указать параметры code и date");
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + request.getDate();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        String responseBody = responseEntity.getBody();

        String rate = responseBodyParser.parseRate(request, responseBody);
        String currencyName = responseBodyParser.parseForeignCurrencyName(request, responseBody);
        System.out.println(request.getCode() + " (" + currencyName + ")" + ": " + rate);

        return new Response(currencyName, rate);
    }
}
