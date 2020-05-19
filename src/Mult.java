public class Mult extends BinaryExpression {
    public Mult(Expression left, Expression right) {
        super(left, right);
    }

    protected double calculate(double leftValue, double rightValue) {
        return leftValue * rightValue;
    }

    @Override
    protected Expression differentiateByRule(Expression leftDerivative, Expression rightDerivative) {
        return new Plus(new Mult(this.getLeft(), rightDerivative), new Mult(this.getRight(), leftDerivative));
    }

    @Override
    protected String getSign() {
        return "*";
    }

    @Override
    protected Expression simplifyByRule(Expression leftSimplification, Expression rightSimplification) {
        if (checkEqualNum(leftSimplification, 1) || checkEqualNum(rightSimplification, 1)) {
            return checkEqualNum(leftSimplification, 1) ? rightSimplification : leftSimplification;
        }else if (checkEqualNum(leftSimplification, 0) || checkEqualNum(rightSimplification, 0)) {
            return new Num(0);
        } else if (checkEqual(leftSimplification, rightSimplification)) {
            return new Pow(leftSimplification, new Num(2));
        } else {
            return new Mult(leftSimplification, rightSimplification);
        }

    }
}