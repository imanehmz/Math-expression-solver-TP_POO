import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Compiler {
    public static Set<Variable> variables;
    public static List<String> functions = Arrays.asList("sin","cos","log", "abs", "log") ;

    void setVariables(Set<Variable> variables){
        this.variables = variables; //rectify this later
    }
    Set<Variable> getVariables(){
        return this.variables;
    }
    public boolean contains(String varname){
        for ( Variable var : variables){
            if (var.nom.equals(varname)){
                return true;
            }
        }
        return false;
    }

}
