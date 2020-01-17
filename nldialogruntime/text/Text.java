package nldialogruntime.text;

public abstract class Text{
    protected String text = null;
    
    public Text(){
        
    }
    
    public Text(String text){
        this.text = text;
    }
    
    public String getText(){
        return text;
    }
}
