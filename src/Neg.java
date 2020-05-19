public class Neg extends UnaryExpression {
    public Neg(Expression exp) {
        super(exp);
    }

    @Override
    protected double calculate(double value) {
        return value*(-1);
    }
//not sure
    @Override
    protected Expression differentiateByRule(Expression innerDerivative) {
        return new Neg(innerDerivative);
    }

    @Override
    protected String getSign() {
        return "-";
    }

    @Override
    protected Expression simplifyByRule(Expression simplifiedExp) {
        return new Neg(simplifiedExp);
    }
}

