package nldialogruntime.dialogs.chain;

import nldialogruntime.dialogs.DialogIteratorImpl;

public interface DialogChain{
    void start(DialogIteratorImpl iterator);
    
    String getName();
    String getScopedName();
}
