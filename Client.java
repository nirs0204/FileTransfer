package trans;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Client {
    public static void main(String[] args) throws Exception{
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Client");
        fenetre.setSize(400, 500);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);

        JPanel pane = new JPanel();
        JLabel label = new JLabel("Adresse IP : ");
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(300, 50));
        JLabel label1 = new JLabel("Chemin du fichier a transferer : ");
        JTextField text1 = new JTextField();
        text1.setPreferredSize(new Dimension(180, 50));
        JButton bouton = new JButton("Transferer");

        pane.add(label);
        pane.add(text);
        pane.add(label1);
        pane.add(text1);
        pane.add(bouton);
        fenetre.add(pane);

        fenetre.setVisible(true);

        bouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(bouton)) {
                    try {
                        mandefa(text1.getText(), InetAddress.getByName(text.getText()));
                        //System.out.println(text1.getText());
                        //mandefa(text1.getText(), InetAddress.getLocalHost());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }

    public static void mandefa(String path, InetAddress IP)throws Exception{
        Socket sock = new Socket(IP ,9001);
        OutputStream outStream = sock.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outStream);

        File fichierTransfere = new File(path);
        FileInputStream fileStream = new FileInputStream(fichierTransfere);
        String mess = fichierTransfere.getName();
        System.out.println(mess);
        out.writeUTF(mess);

        Transfert.transfert(fileStream, out, true);

        sock.close();
    }
}
