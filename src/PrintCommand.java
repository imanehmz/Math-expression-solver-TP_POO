import java.security.PublicKey;

public class PrintCommand extends Command {
    private String expression;

    public PrintCommand(String command) throws EmptyParenthesisException, OpeningParenthesisException, NoVariableNameException, InvalidSyntaxException, InvalidVariableNameException, ClosingParenthesisException {
        super(command);
        checkSyntax();
    }

    public void treatment(){
    }

    public void checkSyntax() throws OpeningParenthesisException, ClosingParenthesisException, EmptyParenthesisException, NoVariableNameException, InvalidVariableNameException, InvalidSyntaxException {
        if (!ParenthesisCorrect()) throw new InvalidSyntaxException();
        this.expression = line.substring(5).trim();
        if (this.expression.isEmpty()) throw new InvalidSyntaxException();
        this.expression = this.expression.replace(" ", "");
        if(!Command.isValidExpression(this.expression)) throw new InvalidSyntaxException();

    }
}
