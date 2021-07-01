import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Interpreteur {
    public static Set<Variable> variables;
    public static List<String> functions = Arrays.asList("sin","cos","log") ;
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
    public static  void main(String args[]){

    }
}
