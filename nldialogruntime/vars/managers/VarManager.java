package nldialogruntime.vars.managers;

import nldialogruntime.vars.updatehandler.VarUpdateHandlerByOverride;
import nldialogruntime.common.Initializable;
import nldialogruntime.vars.VariableVar;

public abstract class VarManager implements Initializable{
    
    public VarManager(){
        
    }
    
    @Override
    public void preInit(){
        
    }

    @Override
    public void init(){
        
    }

    @Override
    public void postInit(){
        
    }
    
    public abstract VariableVar getVar(String scopedVarName);
    public abstract void addVar(VariableVar var);
    public abstract void updateVar(String scopedVarName);
    public abstract void updateVar(String scopedVarName, Object value)throws Exception;
    
    public abstract void addVarUpdateHandler(String scopedVarName, VarUpdateHandlerByOverride handler);   
}
