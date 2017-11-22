package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProductDAO;

//A annotation EnableWebMvc habilita várias funcionalidades
//- Conversão de objetos para XML
//- Conversão de objetos para JSON
//- Validação usando a especifcação
//- Suporte a geração de RSS
@EnableWebMvc

//A annotation @ComponentScan através dela indicamos quais pacotes devem ser lidos 
//Perceba que passamos a classe HomeController
@ComponentScan(basePackageClasses={HomeController.class,
		ProductDAO.class})

public class AppWebConfiguration extends WebMvcConfigurerAdapter{		
	/*
	 * A classe InternalResourceViewResolver, como pode ser lido no
	 * próprio código, guarda as confgurações da pasta base e do sufxo que devem
	 * ser adicionados para qualquer caminho que seja retornado por métodos dos controllers.
	 * 
	 * */
	@Bean
	public InternalResourceViewResolver
	internalResourceViewResolver() {
	InternalResourceViewResolver resolver =
	new InternalResourceViewResolver();
	//define a pasta onde serao chamadas as paginas
	resolver.setPrefix("/WEB-INF/views/");
	//define o tipo da página que vai chamar no navegador
	resolver.setSuffix(".jsp");
	return resolver;
	}
}
