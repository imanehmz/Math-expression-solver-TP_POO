import java.util.List;

public class LetCommand extends Command{
    private String expression;
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

    public void treatment() {
    }
}