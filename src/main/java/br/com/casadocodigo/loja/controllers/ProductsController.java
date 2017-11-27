package br.com.casadocodigo.loja.controllers;

import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
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
	@Autowired
	private FileSaver fileSaver;
	// @InitBinder
	// protected void initBinder(WebDataBinder binder) {
	// binder.setValidator(new ProductValidator());
	// }

	// classe ModelAndView possui métodos que nos permitem ir adicionando objetos
	// que serão
	// disponibilizados na view
	@RequestMapping("/form")
	public ModelAndView form(@ModelAttribute Product product) {
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, name = "saveProduct")
	/*
	 * @Valid A annotation @Valid vem da especifcação Bean Validation, e é utilizada
	 * por diversos frameworks para indicar o disparo do processo de validação.
	 * 
	 * @ModelAttribute Estamos sempre confgurando o atributo commandName com o mesmo
	 * nome do tipo do parâmetro recebido no método. Caso você queira alterar isso,
	 * por exemplo passando o valor “objetoAtual”, pode utilizar a
	 * annotation @ModelAttribute.
	 *
	 * #Part summary especifcação de Servlets e a interface responsável por
	 * representar o arquivo que foi enviado pelo formulário. Perceba que o nome do
	 * argumento é o mesmo nome do input que declaramos no formulário
	 */
	public ModelAndView save(MultipartFile summary, @ModelAttribute("product") @Valid Product product,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return form(product);
		}
		
		System.out.println(summary.getName() + ";" + summary.getOriginalFilename());

		String webPath = fileSaver.write("uploaded-images", summary);
		product.setSummaryPath(webPath);
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
	}

}
