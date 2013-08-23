package app.cs.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.Assortment.AssortmentRepository;
import app.cs.interfaces.model.Assortment;

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

}
