package com.redhat.jbpmdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

@Name("solicitacao")
@Entity
@Table(name="SOLICITACAO_HORA_EXTRA")
@SequenceGenerator(name="SOLICITACAO_GENERATOR",sequenceName="SEQ_HORAS_EXTRAS")
public class SolicitacaoHoraExtra {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String projeto;
	
	private String funcionario;
	
	private Integer qtdHoras;
	
	private Integer qtdAprovadas;
	
	private String observacoes;

	public Long getId() {
		return id;
	}

	public String getProjeto() {
		return projeto;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public Integer getQtdHoras() {
		return qtdHoras;
	}

	public Integer getQtdAprovadas() {
		return qtdAprovadas;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public void setQtdHoras(Integer qtdHoras) {
		this.qtdHoras = qtdHoras;
	}

	public void setQtdAprovadas(Integer qtdAprovadas) {
		this.qtdAprovadas = qtdAprovadas;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
}
