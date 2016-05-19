package br.com.senac.pi4.entity;

import java.util.Date;
import java.util.List;

public class Questao {
	private Integer codQuestao;
	private String textoQuestao;
	private Integer codAssunto;
	private String codTipoQuestao;
	private Date tempo;
	private List<Alternativa> alternativas;

	public Integer getCodQuestao() {
		return codQuestao;
	}

	public void setCodQuestao(Integer codQuestao) {
		this.codQuestao = codQuestao;
	}

	public String getTextoQuestao() {
		return textoQuestao;
	}

	public void setTextoQuestao(String textoQuestao) {
		this.textoQuestao = textoQuestao;
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public Date getTempo() {
		return tempo;
	}

	public void setTempo(Date tempo) {
		this.tempo = tempo;
	}

	public String getCodTipoQuestao() {
		return codTipoQuestao;
	}

	public void setCodTipoQuestao(String codTipoQuestao) {
		this.codTipoQuestao = codTipoQuestao;
	}

	public Integer getCodAssunto() {
		return codAssunto;
	}

	public void setCodAssunto(Integer codAssunto) {
		this.codAssunto = codAssunto;
	}
	
	
}
