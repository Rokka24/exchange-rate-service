package ru.qiwi.exchangerateservice.parser;

import org.springframework.stereotype.Component;
import ru.qiwi.exchangerateservice.model.Request;

@Component
public class ResponseBodyParser {

    private static final int SIZE_OF_RATE = 7;
    private static final int NAME_PREFIX = 6;

    public String parseRate(Request request, String responseBody) {
        if (responseBody != null) {
            int startIndex = responseBody.indexOf("<CharCode>" + request.getCode() + "</CharCode>");
            int endIndex = responseBody.indexOf("</Value>", startIndex);
            return responseBody.substring(endIndex - SIZE_OF_RATE, endIndex);
        } else {
            throw new IllegalArgumentException("ResponseBody is null");
        }
    }

    public String parseForeignCurrencyName(Request request, String responseBody) {
        if (responseBody != null) {
            int startIndex = responseBody.indexOf("<CharCode>" + request.getCode() + "</CharCode>");
            int nameStartIndex = responseBody.indexOf("<Name>", startIndex);
            int endIndex = responseBody.indexOf("</Name>", startIndex);

            return responseBody.substring(nameStartIndex + NAME_PREFIX, endIndex);
        } else {
            throw new IllegalArgumentException("ResponseBody is null");
        }
    }
}
