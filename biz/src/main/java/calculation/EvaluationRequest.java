package calculation;

import pl.zankowski.iextrading4j.api.stocks.Quote;

import java.math.BigDecimal;

public class EvaluationRequest {
    private static final int PE_NO_GROWTH = 7;
    private static final double AVG_AAA_BOND_YIELD = 4.4;
    private Quote quote;
    private BigDecimal eps;
    private BigDecimal growth7year;
    private BigDecimal currentAAABondYield;

    public static int getPeNoGrowth() {
        return PE_NO_GROWTH;
    }

    public static double getAvgAaaBondYield() {
        return AVG_AAA_BOND_YIELD;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public BigDecimal getEps() {
        return eps;
    }

    public void setEps(BigDecimal eps) {
        this.eps = eps;
    }

    public BigDecimal getGrowth7year() {
        return growth7year;
    }

    public void setGrowth7year(BigDecimal growth7year) {
        this.growth7year = growth7year;
    }

    public BigDecimal getCurrentAAABondYield() {
        return currentAAABondYield;
    }

    public void setCurrentAAABondYield(BigDecimal currentAAABondYield) {
        this.currentAAABondYield = currentAAABondYield;
    }
}
