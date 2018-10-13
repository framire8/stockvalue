package clients;

public enum StockApi {

    BATCH_REQUEST("/stock/%/batch"),
    BOOK("/stock/%s/book"),
    CHART("/stock/%s/chart/%s"),
    //COLLECTIONS("/stock/market/collection/{collectionType}"),
    COMPANY("/stock/%s/company"),
    //DIVIDENDS("/stock/%s/dividends/%s"),
    EARNINGS("/stock/%s/earnings"),
    EARNINGS_TODAY("/stock/market/today-earnings"),
    EFFECTIVE_SPREAD("/stock/%s/effective-spread"),
    FINANCIALS("/stock/%s/financials"),
    //HISTORICAL_PRICES,
    //IPO_CALENDAR,
    IEX_REGULATION_SHO_THRESHOLD("/stock/%s/threshold-securities"),
    IEX_SHORT_INTEREST_LIST("/stock/%s/short-interest"),
    KEY_STATS("/stock/%s/stats"),
    lARGEST_TRADES("/stock/%s/largest-trades"),
    //LIST("/stock/market/list/"),
    LOGO("/stock/%s/logo"),
    //NEWS("/stock/%s/news/last/{last}"),
    OPEN_CLOSE("/stock/%s/ohlc"),
    PEERS("/stock/%s/peers"),
    PREVIOUS("/stock/%s/previous"),
    PRICE("/stock/%s/price"),
    QUOTE("/stock/%s/quote"),
    RELEVANT("/stock/%s/relevant"),
    SECTOR_PERFORMANCE("/stock/market/sector-performance"),
    //SPLITS("/stock/%s/splits/%s"), //Stock && Range
    TIME_SERIES("/stock/%s/time-series"),
    VOLUME_BY_VENUE("/stock/%s/delayed-quote");

    private final String apiName;

    StockApi(String s) {
        apiName = s;
    }

    public String getApiName() {
        return apiName;
    }
}
