package app.cs.actions.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.assortment.IAssortmentRepository;
import app.cs.model.request.CopyAssortmentRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

@Component
public class CopyAssortment implements Interactor {

	private IAssortmentRepository assortmentRepository;

	@Autowired
	public CopyAssortment(IAssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	public ResponseModel execute(RequestModel request) {
		CopyAssortmentRequest copyAssortmentRequest = (CopyAssortmentRequest) request;
		MultiDimensionalObject assortmentObject = assortmentRepository
				.getDomain("MultiDimensionalObject");

		Assortment assortment = copyAssortmentRequest.getAssortment();
		String newPath = copyAssortmentRequest.getNewPath();
		assortmentRepository.copy(assortmentObject, newPath);

		return new EmptyResponse();

	}
}
