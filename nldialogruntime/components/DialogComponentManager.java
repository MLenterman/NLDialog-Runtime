package nldialogruntime.components;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import nldialogruntime.dialogs.Dialog;

public class DialogComponentManager<T extends DialogComponent>{
    private LinkedHashMap<Dialog, T> components = new LinkedHashMap();
 
    public DialogComponentManager(){

    }
   
    public void processAll(){
        for(Entry entry : components.entrySet()){
            if(entry.getValue() != null)
                process((Dialog)entry.getValue());
        }
    }
   
    public void process(Dialog Dialog){
    
    }
 
    public void addComponentTo(Dialog dialog , T component){
        components.putIfAbsent(dialog, component);
    }
   
    public void removeComponentFrom(Dialog dialog){
        components.remove(dialog);
    }
   
    public T getComponentFrom(Dialog dialog){
        return components.get(dialog);
    }

}
