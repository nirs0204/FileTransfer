package trans;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ServeurPrinc {
    public static void main(String[] args) throws Exception{
        String nomfich = mandray();
        String path="D:\\JDK\\FileTransfert1.1\\DataPrinc\\"+nomfich;
        InetAddress IP=InetAddress.getLocalHost();

        JFrame fenetre = new JFrame();
        fenetre.setTitle("Serveur Principal");
        fenetre.setSize(500, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);
        fenetre.setLayout(new GridLayout(2, 1));

        JPanel pane1 = new JPanel();
        JPanel pane = new JPanel();
        JLabel label = new JLabel(nomfich);
        JLabel label1 = new JLabel("Adresse IP du serveur pour transferer le fichier : ");
        JTextField text1 = new JTextField();
        text1.setPreferredSize(new Dimension(150, 50));
        JButton bouton = new JButton("Transferer");
        
        bouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                if (ev.getSource().equals(bouton)) {
                    try {
                        mandefa(path, InetAddress.getByName(text1.getText()));
                        //mandefa(path, IP);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        pane1.add(label);
        pane.add(label1);
        pane.add(text1);
        pane.add(bouton);
        fenetre.add(pane1);
        fenetre.add(pane);
        fenetre.setVisible(true);
    }

    public static String mandray()throws Exception{
        Socket sock = new ServerSocket(9001).accept();
        InputStream inStream=sock.getInputStream();
        ObjectInputStream in = new ObjectInputStream(inStream);
        
        String mess=in.readUTF();

        File fichierRecu=new File("D:\\JDK\\FileTransfert1.1\\DataPrinc\\"+mess);
        FileOutputStream outStream=new FileOutputStream(fichierRecu);
        Transfert.transfert(in, outStream, true);

        sock.close();

        return mess;
    }

    public static void mandefa(String path, InetAddress IP)throws Exception{
        Socket sock2 = new Socket(IP,9002);
        OutputStream outStream = sock2.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outStream);

        File fichierTransfere = new File(path);
        FileInputStream fileStream = new FileInputStream(fichierTransfere);
        
        String nom = fichierTransfere.getName();
        String[] mess={"Fichier transfere", nom};
        out.writeInt(mess.length);
        for (int i=0; i<mess.length; i++) {
            out.writeUTF(mess[i]);
        }
        
        Transfert.transfert(fileStream, out, true);

        sock2.close();
    }
    
}
