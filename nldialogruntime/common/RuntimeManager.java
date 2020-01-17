package nldialogruntime.common;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import nldialogruntime.dialogs.DialogIterator;
import nldialogruntime.dialogs.DialogIteratorImpl;
import nldialogruntime.dialogs.collections.DialogCollectionImpl;
import nldialogruntime.dialogs.links.Linkable;
import nldialogruntime.vars.managers.VarManager;

public class RuntimeManager{
    private static RuntimeManager runtimeManagerInstance = null;
    private VarManager varManagerInstance = null;
    private DialogIteratorImpl iterator = new DialogIteratorImpl();
    
    private HashMap<String, DialogCollectionImpl> dialogCollections = new HashMap();
    
    private RuntimeManager(){
        
    }
    
    public static RuntimeManager getInstance(){
        if(runtimeManagerInstance == null)
            runtimeManagerInstance = new RuntimeManager();
        return runtimeManagerInstance;     
    }
    
    public VarManager getVarManagerInstance(){
        return varManagerInstance;
    }
    
    public VarManager setVarManagerClass(Class<? extends VarManager> varManagerClass){
        try{
            varManagerInstance = varManagerClass.newInstance();
        }catch(InstantiationException | IllegalAccessException ex){
            Logger.getLogger(RuntimeManager.class.getName()).log(Level.SEVERE, "Failed to instantiate the provided VarManager.", ex);
        }
        return varManagerInstance;
    }
    
    public DialogIterator getDialogIterator(){
        return iterator;
    }
    
    public void addDialogCollection(DialogCollectionImpl dialogCollection){
        dialogCollections.putIfAbsent(dialogCollection.getName(), dialogCollection);
    }
    
    public void start(String name){
        String[] scopes = name.split(Pattern.quote("."));
        
        DialogCollectionImpl collection = dialogCollections.get(scopes[0]);
        if(collection == null){
            Logger.getLogger(RuntimeManager.class.getName()).log(Level.SEVERE, "No collection with the name: {0} is present", scopes[0]);
            return;
        }
        
        if(scopes.length > 1)
            collection.startDialogChain(scopes[1]);
        
        //.get(name).visit(iterator);
    }
}
