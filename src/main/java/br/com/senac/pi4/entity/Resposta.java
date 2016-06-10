package br.com.senac.pi4.entity;

public class Resposta {
	private Integer codGrupo;
	private Integer codQuestao;
	
	private String textoQuestao;
	private Integer codAlternativa;
	private Boolean correta;
	
	private String codTipoQuestao;
	
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

	public String getTextoQuestao() {
		return textoQuestao;
	}
	public void setTextoQuestao(String textoQuestao) {
		this.textoQuestao = textoQuestao;
	}
	public Boolean getCorreta() {
		return correta;
	}
	public void setCorreta(Boolean correta) {
		this.correta = correta;
	}
	public String getCodTipoQuestao() {
		return codTipoQuestao;
	}
	public void setCodTipoQuestao(String codTipoQuestao) {
		this.codTipoQuestao = codTipoQuestao;
	}
	
	
}
