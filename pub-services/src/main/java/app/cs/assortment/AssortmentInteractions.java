package app.cs.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.assortment.AssortmentRepository;
import app.cs.model.Assortment;
import app.cs.model.MultiDimensionalObject;

@Component
public class AssortmentInteractions {

	private AssortmentRepository assortmentRepository;

	@Autowired
	public AssortmentInteractions(AssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	public void create(Assortment assortment, String path) {

		assortmentRepository.save(assortment, path);

	}
	
	public void move(Assortment assortment, String newPath) {
		//Assortment assortment = new Assortment();
		assortmentRepository.move(assortment, newPath);

	}
}
