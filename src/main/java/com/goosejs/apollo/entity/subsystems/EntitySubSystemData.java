package com.goosejs.apollo.entity.subsystems;

import java.util.HashMap;
import java.util.Map;

public class EntitySubSystemData extends EntitySubSystem<EntitySubSystemData>
{

    private Map<String, Object> storedData;

    public EntitySubSystemData()
    {
        super("Data", 0);
        storedData = new HashMap<>();
    }

    @Override
    protected void doUpdate() { /* NO OP */ }

    @Override
    public EntitySubSystemData instantiate()
    {
        return null;
    }

    public boolean storeObject(String key, Object value)
    {
        if (storedData.containsKey(key))
            return false;

        storedData.put(key, value);
        return true;
    }

    public Object getObject(String key)
    {
        return storedData.get(key);
    }

    public String getString(String key)
    {
        return (String)storedData.get(key);
    }

    public int getInt(String key)
    {
        return (int)storedData.get(key);
    }

    public short getShort(String key)
    {
        return (short)storedData.get(key);
    }

    public long getLong(String key)
    {
        return (long)storedData.get(key);
    }

    public double getDouble(String key)
    {
        return (double)storedData.get(key);
    }

    public float getFloat(String key)
    {
        return (float)storedData.get(key);
    }

    public boolean getBoolean(String key)
    {
        return (boolean)storedData.get(key);
    }

    public byte getByte(String key)
    {
        return (byte)storedData.get(key);
    }

}
