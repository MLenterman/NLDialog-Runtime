package nldialogruntime.dialogs.collections;

import java.util.LinkedHashMap;
import nldialogruntime.common.RuntimeManager;
import nldialogruntime.common.ScopedNameHelper;
import nldialogruntime.dialogs.DialogIteratorImpl;
import nldialogruntime.dialogs.chain.DialogChainImpl;
import nldialogruntime.dialogs.links.Linkable;

public class DialogCollectionImpl{
    private String name = null;  
    private LinkedHashMap<String, DialogChainImpl> linkables = new LinkedHashMap();
    
    public DialogCollectionImpl(){
        
    }
    
    public DialogCollectionImpl(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public Linkable getLinkable(String name){
        return linkables.get(ScopedNameHelper.extractTrueName(name));  
    }
    
    public void addDialogChain(DialogChainImpl dialogChain){
        dialogChain.setName(name + "." + dialogChain.getName());
        linkables.putIfAbsent(dialogChain.getName(), dialogChain);
    }
    
    public void startDialogChain(String name){
        DialogChainImpl chain = linkables.get(ScopedNameHelper.extractTrueName(name));
        
        if(chain != null)
            chain.start((DialogIteratorImpl)RuntimeManager.getInstance().getDialogIterator()); 
    }
}
