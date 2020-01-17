package nldialogruntime.common;

public final class ScopedNameHelper{
    private ScopedNameHelper(){
        
    }
    
    public static String extractTrueName(String scopedName){
        int seperatorPos = scopedName.lastIndexOf(".");
        
        if(seperatorPos != -1)
            return scopedName.subSequence(seperatorPos, scopedName.length() - 1).toString();
        
        return scopedName;
    }
    
    public static String extractScope(String scopedName){
        int seperatorPos = scopedName.lastIndexOf(".");
        
        if(seperatorPos != -1)
            return scopedName.subSequence(0, seperatorPos).toString();
        
        return null;
    }
    
    public static String[] splitScopedName(String scopedName){
        return scopedName.split(".");
    }
    
    public static boolean isScopedName(String name){
        return name.contains(".");
    }
}
