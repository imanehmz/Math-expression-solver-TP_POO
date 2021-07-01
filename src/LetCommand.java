public class LetCommand extends Commande{
    public void treatment(){
    }
    public boolean syntaxCorrect(){
        //the line looks like this at this point
        //let var = expression
        String expression = this.line.substring(3);
        String var = expression.substring(expression.indexOf("=") + 1);
       /* if (Interpreteur.variables.contains(var)){
            Interpreteur.variables.
        }*/
        return true;
    }
}
