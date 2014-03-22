package dao;

import banco.ConexaoJDBC;
import entidades.Imagem;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Edilson Anselmo Corrêa Júnior
 */
public class ImagemDao {
    private Connection conexao;

    public ImagemDao() throws SQLException {
        conexao = ConexaoJDBC.getConexao();
    }
    
    public void adiciona(Imagem item) throws SQLException {
        String sql = "insert into imagem(nome, imagem, usuario, processamento) values (?,?,?,?)";

        PreparedStatement stmt = this.conexao.prepareCall(sql);

        stmt.setString(1, item.getNome());
        stmt.setBlob(2, item.getImagem());
        stmt.setInt(3, item.getUsuario());
        stmt.setString(3, item.getProcessamento());

        stmt.execute();
        stmt.close();
    }

    public ArrayList<Imagem> getLista(int i) throws SQLException {
        String sql = "select * from imagem where usuario=?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setInt(1, i);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Imagem> itens = new ArrayList<Imagem>();
        
        while (rs.next()) {
            Imagem p = new Imagem();

            p.setNome(rs.getString("nome"));
            p.setCod(rs.getInt("cod"));
            p.setImagem((InputStream) rs.getBlob("imagem"));
            p.setProcessamento(rs.getString("processamento"));
            p.setUsuario(rs.getInt("usuario"));

            itens.add(p);
        }
        
        rs.close();
        stmt.close();
        return itens;
    }
    public void remove(Imagem u) throws SQLException {
        String sql = "delete from imagem where cod=?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setLong(1, u.getCod());

        stmt.execute();
        stmt.close();
    }
}
