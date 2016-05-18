package br.com.senac.pi4.entity;

public class Participante {

	private Integer codParticipante;
	private String nmParticipante;
	private Integer codCurso;
	private String email;
	private String senha;
	private Boolean ativo;
	
	public Integer getCodParticipante() {
		return codParticipante;
	}
	public void setCodParticipante(Integer codParticipante) {
		this.codParticipante = codParticipante;
	}
	public String getNmParticipante() {
		return nmParticipante;
	}
	public void setNmParticipante(String nmParticipante) {
		this.nmParticipante = nmParticipante;
	}
	public Integer getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(Integer codCurso) {
		this.codCurso = codCurso;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	

}
