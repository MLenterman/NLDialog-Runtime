package nldialogruntime.events.events;

import nldialogruntime.dialogs.Dialog;

public class DialogActionEvent extends NLDialogEvent{ 
    protected int eventTypeId = 0;
    
    public final static int PRE_VISIT = 1;
    public final static int POST_VISIT = 2;
    public final static int PRE_NEXT = 3;
    public final static int POST_NEXT = 4;
    
    public DialogActionEvent(){
        super();
    }
    
    public DialogActionEvent(Dialog source){
        super(source);
    }
}
