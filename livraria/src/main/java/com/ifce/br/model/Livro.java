package com.ifce.br.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Livro {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@NotBlank(message = "Preencha o campo Titulo!")
	private String titulo;
	
	@NotBlank(message = "Preencha o campo Autor!")
	private String autor;
	
	@NotNull(message = "O campo de ISBN não pode ser nulo!")
	private Long isbn;
	
	@NotNull(message = "O campo de Preço não pode ser nulo!")
	private Long preco;

	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Long getIsbn() {
		return isbn;
	}
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	public Long getPreco() {
		return preco;
	}
	public void setPreco(Long preco) {
		this.preco = preco;
	}
	
	

}
