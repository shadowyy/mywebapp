package mywebapp;

import org.apache.poi.util.IOUtils;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author shadowyy
 * @version 2017/8/15 15:00
 */
public class ChangeLineBreakUtil {

    public static void main(String[] args) throws Exception {
        //test1();
        //byteArrayToReader();

        //String defaultCharsetName= Charset.defaultCharset().displayName();
        //System.out.println("defaultCharsetName:"+defaultCharsetName);
    }

    private static void test1() throws Exception {
        File file = new File("d:/1.txt");
        String charset = getEncoding(file);

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("d:/2.txt")), StandardCharsets.UTF_8));

        String line;
        while ((line = in.readLine()) != null) {
            out.write(line + "\n");
        }
        out.flush();
        out.close();

        //Path path = FileSystems.getDefault().getPath("d:/1.txt");
        //List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
        //for (String line : lines) {
        //    System.out.println(line);
        //}
    }

    private static String getEncoding(File file) {
        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.toByteArray(new FileInputStream(file));
        } catch (IOException e) {
            System.out.println();
        }
        String DEFAULT_ENCODING = "UTF-8";
        UniversalDetector detector = new UniversalDetector(null);
        detector.handleData(bytes, 0, bytes.length);
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        detector.reset();
        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
        }
        return encoding;
    }


    public static void byteArrayToReader() {
        String str = "1" + "\r\n2" + "\r\n3";
        byte[] content = str.getBytes();
        InputStream is = null;
        BufferedReader bfReader = null;
        try {
            is = new ByteArrayInputStream(content);
            bfReader = new BufferedReader(new InputStreamReader(is));
            String temp = null;
            while ((temp = bfReader.readLine()) != null) {
                System.out.println(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
