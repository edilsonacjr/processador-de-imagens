package testes;

import ij.ImagePlus;
import ij.gui.ImageCanvas;
import ij.process.ByteProcessor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Lendo imagem ruidosa e extraindo o objeto
 *
 * @author ERCEMAPI 2009
 */
public class SegRdI extends JFrame {

    JPanel painelPrinc, painelBotoes;
    JButton btnAbrir, btnProc, btnLimpar, btnSair;
    File fileName;
    ImagePlus imagemIJ;

    public void iniciaComponentes() {
        this.setLayout(new BorderLayout());
        painelPrinc = new JPanel();
        painelPrinc.setLayout(new BorderLayout());
        this.add(painelPrinc, BorderLayout.CENTER);
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());
        this.add(painelBotoes, BorderLayout.SOUTH);

        btnAbrir = new JButton();
        painelBotoes.add(btnAbrir);
        btnAbrir.setText(" Abrir ... ");
        btnProc = new JButton();
        painelBotoes.add(btnProc);
        btnProc.setText("Processar");
        btnLimpar = new JButton();
        painelBotoes.add(btnLimpar);
        btnLimpar.setText(" Limpar ");
        btnSair = new JButton();
        painelBotoes.add(btnSair);
        btnSair.setText(" Sair ");

        btnAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        btnProc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                processarActionPerformed(evt);
            }
        });
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                limparActionPerformed(evt);
            }
        });
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });
        this.setVisible(true);
        this.setSize(450, 350);
        this.setDefaultCloseOperation(
                javax.swing.WindowConstants.EXIT_ON_CLOSE);

    }

    private void abrirActionPerformed(ActionEvent evt) {
        JFileChooser dialogo = new JFileChooser();
        dialogo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = dialogo.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }

        fileName = dialogo.getSelectedFile();

        if (fileName == null || fileName.getName().equals("")) {
            JOptionPane.showMessageDialog(this, "Nome de Arquivo Inválido",
                    "Nome de Arquivo Inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }
        imagemIJ = new ImagePlus(fileName.toString());
        JScrollPane sp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ImageCanvas ic = new ImageCanvas(imagemIJ);
        sp.add(ic);
        sp.setSize(imagemIJ.getWidth(), imagemIJ.getHeight());
        painelPrinc.add(sp, BorderLayout.CENTER);
    }

    private void processarActionPerformed(ActionEvent evt) {
        Image image = imagemIJ.getImage();
        ByteProcessor byteProc = new ByteProcessor(image);
        byteProc.medianFilter();
        image = byteProc.createImage();
        ImagePlus imFilt = new ImagePlus("filtragem", image);

        int max = -1;
        for (int lin = 0; lin < imFilt.getHeight(); lin++) {
            for (int col = 0; col < imFilt.getWidth(); col++) {
                int[] pixels = imFilt.getPixel(col, lin);
                if (pixels[0] > max) {
                    max = pixels[0];
                }
            }
        }
        image = imFilt.getImage();
        byteProc = new ByteProcessor(image);

        byteProc.threshold(max - 1);
        image = byteProc.createImage();
        ImagePlus imSeg = new ImagePlus("segmentacao", image);
        image = imSeg.getImage();
        byteProc = new ByteProcessor(image);

        byteProc.dilate(1,0);
        image = byteProc.createImage();
        ImagePlus imDil = new ImagePlus("dilatacao", image);
        image = imDil.getImage();
        byteProc = new ByteProcessor(image);

        byteProc.erode(1,0);
        image = byteProc.createImage();
        ImagePlus imErosao = new ImagePlus("erosao", image);
        JScrollPane sp = new JScrollPane(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ImageCanvas ic = new ImageCanvas(imErosao);
        //ImageCanvas ic = new ImageCanvas(imagemIJ);
        sp.add(ic);
        sp.setSize(imErosao.getWidth(), imErosao.getHeight());
        painelPrinc.removeAll();
        painelPrinc.add(sp, BorderLayout.CENTER);

    }

    private void limparActionPerformed(ActionEvent evt) {
        ImagePlus imp = new ImagePlus();
        ImageCanvas ic = new ImageCanvas(imp);
        painelPrinc.removeAll();
        painelPrinc.add(ic, BorderLayout.CENTER);
    }

    private void sairActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    public SegRdI() {
        this.iniciaComponentes();
    }

    public static void main(String[] args) {
        SegRdI aplicacao = new SegRdI();
    }
}
