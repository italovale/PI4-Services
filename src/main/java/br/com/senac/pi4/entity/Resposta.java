package br.com.senac.pi4.entity;

public class Resposta {
	private Integer codGrupo;
	private Integer codQuestao;
	
	private String textoQuestao;
	private Integer codAlternativa;
	private Boolean boolQuestao;
	
	public Integer getCodGrupo() {
		return codGrupo;
	}
	public void setCodGrupo(Integer codGrupo) {
		this.codGrupo = codGrupo;
	}
	public Integer getCodQuestao() {
		return codQuestao;
	}
	public void setCodQuestao(Integer codQuestao) {
		this.codQuestao = codQuestao;
	}
	public Integer getCodAlternativa() {
		return codAlternativa;
	}
	public void setCodAlternativa(Integer codAlternativa) {
		this.codAlternativa = codAlternativa;
	}
	public Boolean getBoolQuestao() {
		return boolQuestao;
	}
	public void setBoolQuestao(Boolean boolQuestao) {
		this.boolQuestao = boolQuestao;
	}
	public String getTextoQuestao() {
		return textoQuestao;
	}
	public void setTextoQuestao(String textoQuestao) {
		this.textoQuestao = textoQuestao;
	}
	
	
}
