package com.generation.blogPessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity //create database
@Table(name="tb_postagens") //create table
public class Postagem {
	@Id //tornar esse atributo uma chave primária no banco de dados
	@GeneratedValue(strategy=GenerationType.IDENTITY) //adicionar o id como auto_increment
private Long id;
	@Size(min=5, max=100, message = "O título deve ter no minimo 5 caracteres e no máximo 100") //definir a quantidade de caracteres no titulo
	@NotBlank(message = "Atributo título é obrigatório") //não pode deixar sem título
	private String titulo;
	
	@Size(min=10, max=1000, message = "O texto deve ter no minimo 10 caracteres e no máximo 1000") //definir a quantidade de caracteres no titulo
	@NotBlank(message = "Atributo texto é obrigatório") //não pode deixar sem título
	private String texto;
	@UpdateTimestamp //pega a data do sistema ao cadastrar um 
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
}

