package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.AgenciaNathalia;
import br.com.faculdadedelta.modelo.BancoNathalia;
import br.edu.faculdadedelta.util.Conexao;

public class AgenciaDaoNathalia {

	public void incluir(AgenciaNathalia agencia) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO agencias(nome_agencia, codigo_agencia, id_banco, digito_verificador) VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, agencia.getNome().trim());
			ps.setString(2, agencia.getCodigo().trim());
			ps.setLong(3, agencia.getBanco().getId());
			ps.setString(4, agencia.getDigito().trim());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(AgenciaNathalia agencia) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE agencias SET nome_agencia =?, codigo_agencia=?, digito_verificador=?, id_banco=? WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, agencia.getNome().trim());
			ps.setString(2, agencia.getCodigo().trim());
			ps.setString(3, agencia.getDigito().trim());
			ps.setLong(4, agencia.getBanco().getId());
			ps.setLong(5, agencia.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(AgenciaNathalia agencia) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM agencias WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, agencia.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public List<AgenciaNathalia> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT\r\n" + 
				"a.id AS idAgencia,\r\n" + 
				"a.nome_agencia AS nomeAgencia,\r\n" + 
				"a.codigo_agencia AS codigoAgencia,\r\n" + 
				"a.digito_verificador AS digito,\r\n" + 
				"b.id AS idBanco,\r\n" + 
				"b.nome_banco AS nomeBanco,\r\n" + 
				"b.codgio_banco AS codigoBanco,\r\n" + 
				"b.cnpj_banco AS cnpj\r\n" + 
				"FROM agencias a\r\n" + 
				"INNER JOIN bancos b ON a.id = b.id";

		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<AgenciaNathalia> listaRetorno = new ArrayList<AgenciaNathalia>();

		try {
			while (rs.next()) {
				AgenciaNathalia agencia = new AgenciaNathalia();
				agencia.setId(rs.getLong("idAgencia"));
				agencia.setNome(rs.getString("nomeAgencia"));
				agencia.setCodigo(rs.getString("codigoAgencia"));
				agencia.setDigito(rs.getString("digito"));

				BancoNathalia banco = new BancoNathalia();
				banco.setId(rs.getLong("idBanco"));
				banco.setNome(rs.getString("nomeBanco"));
				banco.setCodigo(rs.getString("codigoBanco"));
				banco.setCnpj(rs.getString("cnpj"));

				agencia.setBanco(banco);
				listaRetorno.add(agencia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}
}