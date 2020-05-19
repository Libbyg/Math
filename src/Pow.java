public class Pow extends BinaryExpression{
    public Pow(Expression base, Expression exponent) {
        super(base, exponent);
    }

    @Override
    protected double calculate(double base, double exponent) throws ArithmeticException {
        return Math.pow(base, exponent);
    }

    @Override
    protected Expression differentiateByRule(Expression baseDerivative, Expression expDerivative) {
        return new Mult(this,new Plus(
                new Mult(baseDerivative,new Div(this.exponent(),this.base())),
                new Mult(expDerivative,new Ln(this.base()))));
    }

    private Expression exponent(){
        return this.getRight();
    }

    private Expression base(){
        return this.getLeft();
    }

    @Override
    protected String getSign() {
        return "^";
    }

    @Override
    protected Expression simplifyByRule(Expression baseSimplification, Expression argSimplification) {
        if (checkEqualNum(baseSimplification,0)){
            return new Num(0);
        } else if (checkEqualNum(baseSimplification,1)){
            return new Num(1);
        } else if (checkEqualNum(argSimplification,0)){
            return new Num(1);
        } else if (checkEqualNum(argSimplification,1)){
            return baseSimplification;
        }else {
            return new Pow(baseSimplification,argSimplification);
        }
    }
}
