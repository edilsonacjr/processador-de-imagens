package dao;

import banco.ConexaoJDBC;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Edilson Anselmo Corrêa Júnior
 */
public class UsuarioDao {
    private Connection conexao;

    public UsuarioDao() throws SQLException {
        conexao = ConexaoJDBC.getConexao();
    }

    public void adiciona(Usuario item) throws SQLException {
        String sql = "insert into usuario(username, senha, admin) values (?,?,?)";

        PreparedStatement stmt = this.conexao.prepareCall(sql);

        stmt.setString(1, item.getUsername());
        stmt.setString(2, item.getSenha());
        stmt.setBoolean(3, item.isAdmin());

        stmt.execute();
        stmt.close();
    }

    public ArrayList<Usuario> getLista() throws SQLException {
        String sql = "select * from autor";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Usuario> itens = new ArrayList<Usuario>();
        
        while (rs.next()) {
            Usuario p = new Usuario();

            p.setUsername(rs.getString("username"));
            p.setCod(rs.getInt("cod"));
            p.setSenha(rs.getString("senha"));
            p.setAdmin(rs.getBoolean("admin"));

            itens.add(p);
        }
        
        rs.close();
        stmt.close();
        return itens;
    }
    public void remove(Usuario u) throws SQLException {
        String sql = "delete from usuario where cod=?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setLong(1, u.getCod());

        stmt.execute();
        stmt.close();
    }
    public boolean valida(Usuario u) throws SQLException{
        String sql = "select from usuario where senha like ? and username like ?";
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        
        stmt.setString(1, u.getSenha());
        stmt.setString(2, u.getUsername());
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next())
            return true;
        return false;
    }
}
