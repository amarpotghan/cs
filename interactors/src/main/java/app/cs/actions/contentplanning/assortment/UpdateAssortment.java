package app.cs.actions.contentplanning.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;
import org.springframework.stereotype.Component;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.assortment.AssortmentRepository;
import app.cs.impl.model.Assortment;
import app.cs.model.request.RequestModel;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

@Component
public class UpdateAssortment implements Interactor {

	private AssortmentRepository assortmentRepository;

	@Autowired
	public UpdateAssortment(AssortmentRepository assortmentRepository) {
		this.assortmentRepository = assortmentRepository;

	}

	@Override
	public ResponseModel execute(RequestModel requestModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
