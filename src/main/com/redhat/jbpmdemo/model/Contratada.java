package com.redhat.jbpmdemo.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.validator.Length;
import org.jboss.seam.annotations.Name;

@Name("contratada")
@Entity
@Table(name="CONTRATADA")
@SequenceGenerator(name="CONTRATADA_GENERATOR",sequenceName="SEQ_CONTRADATA"
	
)public class Contratada implements Serializable
{
private static final long serialVersionUID = -1869934584888756245L;
	private Long id;
    private Integer version;
    private String razao;
	private String cnpj;
    private String endereco;
    private String complemento;
    private String estado;
    private String faturamento;
    private String dadosContrato;
    private String contacorrente;
    private String banco;

    public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFaturamento() {
		return faturamento;
	}

	public void setFaturamento(String faturamento) {
		this.faturamento = faturamento;
	}

	public String getDadosContrato() {
		return dadosContrato;
	}

	public void setDadosContrato(String dadosContrato) {
		this.dadosContrato = dadosContrato;
	}

	public String getContacorrente() {
		return contacorrente;
	}

	public void setContacorrente(String contacorrente) {
		this.contacorrente = contacorrente;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}



    // add additional entity attributes

    // seam-gen attribute getters/setters with annotations (you probably should edit)

    @Id @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    private void setVersion(Integer version) {
        this.version = version;
    }

    @Length(max = 20)
    public String getRazao() {
        return razao;
    }

    public void setRazao(String name) {
        this.razao = name;
    }

}
