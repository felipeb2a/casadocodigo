package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price {
	@Column(scale = 2)
	private BigDecimal value;
	private BookType booktpe;
	
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BookType getBooktpe() {
		return booktpe;
	}

	public void setBooktpe(BookType booktpe) {
		this.booktpe = booktpe;
	}

	public enum BookType {
		EBOOK, PRINTED, COMBO
	}
}
