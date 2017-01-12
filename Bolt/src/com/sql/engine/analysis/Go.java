package com.sql.engine.analysis;

import com.sql.engine.memory.BplusTree;
import com.sql.engine.memory.Node;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by E450C on 2017/1/8.
 */
public class Go {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
      /*  BplusTree tree=new BplusTree(3);
       for(int i=0;i<7;i++){
            tree.makeIndex(i,i,tree);
        }
        try {
            tree.writeNode(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
      BplusTree bplusTreet=BplusTree.readNode();

        System.out.println(bplusTreet.get(1));
        bplusTreet.insertOrUpdate(211,"蛤");
        System.out.println(bplusTreet.get(211));
        System.out.println(bplusTreet.get(888));




    }
}
