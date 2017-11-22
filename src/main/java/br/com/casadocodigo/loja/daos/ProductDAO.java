package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Product;

//A annotation @Repository, apenas indica que essa classe, além de ser gerenciada
//pelo Spring, é também responsável pelo acesso a dados
@Repository
public class ProductDAO {

	// a annotation @PersistenceContext faz a injeção do EntityManager
	@PersistenceContext
	private EntityManager manager;

	public void save(Product product) {

		manager.persist(product);
	}

	public List<Product> list() {
		return manager.createQuery("select distinct(p) from Product p join fetch p.prices", Product.class)
				.getResultList();
	}
}
