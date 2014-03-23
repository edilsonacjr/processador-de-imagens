package testes;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
        File fileName = new File("/home/edilson/teste3.jpg");
        FileInputStream io = new FileInputStream(fileName);
        ImageIcon i = new ImageIcon(ImageIO.read(io));

        Image image = i.getImage();
        BufferedImage bImage = (BufferedImage) image;


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", baos);
        byte[] res = baos.toByteArray();

        InputStream is = new ByteArrayInputStream(res);

        ImageIcon ii = new ImageIcon(ImageIO.read(is));

        Image img = ii.getImage();

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
