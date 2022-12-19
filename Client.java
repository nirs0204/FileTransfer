package trans;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class Client {
    public static File fichier;
    public static String nomFichier;
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
        // JLabel label1 = new JLabel("Chemin du fichier a transferer : ");
        // JTextField text1 = new JTextField();
        // text1.setPreferredSize(new Dimension(180, 50));
        JButton bouton = new JButton("Transferer");
        JButton button = new JButton("Choisir un fichier");

        JLabel text1 = new JLabel("Choisir un fichier d'abord");

        pane.add(label);
        pane.add(text);
        // pane.add(label1);
        pane.add(text1);
        pane.add(button);
        pane.add(bouton);
        fenetre.add(pane);

        fenetre.setVisible(true);

        bouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(bouton)) {
                    try {
                        if (fichier == null) {
                            throw new Exception("Choisissez un fichier s'il vous plait");
                        }
                        mandefa(InetAddress.getByName(text.getText()));
                        //System.out.println(text1.getText());
                    } catch (Exception ex) {
                        JFrame frame=new JFrame();
                        JOptionPane.showMessageDialog(frame, ex.getMessage());

                    }
                }
            }
        });

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(button)) {
                    JFileChooser choose = new JFileChooser();
                    // Ouvrez le fichier
                    int res = choose.showOpenDialog(null);
                    // Enregistrez le fichier
                    // int res = choose.showSaveDialog(null);

                    if (res == JFileChooser.APPROVE_OPTION) {
                        fichier = choose.getSelectedFile();
                        text1.setText("Le fichier que vous avez choisi : "+fichier.getName());
                        // nomFichier = fichier.getName();
                        // System.out.println(file.getAbsolutePath());
                        // System.out.println(file.getName());
                    }
                }
            }
        });
    }

    public static void mandefa(InetAddress IP)throws Exception{
        try {
            Socket sock = new Socket(IP ,9001);
        OutputStream outStream = sock.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outStream);

        File fichierTransfere = new File(fichier.getAbsolutePath());
        FileInputStream fileStream = new FileInputStream(fichierTransfere);
        System.out.println(fichierTransfere.getAbsolutePath());
        String mess = fichier.getName();
        System.out.println(mess);
        out.writeUTF(mess);

        Transfert.transfert(fileStream, out, true);

        sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
