public class Ln extends Log {
    public Ln(Expression arg) {
        super(new Num(Math.E), arg);
    }


    @Override
    protected Expression differentiateByRule(Expression baseDerivative, Expression argDerivative) {
        return new Div(argDerivative,this.getArg());
    }


}
