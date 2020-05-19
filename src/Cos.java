public class Cos extends UnaryExpression{
    public Cos(Expression exp) {
        super(exp);
    }

    @Override
    protected double calculate(double value) {
        return Math.cos(Math.toRadians(value));
    }

    @Override
    protected Expression differentiateByRule(Expression innerDerivative) {
        Mult outerDerivative = new Mult(new Sin(super.getExp()),new Num(-1));
         return (new Mult (outerDerivative,innerDerivative));

    }

    @Override
    protected String getSign() {
        return "cos";
    }

    @Override
    protected Expression simplifyByRule(Expression simplifiedExp) {
        if (checkEqualNum(simplifiedExp,0)){
            return new Num(1);
        }else if (checkEqualNum(simplifiedExp,90)){
            return new Num(0);
        }else {
            return new Cos(simplifiedExp);
        }
    }
}
