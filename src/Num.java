import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Num implements Expression {
    //Fields
    double val;

    //constructor
    public Num(double val) {
        // initialize number's numeric value
        this.val = val;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) {
        return this.val;
    }

    @Override
    public double evaluate() {
        return this.val;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public String toString() {
        if (Double.compare(val, Math.E) == 0) {
            return "e";
        } else if (Double.compare(val, Math.PI) == 0) {
            return "\u03c0";
        } else if (val % 1 == 0) {
            return String.format("%d", ((int) val));
        } else {
            return String.format("%.3f", val);
        }

    }
}
