package testes;

import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author mustaq
 */
public class ImagePanel extends JPanel implements ActionListener {

    JButton browse;
    Connection con = null;

    public ImagePanel() {
        con = this.getConnection();
        browse = new JButton("Browse");
        browse.addActionListener(this);
        this.add(browse);
    }

    public Connection getConnection() {
        try {
// Creating connection to DB
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/image";
            Connection c = DriverManager.getConnection(url, "root", "");
            return c;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public void imageWrite(File file) {
        try {

            FileInputStream io = new FileInputStream(file);
            String query = "insert into image(IMG) values(?)";
            java.sql.PreparedStatement stmt = con.prepareStatement(query);
            stmt.setBinaryStream(1, (InputStream) io, (int) file.length());
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public BufferedImage getImageById(int id) {
        String query = "select IMG from image where IMG_ID = ?";
        BufferedImage buffimg = null;
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            result.next();
            InputStream img = result.getBinaryStream(1); // reading image as InputStream
            buffimg = ImageIO.read(img); // decoding the inputstream as BufferedImage

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return buffimg;
    }

    @Override
    public void paint(Graphics g) {

        BufferedImage img = this.getImageById(5); // pass valid IMG_ID
        if (img != null) {
            g.drawImage(img, 70, 20, this);
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ImagePanel Demo");
        ImagePanel imgPanel = new ImagePanel();
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(imgPanel);

    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        File file = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile(); // path to image
            this.imageWrite(file); // inserting image into database
            JOptionPane.showMessageDialog(this, "Image inserted.", "ImageDemo", JOptionPane.PLAIN_MESSAGE);
            this.repaint();

        }
    }

}
