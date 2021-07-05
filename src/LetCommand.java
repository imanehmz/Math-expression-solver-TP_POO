import java.util.List;
import java.util.Set;

public class LetCommand extends Command{
    private String variable;

    public String getVariable(){
        return this.variable;
    }

    public String getExpression(){
        return this.expression;
    }


    public LetCommand(String commandeLine) throws InvalidSyntaxException, OpeningParenthesisException, ClosingParenthesisException, EmptyParenthesisException, InvalidVariableNameException, NoVariableNameException {
        super(commandeLine);
        checkSyntax();
    }

    public void retreiveVariableName(List<String> functions) throws InvalidVariableNameException, NoVariableNameException {
        String subString = line.trim();
        subString = subString.substring(3, subString.indexOf("=")).trim();
        if (subString.contains(" ")) throw new InvalidVariableNameException();
        if (functions.contains(subString)) throw new InvalidVariableNameException();
        if (!subString.isEmpty() && !subString.isBlank()) this.variable = subString;
        else throw new NoVariableNameException();
    }

    public void checkSyntax() throws OpeningParenthesisException, ClosingParenthesisException, EmptyParenthesisException, NoVariableNameException, InvalidVariableNameException, InvalidSyntaxException {
        if (!ParenthesisCorrect()) throw new InvalidSyntaxException();
        int index = this.line.indexOf("=");
        if (index < 4) throw  new InvalidSyntaxException();

        this.expression = line.substring(index + 1).trim();
        if (this.expression.isEmpty()) throw new InvalidSyntaxException();

        retreiveVariableName(Compiler.functions);

        if(!Command.isValidExpression(this.expression)) throw new InvalidSyntaxException();

    }
    public boolean variableExists(Set<Variable> variables,String var){
        for ( Variable v : variables){
            if (var.equals(v.nom)){
                return true;
            }
        }
        return false;
    }
    public void treatment() throws DivisionByZeroException,LogForNegativeException{
            expressionCorrect(); //verify if the syntax is correct
            String var = this.line.trim();
            var = var.substring(3, var.indexOf("=")).trim(); //extract the variable name
            System.out.println(this.expression);
            System.out.println(this.line);
            System.out.println(var);
            //We verify if the expression is valid (division by zero,log something negative...
            //Counting the value of the expression is permitted so we only put it in the variable
            //if the variable name exists,we modify it else we add it to the set
        if(Compiler.variables != null){
            if (variableExists(Compiler.variables, var)) {
                for (Variable v : Compiler.variables) {
                    if (var.equals(v.nom)) {
                        v.valeur = Double.parseDouble(countExpression(this.expression));
                        System.out.println(v.nom +" "+ v.valeur);
                    }
                }
            }
        }else {
                Variable v = new Variable(var, Double.parseDouble(countExpression(this.expression)));
                System.out.println(v.nom +" "+ v.valeur);
                Compiler.variables.add(v);
            }
    }
}