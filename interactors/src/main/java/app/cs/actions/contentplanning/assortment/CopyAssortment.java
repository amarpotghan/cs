package app.cs.actions.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.assortment.AssortmentRepository;
import app.cs.model.request.CopyAssortmentRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

@Component
public class CopyAssortment implements Interactor {

	private AssortmentRepository assortmentRepository;

	@Autowired
	public CopyAssortment(AssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	public ResponseModel execute(RequestModel request) {
		CopyAssortmentRequest copyAssortmentRequest = (CopyAssortmentRequest) request;
		assortmentRepository.copy(copyAssortmentRequest.getAssortment(),
				copyAssortmentRequest.getNewPath());

		return new EmptyResponse();

	}
}
