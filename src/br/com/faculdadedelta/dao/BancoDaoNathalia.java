package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.BancoNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class BancoDaoNathalia {

	public void incluir(BancoNathalia banco) throws Exception  {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql ="INSERT INTO bancos (nome_banco, codgio_banco, cnpj_banco) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, banco.getNome().trim());
			ps.setString(2, banco.getCodigo().trim());
			ps.setString(3, banco.getCnpj().trim());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}}
     	
	public void alterar(BancoNathalia banco) throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE bancos SET nome_banco=?, codgio_banco=?, cnpj_banco=? WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, banco.getNome().trim());
			ps.setString(2, banco.getCodigo().trim());
			ps.setString(3, banco.getCnpj().trim());
			ps.setLong(4, banco.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}}
	
	public void excluir (BancoNathalia banco) throws Exception {
		Connection conn =  Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM bancos WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
	    try {
			ps.setLong(1, banco.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	    
	}
	
	public List<BancoNathalia>listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT * FROM bancos";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<BancoNathalia> listaRetorno = new ArrayList<>();
		try {
			while(rs.next()) {
			BancoNathalia banco = new BancoNathalia();
				banco.setId(rs.getLong("id"));
				banco.setNome(rs.getString("nome_banco").trim());
				banco.setCodigo(rs.getString("codgio_banco").trim());
				banco.setCnpj(rs.getString("cnpj_banco").trim());
				
				listaRetorno.add(banco);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}
	
	public BancoNathalia pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT * FROM bancos WHERE id = ?";
		PreparedStatement ps  = null;
		ResultSet rs = null;
		BancoNathalia retorno = new BancoNathalia();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setNome(rs.getString("nome_banco").trim());
				retorno.setCodigo(rs.getString("codgio_banco").trim());
				retorno.setCnpj(rs.getString("cnpj_banco").trim());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return retorno;
	}
	
}