package br.com.senac.pi4.entity;

public class Alternativa {
	private Integer codQuestao;
	private Integer codAlternativa;
	private String textoAlternativa;
	
	public String getTextoAlternativa() {
		return textoAlternativa;
	}
	public void setTextoAlternativa(String textoAlternativa) {
		this.textoAlternativa = textoAlternativa;
	}
	public Integer getCodAlternativa() {
		return codAlternativa;
	}
	public void setCodAlternativa(Integer codAlternativa) {
		this.codAlternativa = codAlternativa;
	}
	public Integer getCodQuestao() {
		return codQuestao;
	}
	public void setCodQuestao(Integer codQuestao) {
		this.codQuestao = codQuestao;
	}
	
}
