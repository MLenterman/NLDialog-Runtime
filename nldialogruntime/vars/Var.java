package nldialogruntime.vars;

public abstract class Var{
    protected String name = null;
    protected Object var = null;
    protected Class<?> type = null;
    
    public Var(){
        
    }
    
    public Var(String name, Object var){
        this.name = name;
        this.var = var;
        this.type = var.getClass();
    }
    
    public String getName(){
        String[] split = name.split(".");
        return split[split.length - 1];
    }
    
    public String getScopedName(){
        return name;
    }
    
    public Object getValue(){
        return var;
    }
    
    public void updateValue(Object value)throws Exception{
        if(type == value.getClass())
            var = value;
        else throw new Exception("Type mismatch in function updateValue");
    }
    
    public Class<?> getType(){
        return type;
    }
    
    public abstract void update();
}
