package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.Price.BookType;
import br.com.casadocodigo.loja.models.Product;

@Controller
// Precisamos informar que os métodos da
// ProductsController precisam de transação.
@Transactional
public class ProductsController {
	/*
	 * Ao inves de criarmos o objeto
	 * "private ProductDAO productDAO = new ProductDAO();" construtor do ProductDAO
	 * vai precisar receber algum objeto que represente a conexão com o banco de
	 * dados e, nesse caso, vamos ter que começar a controlar essas dependências na
	 * mão. Essa é uma ótima parte para usarmos o Spring MVC O nosso único trabalho
	 * é indicar que precisamos receber injetado uma instância do ProductDAO. A
	 * annotation @Autowired é justamente a responsável por indicar os pontos de
	 * injeção dentro da sua classe.
	 */
	@Autowired
	private ProductDAO productDAO;

	//@RequestMapping("/produtos")
	public String save(Product product) {
		productDAO.save(product);
		return "products/ok";
	}

	/**
	 * 
	 * @RequestMapping("/produtos/form") public String form() { return
	 * "products/form"; }
	 * 
	 */

	// classe ModelAndView possui métodos que nos permitem ir adicionando objetos
	// que serão
	// disponibilizados na view
	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}

}
