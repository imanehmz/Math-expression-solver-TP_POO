public abstract class Commande {
    protected String line;
    abstract void treatment();
    public abstract boolean syntaxCorrect();
    /*public boolean isFunction(){
        return true;
    }*/
    public double countExpression(){
        return 0;
    }
    public boolean bracketsCorrect(){
        return true;
    }
}
