package app.cs.actions.contentplanning.assortment;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.assortment.IAssortmentRepository;
import app.cs.model.request.CreateAssortmentRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

@Component
public class CreateAssortment implements Interactor {

	private IAssortmentRepository assortmentRepository;

	@Autowired
	public CreateAssortment(IAssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	public ResponseModel execute(RequestModel request) {

		CreateAssortmentRequest createAssortmentRequest = (CreateAssortmentRequest) request;
		MultiDimensionalObject assortmentObject = (MultiDimensionalObject) assortmentRepository
				.getDomain("MultiDimensionalObject");
		Assortment assortment = createAssortmentRequest.getAssortment();
		assortmentObject.setPath(createAssortmentRequest.getNewPath());
		assortment.setID(UUID.randomUUID().toString());
		assortmentRepository.save(assortmentObject);
		return new EmptyResponse();

	}

}
