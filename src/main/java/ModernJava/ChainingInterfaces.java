package ModernJava;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ChainingInterfaces {

    public static void main(String[] args) {
        List<Function<Double, Double>> functions = new ArrayList<>();
        functions.add(x -> x * .12);
        functions.add(x -> x * .93);


        Double nextYear = getBonusCalculator()
                .startAction(124_080.00)
                .midAction(functions)
                .get();

        Double thisYearBonus = getBonusCalculator()
                .startAction(56_000.00)
                .midAction(functions)
                .get();

        System.out.println(nextYear);
        System.out.println(thisYearBonus);
    }

    public static InitialValue getBonusCalculator() {
        return startValue -> {
            return (List<Function<Double, Double>> consumers) -> {
                double intermediateValue = startValue;
                for (Function<Double, Double> consumer : consumers)
                    intermediateValue = consumer.apply(intermediateValue);
                double finalValue = intermediateValue;
                return () -> finalValue;
            };
        };
    }

    @FunctionalInterface
    public interface InitialValue {
        MiddleAction startAction(double start);
    }

    @FunctionalInterface
    public interface MiddleAction {
        Supplier<Double> midAction(List<Function<Double, Double>> functions);
    }

    @FunctionalInterface
    public interface TerminalAction {
        double endAction();
    }
}
