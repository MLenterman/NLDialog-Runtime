package nldialogruntime.dialogs;

import nldialogruntime.dialogs.links.Linkable;
import nldialogruntime.text.Text;

public class BasicDialog extends Dialog{
    protected Linkable nextLinkable = null;
    
    public BasicDialog(){
        
    }
    
    public BasicDialog(String name){
        super(name);
    }
    
    public BasicDialog(String name, Text text){
        super(name, text);
    }
    
    public void setNextLinkable(Linkable linkable){
        nextLinkable = linkable;
    }
    
    @Override
    public void visit(DialogIteratorImpl iterator){
        iterator.setCurrentDialog(this);
    }
    
    @Override
    public void next(DialogIteratorImpl iterator){
        if(nextLinkable != null)
            nextLinkable.visit(iterator);
    }
    
}
