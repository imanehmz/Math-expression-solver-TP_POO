import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command {
    protected String line;//this attribute keeps the command line just like the user has given it,we want to keep it just like a name
    protected String expression;//this attribute is the arithmetic expression extracted from the line either to print or to let
    private static final int RIGHT_DIRECTION = 1; //just some constants to make the process of writing the code easier
    private static final int LEFT_DIRECTION = -1;
    private static final ArrayList<Character> DIVIDERS = new ArrayList<Character>
            (Arrays.asList('*', '/', '-', '+'));
    public Command(String commandLine){
        this.line = commandLine;
    }

    abstract public void treatment()throws DivisionByZeroException,LogForNegativeException;
    public abstract void checkSyntax() throws InvalidSyntaxException, OpeningParenthesisException, ClosingParenthesisException, EmptyParenthesisException, NoVariableNameException, InvalidVariableNameException;
    public boolean expressionCorrect()throws DivisionByZeroException,LogForNegativeException{
        int pos = 0;
        if(-1 !=(pos = expression.indexOf("/"))){
            if (expression.charAt(pos + 1) == '0'){
                throw new DivisionByZeroException();
            }
        }
        if(-1 != (pos = expression.indexOf("log("))){
            if(expression.charAt(pos + 1) == '0'){
                throw new LogForNegativeException();
            }
        }
        return true;
    }
    public String countExpression(String expression){
        int pos = 0;
        System.out.println("Solving expression: "+expression);
        //Extracting expression from braces, doing recursive call
        //replace braced expression on result of it solving
        if (-1 != (pos = expression.indexOf("("))) {

            String subexp = extractExpressionFromBraces(expression,pos);
            expression = expression.replace("("+subexp+")", countExpression(subexp));

            return countExpression(expression);

            //Three states for calculating sin cos exp
            //input must be like sin0.7
        } else if (-1 != (pos = expression.indexOf("sin"))) {

            pos += 2;//shift index to last symbol of "sin" instead of first

            String number = extractNumber(expression, pos, RIGHT_DIRECTION);

            expression = expression.replace("sin"+number,
                    Double.toString(Math.sin(Double.parseDouble(number))));

            return countExpression(expression);

        } else if (-1 != (pos = expression.indexOf("cos"))) {

            pos += 2;

            String number = extractNumber(expression, pos, RIGHT_DIRECTION);

            expression = expression.replace("cos"+number,
                    Double.toString(Math.cos(Double.parseDouble(number))));

            return countExpression(expression);

        } else if (-1 != (pos = expression.indexOf("exp"))) {

            pos += 2;

            String number = extractNumber(expression, pos, RIGHT_DIRECTION);

            expression = expression.replace("exp" + number,
                    Double.toString(Math.exp(Double.parseDouble(number))));

            return countExpression(expression);
        }
        else if (-1 != (pos = expression.indexOf("log"))) {

                pos += 2;

                String number = extractNumber(expression, pos, RIGHT_DIRECTION);

                expression = expression.replace("log" + number,
                        Double.toString(Math.log(Double.parseDouble(number))));

                return countExpression(expression);
        }
        else if (-1 != (pos = expression.indexOf("abs"))) {

            pos += 2;

            String number = extractNumber(expression, pos, RIGHT_DIRECTION);

            expression = expression.replace("abs" + number,
                    Double.toString(Math.abs(Double.parseDouble(number))));

            return countExpression(expression);
        }
        else if (-1 != (pos = expression.indexOf("tan"))) {

            pos += 2;

            String number = extractNumber(expression, pos, RIGHT_DIRECTION);

            expression = expression.replace("tan" + number,
                    Double.toString(Math.tan(Double.parseDouble(number))));

            return countExpression(expression);
        }
        else if (-1 != (pos = expression.indexOf("sqrt"))) {

            pos += 3;

            String number = extractNumber(expression, pos, RIGHT_DIRECTION);

            expression = expression.replace("sqrt" + number,
                    Double.toString(Math.sqrt(Double.parseDouble(number))));

            return countExpression(expression);

        } else if (expression.indexOf("*") > 0 | expression.indexOf("/") > 0) {

            int multPos = expression.indexOf("*");
            int divPos = expression.indexOf("/");

            pos = Math.min(multPos, divPos);
            if (multPos < 0) pos = divPos; else if (divPos < 0) pos = multPos;
            //If one value of
            //*Pos will be -1 result of min will be incorrect.

            char divider = expression.charAt(pos);

            String leftNum = extractNumber(expression, pos, LEFT_DIRECTION);
            String rightNum = extractNumber(expression, pos, RIGHT_DIRECTION);

            expression = expression.replace(leftNum + divider + rightNum,
                    calcShortExpr(leftNum, rightNum, divider));

            return countExpression(expression);


        } else if (expression.indexOf("+") > 0 | expression.indexOf("-") > 0) {

            int summPos = expression.indexOf("+");
            int minusPos = expression.indexOf("-");

            pos = Math.min(summPos, minusPos);

            if (summPos < 0) pos = minusPos; else if (minusPos < 0) pos = summPos;

            char divider = expression.charAt(pos);

            String leftNum = extractNumber(expression, pos, LEFT_DIRECTION);
            String rightNum = extractNumber(expression, pos, RIGHT_DIRECTION);

            expression = expression.replace(leftNum + divider + rightNum,
                    calcShortExpr(leftNum, rightNum, divider));

            return countExpression(expression);
        }
        else {



            ///////////////I THINK  THERE'S an error here ////////////////////////////////////////////////





            for (Variable v :Compiler.variables){
                if (-1 != (pos = expression.indexOf(v.nom))) {

                    pos += v.nom.length();

                    //String number = extractNumber(expression, pos, RIGHT_DIRECTION);

                    expression = expression.replace(v.nom ,Double.toString(v.valeur));

                    return countExpression(expression);

                }
            }
        } return expression;
    }
    private static String extractNumber(String expression, int pos, int direction) {

        String resultNumber = "";
        int currPos = pos + direction;//shift pos on next symbol from divider

        //For negative numbers
        if (expression.charAt(currPos) == '-') {
            resultNumber+=expression.charAt(currPos);
            currPos+=direction;
        }

        for (; currPos >= 0 &&
                currPos < expression.length() &&
                !DIVIDERS.contains(expression.charAt(currPos));
             currPos += direction) {
            resultNumber += expression.charAt(currPos);
        }

        if (direction==LEFT_DIRECTION) resultNumber = new
                StringBuilder(resultNumber).reverse().toString();

        return resultNumber;
    }

    private static String calcShortExpr(String leftNum, String rightNum, char divider) {
        switch (divider) {
            case '*':
                return Double.toString(Double.parseDouble(leftNum) *
                        Double.parseDouble(rightNum));
            case '/':
                return Double.toString(Double.parseDouble(leftNum) /
                        Double.parseDouble(rightNum));
            case '+':
                return Double.toString(Double.parseDouble(leftNum) +
                        Double.parseDouble(rightNum));
            case '-':
                return Double.toString(Double.parseDouble(leftNum) -
                        Double.parseDouble(rightNum));
            default:
                return "0";
        }

    }
    private static String extractExpressionFromBraces(String expression, int pos) {
        int braceDepth = 1;
        String subexp="";

        for (int i = pos+1; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '(':
                    braceDepth++;
                    subexp += "(";
                    break;
                case ')':
                    braceDepth--;
                    if (braceDepth != 0) subexp += ")";
                    break;
                default:
                    if (braceDepth > 0) subexp += expression.charAt(i);

            }
            if (braceDepth == 0 && !subexp.equals("")) return subexp;
        }
        return "failure"; //what should we put in here
    }
    public boolean ParenthesisCorrect() throws  OpeningParenthesisException, ClosingParenthesisException, EmptyParenthesisException{
        Stack stack = new Stack();
        Stack.IndexedChar indexedChar;
        for(int i =0; i<line.length(); i++){
            char c = line.charAt(i);
            if(c=='(') stack.push(c, i);
            else if (c==')'){
                try{
                    indexedChar = stack.pop();
                    String stringBetweenParenthesis = line.substring(indexedChar.getIndex()+1, i).replace(" ", "");
                    if(stringBetweenParenthesis.isEmpty()) throw new EmptyParenthesisException();
                }catch (StackUnderflowException e){
                    throw  new OpeningParenthesisException();
                }
            }
        }
        if(!stack.isEmpty()) throw new ClosingParenthesisException();
        return true;
    }

    private static boolean isAnOperator(char c) {
        switch (c) {
            case '*':
            case '/':
            case '+':
            case '-':
            case '%':
                return true;
            default:
                return false;
        }
    }
    private static boolean isANumber(char c){
        return ((int)c) >= 48 && ((int)c) <= 57;
    }
    private static boolean isAllowedInVariableName(char c){
        return Character.isAlphabetic(c);
    }

    public static boolean isValidExpression(String expression) {
        if (isAnOperator(expression.charAt(0)) || isAnOperator(expression.charAt(expression.length() - 1))) {
            return false;
        }

        int openParenthCount = 0;
        boolean lastWasOp = false;
        boolean lastWasOpen = false;
        StringBuilder variableName = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (c == '(') {
                variableName = new StringBuilder();
                openParenthCount++;
                lastWasOpen = true;
                continue;

            } else if (c == ')') {
                variableName = new StringBuilder();
                if (openParenthCount <= 0 || lastWasOp) {
                    return false;
                }
                openParenthCount--;
            }else if (isAnOperator(c)){
                variableName = new StringBuilder();
                if (lastWasOp || lastWasOpen) return false;
                lastWasOp = true;
                continue;
            }else if (isAllowedInVariableName(c)){
                variableName.append(c);
            }
            else if(!isANumber(c)){
                variableName = new StringBuilder();
                return false;
            }
            lastWasOp = false;
            lastWasOpen = false;
        }
        if(openParenthCount != 0) return false;
        if(lastWasOp || lastWasOpen) return false;
        return true;
    }

}























