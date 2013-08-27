package app.cs.actions.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.assortment.AssortmentRepository;
import app.cs.impl.model.Assortment;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

@Component
public class CreateAssortment {

	private AssortmentRepository assortmentRepository;

	@Autowired
	public CreateAssortment(AssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	public ResponseModel execute(Assortment assortment, String path) {

		assortmentRepository.save(assortment, path);
		return new EmptyResponse();

	}

}
