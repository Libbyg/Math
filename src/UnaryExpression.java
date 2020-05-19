import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

abstract public class UnaryExpression extends BaseExpression{
    private Expression exp;

    public UnaryExpression(Expression exp) {
        this.exp = exp;
    }

    public Expression getExp() {
        return exp;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws InvalidAssignmentException {
        double value = this.exp.evaluate(assignment);
        return this.calculate(value);
    }

    protected abstract double calculate(double value);

    @Override
    public List<String> getVariables() {
        return new ArrayList<>(new HashSet<>(this.exp.getVariables()));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        this.exp = this.exp.assign(var, expression);
        return this;
    }

    @Override
    public Expression differentiate(String var) {
        Expression innerDerivative = this.exp.differentiate(var);
        return this.differentiateByRule(innerDerivative);
    }

    protected abstract Expression differentiateByRule(Expression innerDerivative);

    @Override
    public String toString() {
        return this.getSign() + "(" + this.exp.toString() + ")";
    }

    @Override
    public Expression simplify() {
        if (onlyNums(this)){
            return turnToNum(this);
        }
        Expression simplifiedExp = this.exp.simplify();
        return this.simplifyByRule(simplifiedExp);
    }

    protected abstract Expression simplifyByRule(Expression simplifiedExp);
}
