package br.com.senac.pi4.entity;

public class Evento {

	private Integer codEvento;
	private String descricao;
	//private Data data;
	private Integer codTipoEvento;
	private String codStatus;
	private Integer codProfessor;
	private String identificador;
	
	public Integer getCodEvento() {
		return codEvento;
	}
	public void setCodEvento(Integer codEvento) {
		this.codEvento = codEvento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getCodTipoEvento() {
		return codTipoEvento;
	}
	public void setCodTipoEvento(Integer codTipoEvento) {
		this.codTipoEvento = codTipoEvento;
	}
	public String getCodStatus() {
		return codStatus;
	}
	public void setCodStatus(String codStatus) {
		this.codStatus = codStatus;
	}
	public Integer getCodProfessor() {
		return codProfessor;
	}
	public void setCodProfessor(Integer codProfessor) {
		this.codProfessor = codProfessor;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
}
