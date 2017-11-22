package br.com.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

//@Entity indica que a classe vai virar uma tabela.
@Entity
public class Product {
	//@Id: indica que o atributo em questão é a chave primária, pode-se defnir que não precisa de método
	//set. A ideia da JPA é que o id é imutável, então não seria necessário acesso a ele por set.
	@Id
	//@GeneratedValue: indica a maneira como vai ser gerada a chave primária.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	//@Lob: indica que o atributo em questão vai ser salvo como Clob ou
	//Blob no banco de dados.
	@Lob
	private String description;
	private int pages;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	//Metodo retorna dados do objeto de forma legivel
	//que nao seja com produst@123
	@Override
	public String toString(){
		return this.title + this.description + this.pages;
	}
	

}
