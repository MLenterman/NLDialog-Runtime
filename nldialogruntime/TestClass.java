/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nldialogruntime;

import nldialogruntime.events.EventHandler;
import nldialogruntime.events.EventListener;
import nldialogruntime.events.EventManager;
import nldialogruntime.events.events.NLDialogEvent;

/**
 *
 * @author MARCEL-MSI
 */
public class TestClass implements EventListener{
    
    public TestClass(){
        EventManager.register(this);
    }
    
    @EventHandler
    public void testListener(NLDialogEvent event){
        System.out.println("check");
    }
    
}
