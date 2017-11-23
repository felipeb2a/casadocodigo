package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Product;

/*
 * O método validate recebe como argumento o objeto a ser validado,
 * no caso especifcado pelo parâmetro target e um outro objeto do tipo
 * Errors, onde vamos guardando cada uma das falhas de validação.
 */
public class ProductValidator implements Validator {

	/*
	 * sse método recebe a classe do objeto que está querendo ser validado e
	 * retorna se o validador consegue lidar com ele
	 * (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

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
