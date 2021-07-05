import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /** i recommend putting the main inside the Main class, leave Interpreteur (that i renamed to: Compiler) clean*/

        // and
        Compiler compiler = new Compiler();
        Compiler.variables = new HashSet<Variable>();

        /** test these examples
         * let
         * let x
         * letx
         * let x 5
         * let x != 5
         * let my variable = 4
         * let x = (5*8)
         * let x= (5*8+())
         * let x = (5 + 2
         * let x = 2+ 5 )
         * let x = sin(4+2)
         * let x = myfunction()
         * let x = myfunction(x+3)
         * + les autres cas
         */
        Scanner sc =  new Scanner(System.in);
        String cmd = "";
        LetCommand let;
        PrintCommand print;

        while(!cmd.toLowerCase().equals("end")){
            try{
                System.out.print(">> ");
                cmd = sc.nextLine();
                if(cmd.trim().startsWith("let")){
                    try {
                        let = new LetCommand(cmd.trim());
                        System.out.println("syntax correct");
                        let.treatment();

                    } catch (InvalidSyntaxException e) {
                        System.out.println(e.getMessage());
                    } catch (OpeningParenthesisException e) {
                        System.out.println(e.getMessage());
                    } catch (ClosingParenthesisException e) {
                        System.out.println(e.getMessage());
                    } catch (EmptyParenthesisException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidVariableNameException e) {
                        System.out.println(e.getMessage());
                    } catch (NoVariableNameException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (DivisionByZeroException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (LogForNegativeException e) {
                        System.out.println(e.getMessage());
                    }

                }

                else if (cmd.trim().startsWith("print")){
                    try{
                        print = new PrintCommand(cmd.trim());
                        System.out.println("syntax correct");
                        print.treatment();
                    } catch (EmptyParenthesisException e) {
                        System.out.println(e.getMessage());
                    } catch (OpeningParenthesisException e) {
                        System.out.println(e.getMessage());
                    } catch (NoVariableNameException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidSyntaxException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidVariableNameException e) {
                        System.out.println(e.getMessage());
                    } catch (ClosingParenthesisException e) {
                        System.out.println(e.getMessage());
                    }
                }

                else if(!cmd.toLowerCase().equals("end")){
                    throw new CommandNotKnownException();
                }

            }catch (CommandNotKnownException e){
                System.out.println(e.getMessage());
            }
        }

    }
}
