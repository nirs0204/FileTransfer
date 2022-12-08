package trans;
import java.io.*;
import java.net.*;
public class Transfert {
    public static void transfert(InputStream in, OutputStream out, boolean closeOnExit) throws Exception{
        byte buf[] = new byte[1024];
        int n;
        while((n=in.read(buf))!=-1){
            out.write(buf,0,n);
        }

        if (closeOnExit){
            in.close();
            out.close();
        }
    }
}
