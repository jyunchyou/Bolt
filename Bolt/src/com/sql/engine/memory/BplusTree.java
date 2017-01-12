package com.sql.engine.memory;

import java.io.*;
import java.util.Random;

/**
 * Created by E450C on 2017/1/8.
 */
public class BplusTree implements BplusTreeI,Serializable{

    /**
     * 根节点
     */
    protected Node root;

    /**
     * 阶数，M值
     */
    protected int order;

    /**
     * 叶子节点的链表头
     */
    protected Node head;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public Object get(Comparable key) throws IOException {
        return root.get(key);
    }


    @Override
    public void remove(Comparable key) throws IOException {
        root.remove(key, this);
        writeNode(this);

    }

    @Override
    public void insertOrUpdate(Comparable key, Object obj) throws IOException {
        root.insertOrUpdate(key, obj, this);
        writeNode(this);


    }

    public BplusTree(int order) {
        if (order < 3) {
            System.out.print("order must be greater than 2");
            System.exit(0);
        }
        this.order = order;
        root = new Node(true, true);
        head = root;
    }
    //把节点写入文件
    public void writeLeaf(Long point,Object obj) throws IOException {
        RandomAccessFile file=new RandomAccessFile("D:\\Internet\\MyProject\\Bolt\\1.txt", "rwd");
        file.seek(point);
    ObjectOutputStream obi = null;
    ByteArrayOutputStream bai = null;
        try {
        bai = new ByteArrayOutputStream();
        obi = new ObjectOutputStream(bai);
        obi.writeObject(obj);
        byte[] byt = bai.toByteArray();
        byte[] temp=new byte[300];
        for(int i=0;i<temp.length;i++){

            temp[i]=byt[i];
            if(byt.length-1==i){
                break;
            }
        }
        file.write(temp);
    } catch (IOException e) {
        e.printStackTrace();
    }
        obi.close();
        bai.close();
        file.close();
}
    //从文件读取根节点
    public Object readLeaf(Long point) throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:\\Internet\\MyProject\\Bolt\\1.txt", "rwd");
        file.seek(point);
        byte[] b = new byte[300];
        file.read(b);
        ObjectInputStream oii = null;
        ByteArrayInputStream bis = null;
        bis = new ByteArrayInputStream(b);
        Object obj=null;
        try {
            oii = new ObjectInputStream(bis);
            obj= oii.readObject();

            file.close();
            oii.close();
            bis.close();
            return obj;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    //读取索引
    public static  BplusTree readNode() throws IOException, ClassNotFoundException {
        FileInputStream file=new FileInputStream("D:\\Internet\\MyProject\\Bolt\\index1.txt");
        byte[] j=new byte[file.available()];
        file.read(j);
        ObjectInputStream oit=null;
        ByteArrayInputStream bai=null;
        bai=new ByteArrayInputStream(j);
        oit=new ObjectInputStream(bai);
        BplusTree bt= (BplusTree) oit.readObject();
        oit.close();
        bai.close();
        file.close();
        return bt;

    }
    //保存索引
    public  static void writeNode(BplusTree bt) throws IOException {

        File f=new File("D:\\Internet\\MyProject\\Bolt\\index1.txt");
        if(f.isFile()&&f.exists()){
            f.delete();
        }
        FileOutputStream file=new FileOutputStream("D:\\Internet\\MyProject\\Bolt\\index1.txt");
        ObjectOutputStream obi = null;
        ByteArrayOutputStream bai = null;
        try {
            bai = new ByteArrayOutputStream();
            obi = new ObjectOutputStream(bai);
            obi.writeObject(bt);
            byte[] byt = bai.toByteArray();
            file.write(byt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        obi.close();
        bai.close();
        file.close();
    }
    //构建索引
    public void makeIndex(Object id,Object name,BplusTree tree) throws IOException {
                        tree.insertOrUpdate((Comparable) id, name);

            }
        //测试
    public static void main(String[] args) throws IOException {

     /*   BplusTree tree = new BplusTree(6);
        Random random = new Random();
        long current = System.currentTimeMillis();
        for (int j = 0; j < 100000; j++) {
            for (int i = 0; i < 100; i++) {
                int randomNumber = random.nextInt(1000);
                tree.insertOrUpdate(randomNumber, randomNumber,tree);
            }

            for (int i = 0; i < 100; i++) {
                int randomNumber = random.nextInt(1000);
                tree.remove(randomNumber);
            }
        }

        long duration = System.currentTimeMillis() - current;
        System.out.println("time elpsed for duration: " + duration);
        int search = 80;
        System.out.print(tree.get(search));*/

    }

}
