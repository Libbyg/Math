public class Test {
    public static void main(String[] args) {
        Expression x = new Var("x");
        Expression e = new Log(new Var("x"), new Var("y"));
        Expression e1 = new Pow(new Mult(x,new Sin(x)),e);
        System.out.println(e1.toString());
        System.out.println(e1.differentiate("x").toString());
        System.out.println(e1.differentiate("x").simplify().toString());
    }
}
