public class Minus extends BinaryExpression {
    public Minus(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected double calculate(double leftValue, double rightValue) throws ArithmeticException {
        return leftValue-rightValue;
    }

    @Override
    protected Expression differentiateByRule(Expression leftDerivative, Expression rightDerivative) {
        return new Minus(leftDerivative, rightDerivative);
    }

    @Override
    protected String getSign() {
        return "-";
    }

    @Override
    protected Expression simplifyByRule(Expression leftSimplification, Expression rightSimplification) {
        if (BaseExpression.checkEqual(leftSimplification, rightSimplification)){
            return new Num(0);
        } else if (BaseExpression.checkEqualNum(leftSimplification,0)){
            return new Neg(rightSimplification);
        } else if (BaseExpression.checkEqualNum(rightSimplification,0)){
            return leftSimplification;
        } else {
            return new Minus(leftSimplification,rightSimplification);
        }
    }
}