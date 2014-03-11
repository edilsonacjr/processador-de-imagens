package testes;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * Lendo imagem usando apenas APIs nativas
 *
 * @author ERCEMAPI 2009
 */
public class ExibeImagem extends JFrame {
// Construtor padrão: define interface e exibe a imagem lida na mesma

    public ExibeImagem() throws IOException {
        BufferedImage imagem = ImageIO.read(new File("Lenna.jpg"));
        String infoImagem = "Dimensões: " + imagem.getWidth() + "x" + imagem.getHeight()
                + " Bandas: "
                + imagem.getRaster().getNumBands();
        ImageIcon icone = new ImageIcon(imagem);
        JLabel labImagem = new JLabel(icone);
        this.setTitle("Display da Imagem: " + "Lenna.jpg");
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(labImagem), BorderLayout.CENTER);
        contentPane.add(new JLabel(infoImagem), BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(imagem.getWidth(), imagem.getHeight());
        this.setVisible(true);
    } // fim do construtor
// Método principal

    public static void main(String args[]) {
        try { // tratamento de exceção de E/S
            ExibeImagem appExibeImg = new ExibeImagem();
        } catch (IOException exc) {
            System.out.println("Erro de leitura! " + exc.getMessage());
        }
    } // fim do método principal
} // fim da classe

