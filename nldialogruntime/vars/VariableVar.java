package nldialogruntime.vars;

import java.util.Map;
import nldialogruntime.common.Saveable;
import nldialogruntime.vars.updatehandler.VarUpdateHandler;

public class VariableVar extends Var implements Saveable{
    protected VarUpdateHandler updateHandler = null;
    
    public VariableVar(){
        
    }
    
    public VariableVar(String name, Object var){
        super(name, var);
    }

    public VarUpdateHandler getUpdateHandler(){
        return updateHandler;
    }
    
    public void setUpdateHandler(VarUpdateHandler updateHandler){
        this.updateHandler = updateHandler;
    }
    
    @Override
    public void update(){
        if(updateHandler != null){
            this.var = updateHandler.getUpdatedValue(this);
        }  
    }  

    @Override
    public void save(Map<String, Object> saveMap){
        
    }

    @Override
    public void load(Map<String, Object> loadMap){
        
    }
}
