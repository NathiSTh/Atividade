package br.com.faculdadedelta.modelo;

public class AgenciaNathalia {
  
	private Long id;
	private String nome;
	private String codigo;
	private String digito;
	private BancoNathalia bancoPedro;
	
	public AgenciaNathalia() {
		super();
	}
	
	public AgenciaNathalia(Long id, String nome, String codigo, String digito, BancoNathalia bancoPedro) {
		super();
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
		this.digito = digito;
		this.bancoPedro = bancoPedro;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDigito() {
		return digito;
	}
	public void setDigito(String digito) {
		this.digito = digito;
	}
	public BancoNathalia getBanco() {
		return bancoPedro;
	}
	public void setBanco(BancoNathalia bancoPedro) {
		this.bancoPedro = bancoPedro;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgenciaNathalia other = (AgenciaNathalia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
