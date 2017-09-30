package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author shadowyy
 * @version 2017/9/29 13:11
 */
public class Test {
    public static void main(String[] args) throws Exception {
        test1();
    }

    /**
     * 1.传统IO中，Stream是单向的，Channel是双向的
     * 2.传统IO中数组，类似在NIO中，读取的数据只能放在Buffer中。同样地，写入数据也是先写入到Buffer中。
     * 3.Selector的作用就是用来轮询每个注册的Channel，一旦发现Channel有注册的事件发生，便获取事件然后进行处理
     */
    private static void test0() throws Exception {
        InputStream inputStream = new FileInputStream(new File("d:/documents/鲁大师详细报表.txt"));
        inputStream.read(new byte[1024]);
        inputStream.close();
    }

    /**
     * 常用的channel的有：
     * FileChannel
     * SocketChanel 以TCP来向网络连接的两端读写数据
     * ServerSocketChannel 能够监听客户端发起的TCP连接，并为每个TCP连接创建一个新的SocketChannel来进行数据读写
     * DatagramChannel 以UDP协议来向网络连接的两端读写数据
     *
     * @throws Exception
     */
    private static void test1() throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/1.txt"));
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put("adsdadasd".getBytes());//通过Buffer的put()方法写到Buffer里。
        byteBuffer.flip();//将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。
        fileChannel.write(byteBuffer);


        //Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）。




        fileChannel.close();
        fileOutputStream.close();
    }
}
