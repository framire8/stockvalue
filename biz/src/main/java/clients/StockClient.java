package clients;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

import static java.lang.String.format;

public class StockClient {
    Logger logger = LoggerFactory.getLogger(StockClient.class);

    private static final String BASE_URL = "https://api.iextrading.com/1.0/%s";
    private String url;

    public StockClient(StockApi apiOperation) {
        url = format(BASE_URL, apiOperation.getApiName());
    }

    public HttpResponse execute(String ticker) throws IOException {
        HttpRequestFactory requestFactory
                = new NetHttpTransport().createRequestFactory();
        HttpRequest request;
        HttpResponse response = null;

        if(ticker != null && !ticker.isEmpty()){
            request = requestFactory.buildGetRequest(
                    new GenericUrl(format(url, ticker)));

            response = request.execute();
        }

        return response;
    }


}
