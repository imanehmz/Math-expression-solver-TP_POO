class InvalidSyntaxException extends Exception{
    @Override
    public String getMessage() {
        return "Invalid syntax";
    }
}

class InvalidVariableNameException extends Exception{
    @Override
    public String getMessage() {
        return "Invalid variable name";
    }
}

class NoVariableNameException extends Exception{
    @Override
    public String getMessage() {
        return "No variable was found";
    }
}

class StackUnderflowException extends Exception{}

class OpeningParenthesisException extends Exception{
    @Override
    public String getMessage() {
        return "Opening parenthesis missing";
    }
}

class ClosingParenthesisException extends Exception{
    @Override
    public String getMessage() {
        return "Closing parenthesis missing";
    }
}

class  EmptyParenthesisException extends Exception{
    @Override
    public String getMessage() {
        return "Empty parenthesis found";
    }
}

class CommandNotKnownException extends Exception{
    @Override
    public String getMessage() {
        return "Command not known by the compiler";
    }
}


