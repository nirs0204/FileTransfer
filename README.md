#Transfert.java
Dans ce class, il y a la fonction "transfert" qui a besoin de InputStream pour lire le byte du fichier transferer, OutputStream pour écrire son contenu, boolean pour 
confirmer si on doit fermer le OutputStream et le InputStream ou non. Cette fonction est pour lire le byte du fichier transferer ou reçu.
#Client.java
Dans ce class, il y a la fonction "mandefa" qui a besoin de InetAddress pour l'adresse IP du serveur principale. Dans cette fonction, on a ouvert le socket et a appelé
la fonction transfert du class Transfert.java. On y trouve aussi le "main", dans ce main, on a créé une fenetre "JFrame fenetre" et a ajouté un "JPanel". Dans ce JPanel,
on a ajouté un JTextField pour saisir l'adresse IP, des JLabel, et deux JButton : "Choisir un fichier" et "Transferer"; ces deux boutons ont chacun leur ActionListener : 
celui de "Choisir un fichier" est pour ouvrir un JFileChoose, et celui de "Transferer" pour transferer le fichier vers le serveur principal
#ServeurPrinc.java
Dans ce class, il y a la fonction "mandefa" qui a le même fonctionnalité que "mandefa" dans Client.java, et la fonction "mandray" où on ouvre le socket qui accepte le 
socket reçu et reçoit le message envoyé. On y trouve aussi le "main" où on crée une fenêtre "JFrame fenetre", on y ajoute un JPanel qui comporte des JLabel où l'un d'eux 
est le nom du fichier reçu, un JTextField pour saisir l'adresse IP du serveur à partager, et un JButton pour transferer le fichier.
#Serveur.java
Dans ce class, il y a une fonction "mandray" qui a le même fonctionnalité que "mandray" de ServeurPrinc.java.  
