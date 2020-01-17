package nldialogruntime;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import nldialogruntime.common.RuntimeManager;
import nldialogruntime.conditions.CallbackCondition;
import nldialogruntime.conditions.ConditionHandlerByOverride;
import nldialogruntime.dialogs.BasicDialog;
import nldialogruntime.dialogs.DialogIterator;
import nldialogruntime.dialogs.DialogIteratorImpl;
import nldialogruntime.dialogs.chain.DialogChainImpl;
import nldialogruntime.dialogs.collections.DialogCollectionImpl;
import nldialogruntime.dialogs.links.DialogLink;
import nldialogruntime.events.EventManager;
import nldialogruntime.events.events.NLDialogEvent;
import nldialogruntime.text.BasicText;
import nldialogruntime.vars.managers.DefaultVarManager;
import nldialogruntime.vars.SaveableVariableVar;
import nldialogruntime.vars.managers.VarManager;
import nldialogruntime.vars.VariableVar;
import nldialogruntime.vars.updatehandler.VarUpdateHandlerByOverride;

public class NLDialogRuntime{
    public static boolean c1 = true;
    public static boolean c2 = true;

    public static void main(String[] args)throws Exception{
        //collectionDemo();
        
        TestClass test = new TestClass();
        EventManager.postEvent(new NLDialogEvent());
    }
    
    public static void varSaveLoadTest(){
        RuntimeManager runtimeManager = RuntimeManager.getInstance();
        VarManager varManager = runtimeManager.setVarManagerClass(DefaultVarManager.class);
        
        varManager.addVar(new SaveableVariableVar("Var A", true));
        varManager.addVar(new SaveableVariableVar("Var B", "Before Update"));
        varManager.addVar(new SaveableVariableVar("Var C", 5));
        varManager.addVar(new SaveableVariableVar("Var D", 5.4));
        
        System.out.println("Var name: " + varManager.getVar("Var A").getScopedName() + ", Value: " + varManager.getVar("Var A").getValue().toString());
        System.out.println("Var name: " + varManager.getVar("Var B").getScopedName() + ", Value: " + varManager.getVar("Var B").getValue().toString());
        System.out.println("Var name: " + varManager.getVar("Var C").getScopedName() + ", Value: " + varManager.getVar("Var C").getValue().toString());
        System.out.println("Var name: " + varManager.getVar("Var D").getScopedName() + ", Value: " + varManager.getVar("Var D").getValue().toString());
        
        varManager.addVarUpdateHandler("Var B", new VarUpdateHandlerByOverride(){
            @Override
            public String updateString(VariableVar var){
                return "After Update";
            }
        });

        System.out.println("---------------------------\n");
        
        System.out.println("SaveDataMap content:");
        HashMap<String, Object> saveDataMap = new HashMap();
        varManager.getVar("Var B").save(saveDataMap);
        
        for(Entry entry : saveDataMap.entrySet()){
            System.out.println("Var name: " + entry.getKey() + ", value: " + entry.getValue());
        }
        
        System.out.println("\nLoaded var content:");
        
        varManager.updateVar("Var B");
        varManager.getVar("Var B").save(saveDataMap);
        
        SaveableVariableVar loadedVar= new SaveableVariableVar(saveDataMap);
        
        System.out.println("Name: " + loadedVar.getScopedName());
        System.out.println("Value: " + loadedVar.getValue().toString());
        System.out.println("Type: " + loadedVar.getType().toString());
    }
    
