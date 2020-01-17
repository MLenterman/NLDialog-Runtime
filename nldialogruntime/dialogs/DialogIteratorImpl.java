package nldialogruntime.dialogs;

public class DialogIteratorImpl implements DialogIterator{
    private Dialog currentDialog = null;
    private boolean hasChangedFlag = false;
    
    public DialogIteratorImpl(){
        
    }

    @Override
    public Dialog getCurrentDialog(){
        return currentDialog;
    }
    
    public void setCurrentDialog(Dialog dialog){
        currentDialog = dialog;
        hasChangedFlag = true;
    }

    @Override
    public boolean next(){
        hasChangedFlag = false;
        currentDialog.next(this);
        
        if(!hasChangedFlag)
            currentDialog = null;
            
        return hasChangedFlag;
    }
}
