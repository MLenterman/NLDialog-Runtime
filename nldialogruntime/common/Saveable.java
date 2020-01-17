package nldialogruntime.common;

import java.util.Map;

public interface Saveable{
    void save(Map<String, Object> saveDataMap);
    void load(Map<String, Object> saveDataMap);
}
