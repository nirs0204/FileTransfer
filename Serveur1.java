package trans;
import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;

public class Serveur1 {
    public static void main(String[] args) throws Exception{
        String[] mess = mandray();

        JFrame fenetre = new JFrame();
        fenetre.setTitle("Serveur");
        fenetre.setSize(800, 100);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);

        JPanel pane = new JPanel();
        JLabel[] label = new JLabel[mess.length];
        for (int i = 0; i < label.length; i++) {
            label[i] = new JLabel(mess[i]);
            pane.add(label[i]);
        }
        fenetre.add(pane);
        fenetre.setVisible(true);
        
    }

    public static String[] mandray()throws Exception{
        Socket sock4 = new ServerSocket(9002).accept();

        InputStream inStream=sock4.getInputStream();
        ObjectInputStream in = new ObjectInputStream(inStream);
        int n = in.readInt();
        String[] mess = new String[n];
        for (int i = 0; i < mess.length; i++) {
            mess[i] = in.readUTF();
        }
        File fichierRecu=new File("D:\\JDK\\FileTransfert1.1\\Data1\\"+mess[mess.length-1]);
        FileOutputStream outStream=new FileOutputStream(fichierRecu);
        Transfert.transfert(in, outStream, true);

        sock4.close();
        return mess;
    }
}
