public class Sin extends UnaryExpression{
    public Sin(Expression exp) {
        super(exp);
    }

    @Override
    protected double calculate(double value) {
        return Math.sin(Math.toRadians(value));
    }

    @Override
    protected Expression differentiateByRule(Expression innerDerivative) {
        return new Mult(new Cos(super.getExp()),innerDerivative);
    }

    @Override
    protected String getSign() {
        return "sin";
    }

    @Override
    protected Expression simplifyByRule(Expression simplifiedExp) {
        if (checkEqualNum(simplifiedExp,0)){
            return new Num(0);
        }else if (checkEqualNum(simplifiedExp,90)){
            return new Num(1);
        }else {
            return new Sin(simplifiedExp);
        }
    }
}
