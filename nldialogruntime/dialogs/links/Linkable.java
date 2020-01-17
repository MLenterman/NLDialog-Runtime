package nldialogruntime.dialogs.links;

import nldialogruntime.dialogs.DialogIteratorImpl;

public interface Linkable{
    void visit(DialogIteratorImpl iterator);
    void next(DialogIteratorImpl iterator);
}
