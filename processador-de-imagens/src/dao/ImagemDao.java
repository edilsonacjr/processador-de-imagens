package dao;

import banco.ConexaoJDBC;
import entidades.Imagem;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Edilson Anselmo Corrêa Júnior
 */
public class ImagemDao {

    private Connection conexao;

    public ImagemDao() throws SQLException {
        conexao = ConexaoJDBC.getConexao();
    }

    public void adiciona(Imagem item) throws SQLException, IOException {
        String sql = "insert into imagem(nome, imagem, usuario, processamento) values (?,?,?,?)";

        PreparedStatement stmt = this.conexao.prepareCall(sql);

        stmt.setString(1, item.getNome());

        Image image = item.getImagem().getImage();
        BufferedImage bImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) image,"jpeg", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());

        stmt.setBlob(2, is);
        stmt.setInt(3, item.getUsuario());
        stmt.setString(4, item.getProcessamento());

        stmt.execute();
        stmt.close();
    }

    public ArrayList<Imagem> getLista(int i) throws SQLException, IOException {
        String sql = "select * from imagem where usuario=?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1, i);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Imagem> itens = new ArrayList<Imagem>();

        while (rs.next()) {
            Imagem p = new Imagem();

            p.setNome(rs.getString("nome"));
            p.setCod(rs.getInt("cod"));
            p.setImagem(new ImageIcon(ImageIO.read((InputStream) rs.getBinaryStream("imagem"))));
            p.setProcessamento(rs.getString("processamento"));
            p.setUsuario(rs.getInt("usuario"));

            itens.add(p);
        }

        rs.close();
        stmt.close();
        return itens;
    }

    public void remove(int i) throws SQLException {
        String sql = "delete from imagem where cod=?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setLong(1, i);

        stmt.execute();
        stmt.close();
    }

    public Imagem getImagem(int i) throws SQLException, IOException {
        String sql = "select * from imagem where cod=?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1, i);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Imagem> itens = new ArrayList<Imagem>();

        rs.next();
        Imagem p = new Imagem();

        p.setNome(rs.getString("nome"));
        p.setCod(rs.getInt("cod"));
        p.setImagem(new ImageIcon(ImageIO.read((InputStream) rs.getBinaryStream("imagem"))));
        p.setProcessamento(rs.getString("processamento"));
        p.setUsuario(rs.getInt("usuario"));

        rs.close();
        stmt.close();
        return p;
    }
}
