package app.cs.actions.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.Assortment.AssortmentRepository;
import app.cs.model.Assortment;
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
