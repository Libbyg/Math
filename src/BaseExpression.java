import java.util.*;

public abstract class BaseExpression implements Expression{

    @Override
    public double evaluate() throws Exception {
        return this.evaluate(new TreeMap<>());
    }
    protected abstract String getSign();

    public static Map<String,Double> generateAssignment(Collection<String> vars){
        Map<String,Double> assignment = new TreeMap<>();
        Random rand = new Random();
        for(String var:vars){
            Double value = 1 + Math.abs(rand.nextInt()) + rand.nextDouble();
            assignment.put(var,value);
        }
        return assignment;
    }

    public static boolean checkEqual(Expression x, Expression y){
        Set<String> xVarSet = new HashSet<>(x.getVariables());
        Set<String> yVarSet = new HashSet<>(y.getVariables());
        if (xVarSet.equals(yVarSet)){
            try{
                Map<String,Double> assignment = generateAssignment(xVarSet);
                double xValue = x.evaluate(assignment);
                double yValue = y.evaluate(assignment);
                return Double.compare(xValue,yValue)==0;
            } catch (Exception e){
                return checkEqual(x,y);
            }
        }
        return false;
    }

    public static boolean checkEqualNum(Expression x, double value){
        if (onlyNums(x)){
            try{
                return Double.compare(x.evaluate(), value) == 0;
            } catch (Exception e){
                return false;
            }
        }
        return false;
    }

    public static boolean onlyNums(Expression x){
        return x.getVariables().isEmpty();
    }

    public static Expression turnToNum(Expression e){
        if (onlyNums(e)){
            try{
                return new Num(e.evaluate());
            }catch (Exception exception){
                return e;
            }
        }
        return e;
    }
}
