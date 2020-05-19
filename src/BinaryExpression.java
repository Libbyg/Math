import java.util.*;

public abstract class BinaryExpression extends BaseExpression {
    private Expression left;
    private Expression right;

    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws InvalidAssignmentException, ArithmeticException {
        double leftValue = this.getLeft().evaluate(assignment);
        double rightValue = this.getRight().evaluate(assignment);
        return this.calculate(leftValue,rightValue);
    }

    protected abstract double calculate(double leftValue, double rightValue) throws ArithmeticException;


    @Override
    public List<String> getVariables() {
        Set<String> varSet = new HashSet<>(this.left.getVariables());
        varSet.addAll(this.right.getVariables());
        return new ArrayList<>(varSet);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        this.left = this.left.assign(var, expression);
        this.right = this.right.assign(var, expression);
        //returns expression
        return this;
    }

    @Override
    public Expression differentiate(String var) {
        Expression leftDerivative = this.left.differentiate(var);
        Expression rightDerivative = this.right.differentiate(var);
        return this.differentiateByRule(leftDerivative,rightDerivative);
    }

    protected abstract Expression differentiateByRule(Expression leftDerivative, Expression rightDerivative);

    @Override
    public String toString() {
        return "(" + this.left.toString() + this.getSign() + this.right.toString() + ")";
    }

    @Override
    public Expression simplify() {
        Expression leftSimplification,rightSimplification;
        if (onlyNums(left)){
            leftSimplification = turnToNum(left);
        } else{
            leftSimplification = this.left.simplify();
        }
        if (onlyNums(right)){
            rightSimplification= turnToNum(right);
        } else {
            rightSimplification = this.right.simplify();
        }
        return this.simplifyByRule(leftSimplification,rightSimplification);
    }

    protected abstract Expression simplifyByRule(Expression leftSimplification, Expression rightSimplification);


}