    public static void collectionDemo(){
        // Get instance of the runtime manager
        RuntimeManager runtimeManager = RuntimeManager.getInstance();
        
        // Create and add 2 DialogCollections
        DialogCollectionImpl colA = new DialogCollectionImpl("colA");
        DialogCollectionImpl colB = new DialogCollectionImpl("colB");   
        runtimeManager.addDialogCollection(colA);
        runtimeManager.addDialogCollection(colB);
        
        // Create, add and setup 2 dialog chains for collection A
        DialogChainImpl chainA = new DialogChainImpl("chainA");
        DialogChainImpl chainB = new DialogChainImpl("chainB");
        colA.addDialogChain(chainA);        
        colA.addDialogChain(chainB);
        // Chain A
        BasicDialog d1 = new BasicDialog("Dialog 1", new BasicText("Hello!"));
        BasicDialog d2 = new BasicDialog("Dialog 2", new BasicText("Do you want to grade this project?"));
        BasicDialog d3 = new BasicDialog("Dialog choice A", new BasicText("Well, thank you!"));
        BasicDialog d4 = new BasicDialog("Dialog choice B", new BasicText("Aww, c'mon!"));
        DialogLink l1 = new DialogLink();
        
        d1.setNextLinkable(d2);
        d2.setNextLinkable(l1);
        
        l1.addOutLink(d3, new CallbackCondition(new ConditionHandlerByOverride(){
            @Override
            public boolean evaluate(){
                return c1;
            }
        }));
        
        l1.addOutLink(d4, new CallbackCondition(new ConditionHandlerByOverride(){
            @Override
            public boolean evaluate(){
                return !c1;
            }
        }));
        
        chainA.addLinkable(d1);
        chainA.addLinkable(d2);
        chainA.addLinkable(d3);
        chainA.addLinkable(d4);
        chainA.addLinkable(l1);
        
        // Chain B
        BasicDialog dA = new BasicDialog("Dialog 1", new BasicText("This is a simple story..."));
        BasicDialog dB = new BasicDialog("Dialog 2", new BasicText("About a hard working student..."));
        BasicDialog dC = new BasicDialog("Dialog 3", new BasicText("Who likes to do impossibly large projects..."));
        BasicDialog dD = new BasicDialog("Dialog 4", new BasicText("And he never learns..."));
        
        dA.setNextLinkable(dB);
        dB.setNextLinkable(dC);
        dC.setNextLinkable(dD);
        
        chainB.addLinkable(dA);
        chainB.addLinkable(dB);
        chainB.addLinkable(dC);
        chainB.addLinkable(dD);
        
        // Start collection A, chain A
        DialogIterator iterator = runtimeManager.getDialogIterator();
        runtimeManager.start("colA.chainA");
        
        System.out.println();
        System.out.println("Collection A, Chain A:");
        System.out.println("Name: " + iterator.getCurrentDialog().getScopedName() + ", Text: " + iterator.getCurrentDialog().getText().getText());
        iterator.next();
        System.out.println("Name: " + iterator.getCurrentDialog().getScopedName() + ", Text: " + iterator.getCurrentDialog().getText().getText());
          
        Scanner scanner = new Scanner(System.in);
        System.out.print("Answer (yes = true, no = false): ");
        c1 = scanner.nextBoolean();
        
        iterator.next();     
        System.out.println("Name: " + iterator.getCurrentDialog().getScopedName() + ", Text: " + iterator.getCurrentDialog().getText().getText());
        
        // Start collection A, chain B
        runtimeManager.start("colA.chainB");
        
        System.out.println();
        System.out.println("Collection A, Chain B:");
        
        do{
            System.out.println("Name: " + iterator.getCurrentDialog().getScopedName() + ", Text: " + iterator.getCurrentDialog().getText().getText());
        }while(iterator.next());
    }
    
    public static void test(){
        RuntimeManager runtimeManager = RuntimeManager.getInstance();
        
        DialogChainImpl chain1 = new DialogChainImpl("Chain A");
        
        BasicDialog d1 = new BasicDialog("Dialog 1", new BasicText("Text from dialog 1..."));
        BasicDialog d2 = new BasicDialog("Dialog 2", new BasicText("Text from dialog 2..."));
        BasicDialog d3 = new BasicDialog("Dialog choice A", new BasicText("Text from choice A, after DialogLink condition..."));
        BasicDialog d4 = new BasicDialog("Dialog choice B", new BasicText("Text from choice B, after DialogLink condition..."));
        DialogLink l1 = new DialogLink();
        
        d1.setNextLinkable(d2);
        d2.setNextLinkable(l1);
        
        l1.addOutLink(d3, new CallbackCondition(new ConditionHandlerByOverride(){
            @Override
            public boolean evaluate(){
                return c1;
            }
        }));
        
        l1.addOutLink(d4, new CallbackCondition(new ConditionHandlerByOverride(){
            @Override
            public boolean evaluate(){
                return c2;
            }
        }));
        
        chain1.addLinkable(d1); 
        chain1.addLinkable(d2);
        chain1.addLinkable(d3);
        chain1.addLinkable(d4);
        chain1.addLinkable(l1);
        
        DialogChainImpl chain2 = new DialogChainImpl("Chain B");
        BasicDialog ch1 = new BasicDialog("Dialog 1", new BasicText("Text from dialog 1, chain 2..."));
        BasicDialog ch2 = new BasicDialog("Dialog 2", new BasicText("Text from dialog 2, chain 2..."));
        chain2.addLinkable(ch1); 
        chain2.addLinkable(ch2);
        chain1.addLinkable(chain2);
        
        d4.setNextLinkable(chain2);
        ch1.setNextLinkable(ch2); 
        
        DialogIteratorImpl iterator = new DialogIteratorImpl();
        
        System.out.println("Interation with first link condition TRUE, second link condition TRUE:\n");
        chain1.start(iterator);  
        
        do{
            System.out.println("Name: " + iterator.getCurrentDialog().getScopedName() + ", Text: " + iterator.getCurrentDialog().getText().getText());
        }while(iterator.next());
        
        System.out.println("\n-----------------------------\n");
        System.out.println("Interation with first link condition FALSE, second link condition TRUE:\n");
        c1 = false;
        chain1.start(iterator);
        
        do{
            System.out.println("Name: " + iterator.getCurrentDialog().getScopedName() + ", Text: " + iterator.getCurrentDialog().getText().getText());
        }while(iterator.next());
    }

}
