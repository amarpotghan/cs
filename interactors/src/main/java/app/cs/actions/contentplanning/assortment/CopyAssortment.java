package app.cs.actions.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.assortment.AssortmentRepository;
import app.cs.model.request.CreateAssortmentRequest;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

@Component
public class CopyAssortment {

	private AssortmentRepository assortmentRepository;

	@Autowired
	public CopyAssortment(AssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	public ResponseModel execute(CreateAssortmentRequest request) {
		assortmentRepository
				.copy(request.getAssortment(), request.getNewPath());

		return new EmptyResponse();

	}
}
