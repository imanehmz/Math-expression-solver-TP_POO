import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Interpreteur {
    private Set<Variable> variables;
    public static List<String> functions = Arrays.asList("sin","cos","log") ;
    void setVariables(Set<Variable> variables){
        this.variables = variables; //rectify this later
    }
    Set<Variable> getVariables(){
        return this.variables;
    }
    public static  void main(String args[]){

    }
}
