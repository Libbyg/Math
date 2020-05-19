public class Plus extends BinaryExpression {
    public Plus(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    protected double calculate(double leftValue, double rightValue) {
        return leftValue + rightValue;
    }

    @Override
    protected Expression differentiateByRule(Expression leftDerivative, Expression rightDerivative) {
        return new Plus(leftDerivative,rightDerivative);
    }

    @Override
    protected String getSign() {
        return "+";
    }

    @Override
    protected Expression simplifyByRule(Expression leftSimplification, Expression rightSimplification) {
        if (checkEqual(leftSimplification, rightSimplification)){
            return new Mult(new Num(2),leftSimplification);
        } else if (checkEqualNum(leftSimplification,0)){
            return rightSimplification;
        } else if (checkEqualNum(rightSimplification,0)) {
            return leftSimplification;
        }else {
            return new Plus(leftSimplification,rightSimplification);
        }
    }
}
