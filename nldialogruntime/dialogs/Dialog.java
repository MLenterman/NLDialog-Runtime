package nldialogruntime.dialogs;

import java.util.regex.Pattern;
import nldialogruntime.dialogs.links.Linkable;
import nldialogruntime.text.Text;

public abstract class Dialog implements Linkable{
    protected String name = null;
    protected Text text = null;
    
    public Dialog(){
        
    }
    
    public Dialog(String name){
        this.name = name;
    }
    
    public Dialog(String name, Text text){
        this.name = name;
        this.text = text;
    }
    
    public String getName(){
        String[] split = name.split(Pattern.quote("."));
        return split[split.length - 1];
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getScopedName(){
        return name;
    }
    
    public Text getText(){
        return text;
    }
    
    @Override
    public abstract void next(DialogIteratorImpl iterator);
    
    @Override
    public abstract void visit(DialogIteratorImpl iterator);
}
