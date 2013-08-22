package app.cs.assortment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import app.cs.impl.Assortment.AssortmentRepository;
import app.cs.interfaces.model.Assortment;
import app.cs.interfaces.model.Product;

public class AssortmentInteractions {

	AssortmentRepository assortmentRepository;

	@Autowired
	public AssortmentInteractions(AssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	public void create(String name, String path, List<Product> products) {
		Assortment assortment = assortmentRepository.getAssortmentObject();
		assortment.setProducts(products);
		assortmentRepository.save(assortment);

	}

}
