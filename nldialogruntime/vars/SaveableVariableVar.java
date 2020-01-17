package nldialogruntime.vars;

import java.util.Map;

public class SaveableVariableVar extends VariableVar{
    private boolean hasChangedFlag = false;
    
    public SaveableVariableVar(){
        
    }
    
    public SaveableVariableVar(String name, Object var){
        super(name, var);
    }
    
    public SaveableVariableVar(Map<String, Object> saveDataMap){
        super();
        load(saveDataMap);
    }
    
    @Override
    public void update(){
        if(updateHandler != null){
            this.var = updateHandler.getUpdatedValue(this);
        }
        
        hasChangedFlag = true;
    }
    
    @Override
    public void save(Map<String, Object> saveDataMap){
        if(saveDataMap.isEmpty()){
            saveDataMap.putIfAbsent("name", this.name);
            saveDataMap.putIfAbsent("var", this.var);
            saveDataMap.putIfAbsent("type", this.type);
        }
        
        if(hasChangedFlag){
            saveDataMap.replace("name", this.name);
            saveDataMap.replace("var", this.var);
            saveDataMap.replace("type", this.type);
        }
        
        hasChangedFlag = false;
    }

    @Override
    public void load(Map<String, Object> saveDataMap){       
        if(!saveDataMap.isEmpty()){
            this.name = (String)saveDataMap.get("name");
            this.type = (Class<?>)saveDataMap.get("type");
            
            if(this.type == Boolean.class)
                this.var = (Boolean)saveDataMap.get("var");
            if(this.type == String.class)
                this.var = (String)saveDataMap.get("var");
            if(this.type == Integer.class)
                this.var = (Integer)saveDataMap.get("var");
            if(this.type == Float.class)
                this.var = (Float)saveDataMap.get("var");
        }
    }

}
