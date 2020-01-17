package nldialogruntime.vars.managers;

import nldialogruntime.vars.updatehandler.VarUpdateHandlerByOverride;
import java.util.HashMap;
import java.util.Map;
import nldialogruntime.vars.VariableVar;

public class DefaultVarManager extends VarManager{
    protected Map<String, VariableVar> vars = new HashMap();

    @Override
    public VariableVar getVar(String scopedVarName){
        return vars.get(scopedVarName);
    }
    
    @Override
    public void addVar(VariableVar var){
        vars.put(var.getScopedName(), var);
    }

    @Override
    public void updateVar(String scopedVarName){
        VariableVar var = vars.get(scopedVarName);
        
        if(var != null){
            var.update();
        }  
    }

    @Override
    public void updateVar(String scopedVarName, Object value)throws Exception{
        VariableVar var = vars.get(scopedVarName);
        
        if(var != null)
            var.updateValue(value);
    }

    @Override
    public void addVarUpdateHandler(String scopedVarName, VarUpdateHandlerByOverride handler){
        VariableVar var = vars.get(scopedVarName);
        
        if(var != null){
            var.setUpdateHandler(handler);
        }
    }

}
