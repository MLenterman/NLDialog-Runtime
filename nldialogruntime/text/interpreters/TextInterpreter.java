package nldialogruntime.text.interpreters;

import nldialogruntime.text.ScriptedText;

public abstract class TextInterpreter{
    protected ScriptedText text = null;
    
    public TextInterpreter(){
        
    }
    
    public TextInterpreter(ScriptedText text){
        this.text = text;
    }
    
    public ScriptedText getText(){
        return text;
    }
    
    public void setText(ScriptedText text){
        this.text = text;
    }
    
    public abstract void interpret();
    public abstract String getResultText();
}
