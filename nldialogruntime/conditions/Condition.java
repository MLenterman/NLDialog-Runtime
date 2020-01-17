package nldialogruntime.conditions;

import java.lang.reflect.Constructor;

public abstract class Condition implements ConditionPart{
    protected String leftHandVar = null;
    protected Class<?> leftHandType = null;
    protected String rightHandVar = null;
    protected Class<?> rightHandType = null;
    
    protected ConditionOperator operator = null;
    
    protected Condition(){
        
    }
    
    public Condition(String leftHandVar, Class<?> leftHandType, String rightHandVar, Class<?> rightHandType, ConditionOperator operator){
        this.leftHandVar = leftHandVar;
        this.leftHandType = leftHandType;
        this.rightHandVar = rightHandVar;
        this.rightHandType = rightHandType;
        this.operator = operator;
    }

    @Override
    public abstract boolean evaluate();
    
}
