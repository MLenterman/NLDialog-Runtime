package nldialogruntime.dialogs.chain;

import java.util.LinkedList;
import java.util.regex.Pattern;
import nldialogruntime.dialogs.BasicDialog;
import nldialogruntime.dialogs.DialogIteratorImpl;
import nldialogruntime.dialogs.DialogSessionStarter;
import nldialogruntime.dialogs.links.Linkable;

public class DialogChainImpl implements DialogChain, Linkable, DialogSessionStarter{
    private String name = null;
    
    private LinkedList<Linkable> linkables = new LinkedList();
    
    public DialogChainImpl(){
        
    }
    
    public DialogChainImpl(String name){
        this.name = name;
    }
    
    @Override
    public String getName(){
        String[] split = name.split(Pattern.quote("."));
        return split[split.length - 1];
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    @Override
    public String getScopedName(){
        return name;
    }
    
    public void addLinkable(Linkable linkable){
        if(linkable instanceof BasicDialog)
            ((BasicDialog)linkable).setName(name + "." + ((BasicDialog)linkable).getName()); 
        linkables.add(linkable);
    }
    
    @Override
    public void start(DialogIteratorImpl iterator){
        visit(iterator);
    }
    
    @Override
    public void visit(DialogIteratorImpl iterator){
        next(iterator);
    }
    
    @Override
    public void next(DialogIteratorImpl iterator){
        linkables.getFirst().visit(iterator);
    }
    
}
