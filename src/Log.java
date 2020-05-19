import java.util.Map;

public class Log extends BinaryExpression{
    public Log(Expression base, Expression arg) {
        super(base, arg);
    }

    @Override
    protected double calculate(double base, double arg) throws ArithmeticException {
        if (base>0 && base!=1 && arg>0){
            return logInBase(base, arg);
        }
        throw new ArithmeticException("log base/arg invalid");
    }

    @Override
    public Expression differentiate(String var) {
        boolean baseIsFunc = this.getLeft().getVariables().contains(var);
        if (baseIsFunc){
            return this.logDifferentiationHelp(var);
        }
        Expression rightDerivative = this.getRight().differentiate(var);
        Expression leftDerivative = this.getLeft().differentiate(var);
        return this.differentiateByRule(leftDerivative,rightDerivative);

    }

    public Expression logDifferentiationHelp(String var) {
        Expression newLeft, newRight, lnForm;
        newLeft = new Ln(this.getRight());
        newRight = new Ln(this.getLeft());
        lnForm = new Div(newLeft, newRight);
        return lnForm.differentiate(var);
    }

    //we know base is not a func of var
    @Override
    protected Expression differentiateByRule(Expression baseDerivative, Expression argDerivative) {
        return new Div(argDerivative,new Mult(this.getArg(),new Ln(this.getBase())));
    }

    //this function does not validates the base and arg
    public static double logInBase(double base, double arg){
        return Math.log(arg) / Math.log(base);
    }

    protected Expression getBase(){
        return this.getLeft();
    }

    protected Expression getArg(){
        return this.getRight();
    }

    @Override
    protected String getSign() {
        return "Log";
    }

    @Override
    public String toString() {
        if (this.getSign().equals("Log")){
            return this.getSign() + "(" + this.getBase() + "," + this.getArg()+")";
        }else {
            return this.getSign() + "(" +this.getArg()+")";
        }
    }

    @Override
    protected Expression simplifyByRule(Expression leftSimplification, Expression rightSimplification) {
        if (checkEqual(leftSimplification, rightSimplification)){
            return new Num(1);
        } else if (checkEqualNum(rightSimplification,1)){
            return new Num(0);
        }else {
            return new Log(leftSimplification,rightSimplification);
        }
    }
}
