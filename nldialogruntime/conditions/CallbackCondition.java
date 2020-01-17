package nldialogruntime.conditions;

public class CallbackCondition extends Condition{
    private ConditionHandlerByOverride handler = null;
    
    public CallbackCondition(){
        super();
    }
    
    public CallbackCondition(ConditionHandlerByOverride handler){
        this.handler = handler;
    }

    @Override
    public boolean evaluate(){
        if(handler != null)
            return handler.evaluate();
        return false;
    }
    
}
