package nldialogruntime.events;

public class Event{
    protected Object source;
    
    public Event(){
        this.source = null;
    }
    
    public Event(Object source){
        this.source = source;
    }
    
    public Object getSource(){
        return source;
    }
    
    public void setSource(Object source){
        this.source = source;
    }
}
