package br.com.casadocodigo.loja.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.Price.BookType;
import br.com.casadocodigo.loja.models.Product;
import br.com.casadocodigo.loja.validation.ProductValidator;

@Controller

// Precisamos informar que os métodos da
// ProductsController precisam de transação.
@Transactional

// Spring MVC permite que você anote
// um endereço base diretamente sobre a classe
@RequestMapping("/produtos")
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

	// @RequestMapping("/form")
	// public String form() {
	// return "products/form";
	// }

	/*
	 * A annotation @InitBinder indica que esse método deve ser chamado sempre que
	 * um request cair no controller em questão.
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new ProductValidator());
	}

	// classe ModelAndView possui métodos que nos permitem ir adicionando objetos
	// que serão
	// disponibilizados na view
	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}

//	 @RequestMapping(method = RequestMethod.POST)
//	 public String save(Product product) {
//	 productDAO.save(product);
//	 return "redirect:produtos";
//	 }

	@RequestMapping(method = RequestMethod.POST)
<<<<<<< HEAD

	/*
	 * A annotation @Valid vem da especifcação Bean Validation, e é utilizada por
	 * diversos frameworks para indicar o disparo do processo de validação.
	 */
	public ModelAndView save(@Valid Product product, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return form();
		}
=======
	public ModelAndView save(Product product) {
>>>>>>> parent of a36cf72... 4.7 Parâmetros extras nos redirects
		productDAO.save(product);
		return new ModelAndView("redirect:produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
	}

}
