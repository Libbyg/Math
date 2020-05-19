public class Div extends BinaryExpression {
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected double calculate(double leftValue, double rightValue) throws ArithmeticException {
        if (rightValue == 0 && leftValue!=0)
            throw new ArithmeticException("Division by zero");
        return (leftValue/rightValue);
        //check left side?

    }

    @Override
    protected Expression differentiateByRule(Expression leftDerivative, Expression rightDerivative) {
        // (f'g-g'f)/g^2
        Expression numeratorDerivative = new Minus(new Mult(leftDerivative,this.getRight()),
                new Mult(rightDerivative,this.getLeft()));
        Expression denominatorDerivative = new Pow (this.getRight(),new Num(2));
        return new Div(numeratorDerivative, denominatorDerivative);
    }

    @Override
    protected String getSign() {
        return "/";
    }

    @Override
    protected Expression simplifyByRule(Expression leftSimplification, Expression rightSimplification) {
        if (checkEqualNum(leftSimplification,0)){
            return new Num(0);
        }else if (checkEqual(leftSimplification, rightSimplification)){
            return new Num(1);
        } else if (checkEqualNum(rightSimplification,1)){
            return leftSimplification;
        }else {
            return new Div(leftSimplification,rightSimplification);
        }
    }
}