package app;

import calculation.EvaluationContext;
import calculation.EvaluationResponse;
import calculation.IntrinsicValue;
import clients.StockApi;
import clients.StockClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.zankowski.iextrading4j.api.stocks.Quote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IntrinsicValueImpl{
    Logger logger = LoggerFactory.getLogger(IntrinsicValueImpl.class);

    @RequestMapping("/stock")
    public List<EvaluationResponse> stock(@RequestParam(value="tickers") String[] tickers) {

        if(tickers == null && tickers.length > 0 ){
            logger.info("symbol cannot be null. Provide stock symbol to analyze");
            return null;
        }

        List<EvaluationResponse> responses = new ArrayList<>();

        for(String stock: tickers){
            StockClient stockClient;
            EvaluationContext context = new EvaluationContext();

            context.setTicker(stock);
            stockClient = new StockClient(StockApi.QUOTE);
            Quote quote = null;
            try {
                HttpResponse quoteResponse = stockClient.execute(stock);
                if(Integer.valueOf(200).equals(quoteResponse.getStatusCode())){
                    ObjectMapper mapper = new ObjectMapper();
                    quote = mapper.readValue(quoteResponse.getContent(), Quote.class);
                }
            } catch (IOException e) {
                logger.warn("Stock client failed to retrieve response");
                e.printStackTrace();
            }

            IntrinsicValue calculation = new IntrinsicValue();
            String intrinsicValue = calculation.execute(context);

            String valueRatio = String.valueOf(Double.valueOf(intrinsicValue)/quote.getLatestPrice().doubleValue());

            EvaluationResponse response = new EvaluationResponse();
            response.setQuote(quote);
            response.setIntrinsicValue(intrinsicValue);
            response.setIntrinsicRation(valueRatio);

            responses.add(response);
        }

        return responses;
    }



}
