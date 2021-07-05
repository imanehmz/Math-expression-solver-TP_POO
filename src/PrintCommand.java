import java.security.PublicKey;

public class PrintCommand extends Command {
    public PrintCommand(String command) throws EmptyParenthesisException, OpeningParenthesisException, NoVariableNameException, InvalidSyntaxException, InvalidVariableNameException, ClosingParenthesisException {
        super(command);
        checkSyntax();
        treatment();
    }

    public void treatment(){
        System.out.println(this.expression); //expression at this point is ready for evaluation ,variable existence checking
        int pos = 0;
        System.out.println(countExpression(this.expression));
        /*if (-1 != (pos = expression.indexOf("("))) {

            String subexp = extractExpressionFromBraces(expression,pos);
            expression = expression.replace("("+subexp+")", calc(subexp));

            return calc(expression);*/
    }

    public void checkSyntax() throws OpeningParenthesisException, ClosingParenthesisException, EmptyParenthesisException, NoVariableNameException, InvalidVariableNameException, InvalidSyntaxException {
        if (!ParenthesisCorrect()) throw new InvalidSyntaxException();
        this.expression = line.substring(5).trim();
        if (this.expression.isEmpty()) throw new InvalidSyntaxException();
        this.expression = this.expression.replace(" ", "");
        if(!Command.isValidExpression(this.expression)) throw new InvalidSyntaxException();
    }
}
