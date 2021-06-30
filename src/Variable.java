public class Variable extends Symbole{
    public Variable(String nom,double valeur){
        this.nom = nom;
        this.valeur = valeur;
    }
    public boolean isVariable(Interpreteur Interpreter,Variable variable){
        return Interpreter.getVariables().contains(variable);
    }
}
