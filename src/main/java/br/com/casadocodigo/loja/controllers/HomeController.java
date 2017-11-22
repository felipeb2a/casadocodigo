//classe responsavel por atender requisicoes dos clientes

package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	// Para que o spring mvc saiba qual metodo deve ser chamadado para cada URL
	// devemos inserir a annoation @RequestMapping
	@RequestMapping("/")
	public String index() {
		System.out.println("Carregando os produtos");
		return "hello-world";
	}
}
