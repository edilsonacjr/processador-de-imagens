/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Imagem;
import entidades.Usuario;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import rmi.InterfaceRmi;

/**
 *
 * @author edilson
 */
public class Principal extends javax.swing.JFrame {

    private static Usuario usuario;
    private static Registry r;
    public static InterfaceRmi servico;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Principal.usuario = usuario;
    }

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setEnabled(false);
        new Login(this).setVisible(true);
        try {
            r = LocateRegistry.getRegistry("127.0.0.1", 1100);
            servico = (InterfaceRmi) r.lookup("rmi");
        } catch (RemoteException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateT() throws SQLException, RemoteException {
        ArrayList<Imagem> us = this.servico.getImagens(usuario.getCod());
        String[][] data = new String[us.size()][3];
        ArrayList<String> usernames = new ArrayList<>();
        int i = 0;
        for (Imagem u : us) {
            data[i][0] = String.valueOf(u.getCod());
            data[i][1] = u.getNome();
            data[i][2] = u.getProcessamento();
            i++;
        }

        String[] columnNames = {"Código", "Nome", "Processamento"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jtbImagens.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbImagens = new javax.swing.JTable();
        jbtSair = new javax.swing.JButton();
        jbtImportar = new javax.swing.JButton();
        jbtProcessar = new javax.swing.JButton();
        jbtVisualizar = new javax.swing.JButton();
        jbtProcessarTodas = new javax.swing.JButton();
        jbtRemover = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Processador de Imagens");

        jtbImagens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtbImagens);

        jbtSair.setText("Sair");
        jbtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSairActionPerformed(evt);
            }
        });

        jbtImportar.setText("Importar Imagem");
        jbtImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtImportarActionPerformed(evt);
            }
        });

        jbtProcessar.setText("Processar Imagem");
        jbtProcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtProcessarActionPerformed(evt);
            }
        });

        jbtVisualizar.setText("Visualizar Imagem");
        jbtVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVisualizarActionPerformed(evt);
            }
        });

        jbtProcessarTodas.setText("Processar Todas as Imagens");

        jbtRemover.setText("Remover Imagem");
        jbtRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoverActionPerformed(evt);
            }
        });

        jMenu1.setText("Arquivo");

        jMenuItem2.setText("Importar Imagens");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Processar Imagem");
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Sair");
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Configurações");

        jMenuItem1.setText("Usuários");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtImportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtProcessar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtVisualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtProcessarTodas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtImportar)
                        .addGap(18, 18, 18)
                        .addComponent(jbtProcessar)
                        .addGap(18, 18, 18)
                        .addComponent(jbtVisualizar)
                        .addGap(18, 18, 18)
                        .addComponent(jbtProcessarTodas)
                        .addGap(18, 18, 18)
                        .addComponent(jbtRemover)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtSair)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new UsuarioView().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jbtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbtSairActionPerformed

    private void jbtImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtImportarActionPerformed
        File fileName;
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
        String nome = fileName.toString();

        try {
            FileInputStream io = new FileInputStream(fileName);
            Imagem i = new Imagem();
            i.setNome(nome.substring(nome.lastIndexOf("/") + 1));
            i.setImagem(new ImageIcon(ImageIO.read(io)));
            i.setUsuario(usuario.getCod());
            i.setProcessamento("Nenhum");
            this.servico.inserirImagem(i);
            updateT();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jbtImportarActionPerformed

    private void jbtVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVisualizarActionPerformed
        Image img = null;
        try {
            Imagem i = this.servico.getImagem(Integer.valueOf((String) jtbImagens.getValueAt(jtbImagens.getSelectedRow(), 0)));
            img = i.getImagem().getImage();

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
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtVisualizarActionPerformed

    private void jbtProcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtProcessarActionPerformed

        try {
            Imagem i = this.servico.getImagem(Integer.valueOf((String) jtbImagens.getValueAt(jtbImagens.getSelectedRow(), 0)));
            new ProcessamentoView(this, i).setVisible(true);
        } catch (Exception ex) {

        }

    }//GEN-LAST:event_jbtProcessarActionPerformed

    private void jbtRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoverActionPerformed

        try {
            this.servico.removerImagem(Integer.valueOf((String) jtbImagens.getValueAt(jtbImagens.getSelectedRow(), 0)));
            updateT();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jbtRemoverActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtImportar;
    private javax.swing.JButton jbtProcessar;
    private javax.swing.JButton jbtProcessarTodas;
    private javax.swing.JButton jbtRemover;
    private javax.swing.JButton jbtSair;
    private javax.swing.JButton jbtVisualizar;
    private javax.swing.JTable jtbImagens;
    // End of variables declaration//GEN-END:variables
}
