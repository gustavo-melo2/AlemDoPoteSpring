package com.youngdevs.alemdopote.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "CONTEUDO_ALEMDOPOTE")
public class Conteudo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message= "O campo nome é obrigatório")
	private String nome;
	
	@Min(value = 0, message="O campo quantidade ideal diaria não pode ser menor que 0")
    private Double quantIdealDiaria;

    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getQuantIdealDiaria() {
		return quantIdealDiaria;
	}

	public void setQuantIdealDiaria(Double quantIdealDiaria) {
		this.quantIdealDiaria = quantIdealDiaria;
	}
    
    
}
