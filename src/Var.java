import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {
    //Field
    String var;

    //Constructor
    public Var(String var){
        //initialize variable's name
        this.var = var;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws InvalidAssignmentException, ArithmeticException {
        if (assignment != null) {
            if(assignment.containsKey(this.var)){
                return assignment.get(this.var);
            } else {
                throw new InvalidAssignmentException(this.var);
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public double evaluate() throws Exception {
        throw new Exception();
    }

    @Override
    public List<String> getVariables() {
        List<String> varList = new ArrayList<>();
        varList.add(this.var);
        return varList;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(this.var)){
            return expression;
        }
        return this;
    }

    @Override
    public Expression differentiate(String var) {
        if (var.equals(this.var)){
            return new Num(1);
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public String toString() {
        return var;
    }
}
