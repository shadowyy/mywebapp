package nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author shadowyy
 * @version 2017/8/17 10:07
 */
public class TestNio {
    public static void main(String[] args) throws Exception {
        //test1();
        //test2();
        test3();
    }

    private static void test1() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("d:/TestEncoding.java", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);
        ByteBuffer buf2 = ByteBuffer.allocate(16);

        ByteBuffer[] byteBuffers={buf,buf2};

        //buf.put("dasdad".getBytes());
        long bytesRead;
        while ((bytesRead = inChannel.read(byteBuffers)) != -1) {
            System.out.println("Read " + bytesRead);
            //把buffer从写模式调整为读模式；在读模式下，可以读取所有已经写入的数据
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
                //从buffer读数据到channel
                //int bytesWritten = inChannel.write(buf);
            }
            //clear会清空整个buffer，compact则只清空已读取的数据，未被读取的数据会被移动到buffer的开始位置，写入位置则近跟着未读数据之后
            buf.clear();
        }
        aFile.close();
    }

    private static void test2() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("d:/TestEncoding.java", "rw");
        FileChannel      fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("d:/xxx.java", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count    = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
    }

    private static void test3() throws Exception {

        RandomAccessFile fromFile = new RandomAccessFile("d:/1.txt", "rw");
        FileChannel      fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("d:/2.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count    = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
    }
}
