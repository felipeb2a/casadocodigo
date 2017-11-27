package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.infra.FileSaver;

//A annotation EnableWebMvc habilita várias funcionalidades
//- Conversão de objetos para XML
//- Conversão de objetos para JSON
//- Validação usando a especifcação
//- Suporte a geração de RSS
@EnableWebMvc

// A annotation @ComponentScan através dela indicamos quais pacotes devem ser
// lidos
// Perceba que passamos a classe HomeController
@ComponentScan(basePackageClasses = { HomeController.class, ProductDAO.class, FileSaver.class })

public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	/*
	 * A classe InternalResourceViewResolver, como pode ser lido no próprio código,
	 * guarda as confgurações da pasta base e do sufxo que devem ser adicionados
	 * para qualquer caminho que seja retornado por métodos dos controllers.
	 * 
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		// define a pasta onde serao chamadas as paginas
		resolver.setPrefix("/WEB-INF/views/");
		// define o tipo da página que vai chamar no navegador
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/*
	 * Avisa ao Spring MVC que ele deve usá-lo para buscar as mensagens relativas às
	 * chaves que estão confguradas.
	 */
	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		bundle.setCacheSeconds(1);
		return bundle;
	}

	/*
	 * Vamos ensinar ao Spring MVC que ele sempre deve usar essa formatação
	 * yyyy-MM-dd
	 * 
	 * o nome do método tem que ser mvcConversionService. Pois esse é o nome usado
	 * internamente pelo Spring MVC para registrar o objeto responsável por agrupar
	 * os conversores
	 * 
	 * DateFormatterRegistrar Ele implementa a interface FormatterRegistrar
	 */
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
}
