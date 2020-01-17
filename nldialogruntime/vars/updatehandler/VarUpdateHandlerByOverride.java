package nldialogruntime.vars.updatehandler;

import nldialogruntime.vars.VariableVar;

public class VarUpdateHandlerByOverride implements VarUpdateHandler{
    public VarUpdateHandlerByOverride(){
        
    }
    
    public Boolean updateBoolean(final VariableVar var){
        return null;
    }
    
    public String updateString(final VariableVar var){
        return null;
    }
    
    public Integer updateInteger(final VariableVar var){
        return null;
    }
    
    public Float updateFloat(final VariableVar var){
        return null;
    }

    @Override
    public final Object getUpdatedValue(VariableVar var){
        return callUpdateMethod(var);
    }
    
    private Object callUpdateMethod(VariableVar var){
        if(var.getType() == Boolean.class)
            return updateBoolean(var);
        if(var.getType() == String.class)
            return updateString(var);
        if(var.getType() == Integer.class)
            return updateInteger(var);
        if(var.getType() == Float.class)
            return updateFloat(var);
        
        return null;
    }
}
