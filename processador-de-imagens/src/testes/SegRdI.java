package testes;

import ij.ImagePlus;
import ij.gui.ImageCanvas;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
    }

    private void processarActionPerformed(ActionEvent evt) {
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
