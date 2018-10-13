package calculation;

import clients.StockApi;
import clients.StockClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.ArrayMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zankowski.iextrading4j.api.stocks.KeyStats;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Map;
import java.util.concurrent.TransferQueue;

import static java.lang.String.format;

public class IntrinsicValue implements StockEvaluation{
    Logger logger = LoggerFactory.getLogger(IntrinsicValue.class);
    /*
    V= the value expected from the growth formulas over the next 7 to 10 years
EPS= the company’s last 12-month earnings per share
8.5= P/E base for a no-growth company
g= reasonably expected 7 to 10 year growth rate
4.4= the average yield of AAA corporate bonds in 1962 (Graham did not specify the duration of the bonds, though it has been asserted that he used 20 year AAA bonds as his benchmark for this variable[4])
Y= the current yield on AAA corporate bonds.
     */

    private String value;
    private double eps;
    private static final double noGrowthPE = .085;
    private double growth;
    private static final double avgYieldAAABonds = 4.4;
    private static final double currentYieldAAABonds = .0399;

    private static final String TRAILING_TWELVE_MONTH_EPS = "ttmEPS";

    StockClient stockClient;

    @Override
    public String execute(EvaluationContext context ) {
        logger.info("Executing Instrinsic Value");
        stockClient = new StockClient(StockApi.KEY_STATS);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setMinimumIntegerDigits(2);

        Map<String, Object> stats = new ArrayMap<>();
        try {
            HttpResponse statsResponse = stockClient.execute(context.getTicker());
            if(Integer.valueOf(200).equals(statsResponse.getStatusCode())){
                ObjectMapper mapper = new ObjectMapper();
                stats = mapper.readValue(statsResponse.getContent(),
                        new TypeReference<Map<String,Object>>(){});
                logger.info(format("Map of the Key_Stats response: %s", stats.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(stats.containsKey(TRAILING_TWELVE_MONTH_EPS)) {
            eps = (double) stats.get(TRAILING_TWELVE_MONTH_EPS);
        }

        try {
            growth = getGrowth(context.getTicker());
        } catch (IOException e) {
            e.printStackTrace();
        }

        double doubleValue = (eps*(noGrowthPE+2*growth)*avgYieldAAABonds)/currentYieldAAABonds;

        return String.valueOf(doubleValue);
    }

    protected double getGrowth(String ticker) throws IOException {
        double growth = 0.0;
        Document doc = Jsoup.connect(format("https://finance.yahoo.com/quote/%s/analysis?p=%s", ticker, ticker)).get();
        logger.info(doc.title());
        Elements newsHeadlines = doc.select("tr:contains(Next 5 Years (per annum))");
        String growthStr = null;
        if(newsHeadlines != null && newsHeadlines.size() > 0) {
            growthStr = newsHeadlines.get(0).child(1).text().replaceAll("%", "");
            logger.info(format("Next 5 Year growth for %s: %s", ticker, growthStr));
        }

        if(growthStr != null){
            growth = Double.valueOf(growthStr) == 0.0 ? 0.0: Double.valueOf(growthStr)/100;
        }

        return growth;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getNoGrowthPE() {
        return noGrowthPE;
    }

    public double getGrowth() {
        return growth;
    }

    public void setGrowth(double growth) {
        this.growth = growth;
    }

    public double getAvgYieldAAABonds() {
        return avgYieldAAABonds;
    }

    public double getCurrentYieldAAABonds() {
        return currentYieldAAABonds;
    }
}
