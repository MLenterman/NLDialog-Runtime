package nldialogruntime.vars;

public class ConstantVar extends Var{
    
    protected ConstantVar(){
        
    }
    
    public ConstantVar(String name, Object var){
        super(name, var);
    }

    @Override
    public void update(){
        // Do nothing
    }

    @Override
    public void updateValue(Object value){
        // Do nothing
    }

}
