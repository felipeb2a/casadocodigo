package br.com.casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.List;

//@Entity indica que a classe vai virar uma tabela.
@Entity
public class Product {
	// @Id: indica que o atributo em questão é a chave primária, pode-se defnir que
	// não precisa de método
	// set. A ideia da JPA é que o id é imutável, então não seria necessário acesso
	// a ele por set.
	@Id
	// @GeneratedValue: indica a maneira como vai ser gerada a chave primária.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//validar os campos em branco
	@NotBlank
	private String title;
	// @Lob: indica que o atributo em questão vai ser salvo como Clob ou
	// Blob no banco de dados.
	@Lob
	@NotBlank
	private String description;
	@Min(30)
	private int pages;
	/*
	 * @DateTimeFormat
	 *annotation indica que queremos a conversão p/ string
	 * 
	 * iso=ISO.DATE
	 * Serve para converter o formato padrao do spring de M/d/yy 
	 * para yyyy-MM-dd já que usamos o input cujo tipo é o date
	 */
	@DateTimeFormat(iso=ISO.DATE)
	private Calendar releaseDate;
	private String summaryPath;
	
	public String getSummaryPath() {
		return summaryPath;
	}

	public void setSummaryPath(String summaryPath) {
		this.summaryPath = summaryPath;
	}

	public Calendar getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}

	@ElementCollection
	private List<Price> prices = new ArrayList<Price>();

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

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

	// Metodo retorna dados do objeto de forma legivel
	// que nao seja com produst@123
	@Override
	public String toString() {
		return this.title + this.description + this.pages;
	}

}
