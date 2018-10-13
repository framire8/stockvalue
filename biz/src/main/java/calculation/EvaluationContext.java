package calculation;

public class EvaluationContext {
    private String ticker;
    private EvaluationRequest request;
    private EvaluationResponse response;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public EvaluationRequest getRequest() {
        return request;
    }

    public void setRequest(EvaluationRequest request) {
        this.request = request;
    }

    public EvaluationResponse getResponse() {
        return response;
    }

    public void setResponse(EvaluationResponse response) {
        this.response = response;
    }
}
