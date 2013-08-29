package app.cs.actions.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.model.Assortment;
import app.cs.interfaces.assortment.IAssortmentRepository;

@Component
public class AssortmentInteractions {

	private IAssortmentRepository assortmentRepository;

	@Autowired
	public AssortmentInteractions(IAssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	public void create(Assortment assortment, String path) {

		assortmentRepository.save(assortment, path);

	}

	public void move(Assortment assortment, String newPath) {
		assortmentRepository.copy(assortment, newPath);

	}
}
