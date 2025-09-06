package dao;

import database.ConexaoDB;
import model.Garagem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GaragemDAO {

    public void create(Garagem g) {
        Connection con = ConexaoDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO garagem (nome, pais, estado, cidade, bairro, rua, numero, cep) VALUES (?,?,?,?,?,?,?,?)");
            stmt.setString(1, g.getNome());
            stmt.setString(2, g.getPais());
            stmt.setString(3, g.getEstado());
            stmt.setString(4, g.getCidade());
            stmt.setString(5, g.getBairro());
            stmt.setString(6, g.getRua());
            stmt.setString(7, g.getNumero());
            stmt.setString(8, g.getCep());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Garagem salva com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConexaoDB.closeConnection(con, stmt);
        }
    }

    // READ - Ler todas as garagens
    public List<Garagem> read() {
        Connection con = ConexaoDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Garagem> garagens = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM garagem");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Garagem garagem = new Garagem();

                garagem.setId(rs.getInt("id"));
                garagem.setNome(rs.getString("nome"));
                garagem.setPais(rs.getString("pais"));
                garagem.setEstado(rs.getString("estado"));
                garagem.setCidade(rs.getString("cidade"));
                garagem.setBairro(rs.getString("bairro"));
                garagem.setRua(rs.getString("rua"));
                garagem.setNumero(rs.getString("numero"));
                garagem.setCep(rs.getString("cep"));

                garagens.add(garagem);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler dados: " + ex);
        } finally {
            ConexaoDB.closeConnection(con, stmt, rs);
        }

        return garagens;
    }

    // UPDATE - Atualizar uma garagem
    public void update(Garagem g) throws SQLException {
        Connection con = ConexaoDB.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE garagem SET nome = ?, pais = ?, estado = ?, cidade = ?, bairro = ?, rua = ?, numero = ?, cep = ? WHERE id = ?");

            stmt.setString(1, g.getNome());
            stmt.setString(2, g.getPais());
            stmt.setString(3, g.getEstado());
            stmt.setString(4, g.getCidade());
            stmt.setString(5, g.getBairro());
            stmt.setString(6, g.getRua());
            stmt.setString(7, g.getNumero());
            stmt.setString(8, g.getCep());
            stmt.setInt(9, g.getId());

            stmt.executeUpdate();

        } finally {
            ConexaoDB.closeConnection(con, stmt);
        }
    }

    // DELETE - Excluir uma garagem
    public void delete(int id) {
        Connection con = ConexaoDB.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM garagem WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConexaoDB.closeConnection(con, stmt);
        }
    }
}
