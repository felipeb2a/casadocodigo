package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	/*
	 * O método validate recebe como argumento o objeto a ser validado, no caso
	 * especifcado pelo parâmetro target e um outro objeto do tipo Errors, onde
	 * vamos guardando cada uma das falhas de validação. A classe ValidationUtils é
	 * um helper do Spring Validation para realizar algumas validações básicas
	 */
	
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required");

		Product product = (Product) target;
		if (product.getPages() == 0) {
			errors.rejectValue("pages", "field.required");
		}
	}
	
}
