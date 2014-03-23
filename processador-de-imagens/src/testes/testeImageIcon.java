package testes;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Edilson Anselmo Corrêa Júnior
 */
public class testeImageIcon {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File fileName = new File("/home/edilson/teste1.jpeg");
        FileInputStream io = new FileInputStream(fileName);
        ImageIcon i = new ImageIcon(ImageIO.read(io));

        Image img = i.getImage();
                //new BufferedImage(
                //i.getIconWidth(),
                //i.getIconHeight(),
               // BufferedImage.TYPE_INT_RGB);
        //i.getImagem();

        JFrame jFImagem = new JFrame("Imagem Carregada...");

        ImageIcon icone = new ImageIcon(img);
        JLabel labImagem = new JLabel(icone);
        Container contentPane = jFImagem.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(labImagem), BorderLayout.CENTER);
        String imageInfo = "Dimensões: " + img.getWidth(labImagem) + "x" + img.getHeight(labImagem);
        contentPane.add(new JLabel(imageInfo), BorderLayout.SOUTH);

        jFImagem.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jFImagem.setSize(800, 400);
        jFImagem.setLocation(400, 200);
        jFImagem.setVisible(true);
    }

}
