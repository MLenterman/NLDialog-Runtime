package nldialogruntime.dialogs.links;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import nldialogruntime.conditions.Condition;
import nldialogruntime.dialogs.DialogIteratorImpl;

public class DialogLink implements Linkable{
    private Map<Linkable, Condition> outLinks = new LinkedHashMap();
    
    public DialogLink(){
        
    }
    
    public Set<Linkable> getLinkables(){
        return outLinks.keySet();
    }
    
    public Map<Linkable, Condition> getLinkablesWithConditions(){
        return outLinks;
    }
    
    public void addOutLink(Linkable linkable){
        outLinks.putIfAbsent(linkable, null);
    }
    
    public void addOutLink(Linkable linkable, Condition condition){
        outLinks.putIfAbsent(linkable, condition);
    }
    
    @Override
    public void visit(DialogIteratorImpl iterator){
        next(iterator);
    }
    
    @Override
    public void next(DialogIteratorImpl iterator){
        Linkable nextLink = null;
        
        for(Entry entry : outLinks.entrySet()){
            if(entry.getValue() == null){
                nextLink = (Linkable)entry.getKey();
                break;
            }else{
                if(((Condition)entry.getValue()).evaluate()){
                    nextLink = (Linkable)entry.getKey();
                    break;
                }
            }
        }
        
        if(nextLink != null)
            nextLink.visit(iterator);
    }
 
}
