package com.sql.engine.memory;

import java.io.IOException;

/**
 * Created by E450C on 2017/1/8.
 */
public interface BplusTreeI {
    public Object get(Comparable key) throws IOException;   //查询
    public void remove(Comparable key) throws IOException;    //移除
    public void insertOrUpdate(Comparable key, Object obj) throws IOException; //插入或者更新，如果已经存在，就更新，否则插入
}
