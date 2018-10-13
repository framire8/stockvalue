package calculation;

@FunctionalInterface
public interface StockEvaluation {
    String execute(EvaluationContext context);
}
