package calculation;

import pl.zankowski.iextrading4j.api.stocks.Quote;

public class EvaluationResponse {
    private Quote quote;
    private String intrinsicValue;
    private String intrinsicRation;

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public String getIntrinsicValue() {
        return intrinsicValue;
    }

    public void setIntrinsicValue(String intrinsicValue) {
        this.intrinsicValue = intrinsicValue;
    }

    public String getIntrinsicRation() {
        return intrinsicRation;
    }

    public void setIntrinsicRation(String intrinsicRation) {
        this.intrinsicRation = intrinsicRation;
    }
}
