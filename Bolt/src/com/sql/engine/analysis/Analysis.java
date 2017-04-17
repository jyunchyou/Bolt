package com.sql.engine.analysis;

import com.sql.engine.memory.BplusTreeI;

/**
 * Created by E450C on 2017/1/20.
 */
public class Analysis {
    String sql;




    BplusTreeI tree;
    public Analysis(String sql,BplusTreeI tree){
        this.tree=tree;
        this.sql=sql;

    }
    public void  parse(){
	//对sql进行compile
    }

}
