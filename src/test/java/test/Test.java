package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author shadowyy
 * @version 2017/9/4 15:42
 */
public class Test {
    public static void main(String[] args) throws Exception {
        //String tempString=null;
        //int num=0;
        //BufferedReader reader = new BufferedReader(new FileReader(new File("c:/documents/1.txt")));
        //while ((tempString = reader.readLine()) != null) {
        //    String tmp=num+"";
        //    if(num<10){
        //        tmp="00"+num;
        //    }else if(num<100){
        //        tmp="0"+num;
        //    }
        //    System.out.println("sea_"+ tmp +"_"+tempString);
        //    num++;
        //}
        //reader.close();
        test0();
        String host = "127.0.0.1";
        int port = 8888;
        Socket socket = new Socket(host, port);

        OutputStream outputStream = socket.getOutputStream();
        Writer writer= new OutputStreamWriter(outputStream);
        writer.write("xx");
        writer.flush();
        writer.close();
        socket.close();
    }

    /**
     * 找到一个集合中没有的元素
     * @throws Exception
     */
    private static void test0() throws Exception {
        String tempString=null;

        ArrayList<String> base = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("c:/documents/风控-手机.txt")));
        while ((tempString = reader.readLine()) != null) {
            base.add(tempString);
        }

        ArrayList<String> toSearch= new ArrayList<>();
        BufferedReader reader2 = new BufferedReader(new FileReader(new File("c:/documents/机器学习.txt")));
        while ((tempString = reader2.readLine()) != null) {
            toSearch.add(tempString);
        }

        int count=0;
        for (String search : toSearch) {
            if (base.contains(search)){
                System.out.println(search);
                count++;
            }
        }
        System.out.println(count);


        //int num=2;
        //for (String search : toSearch) {
        //    System.out.print(num+" "+search);
        //    for (String s : base) {
        //        if (search.equals(s)){
        //            System.out.print(" 已存在");
        //        }
        //    }
        //    System.out.println();
        //    num++;
        //}


        reader.close();
        reader2.close();
    }
}



//