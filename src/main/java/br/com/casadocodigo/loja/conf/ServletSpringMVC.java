package br.com.casadocodigo.loja.conf;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

//import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.casadocodigo.loja.controllers.AmazonConfiguration;

//import br.com.casadocodigo.loja.controllers.HomeController;
//import br.com.casadocodigo.loja.daos.ProductDAO;
/*
 * A annotation EnableWebMvc não é necessária agora, mas como ela já
 * habilita várias funcionalidades
 * Conversão de objetos para XML
 * Conversão de objetos para JSON
 * Validação usando a especifcação
 * Suporte a geração de RSS
 */
@EnableWebMvc
// Através dela indicamos quais pacotes devem ser lidos
// @ComponentScan(basePackageClasses = { HomeController.class})
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// return new Class[] { SecurityConfiguration.class, AppWebConfiguration.class,
		// JPAConfiguration.class,
		// JPAProductionConfiguration.class, AmazonConfiguration.class };
		return null;
	}

	// Aqui e onde o Spring MVC vai saber quais controllers devem ser mapeados e
	// quais outras classes devem ser carregadas pelo container do próprio Spring
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { AppWebConfiguration.class, JPAConfiguration.class, AmazonConfiguration.class };
		// return new Class[] {};
		// return null;
	}

	// Aqui é onde você diz qual é o padrão de endereço que vai ser delegado para o
	// Servlet do Spring MVC
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
