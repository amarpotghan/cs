package app.cs.actions.publicationplanning.dimension;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.dimension.DimensionRepository;
import app.cs.model.request.EditDimensionRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

@Component
public class EditDimension implements Interactor {

	private DimensionRepository dimensionRepository;

	public EditDimension(DimensionRepository dimensionRepository) {
		this.dimensionRepository = dimensionRepository;
	}

	@Override
	public ResponseModel execute(RequestModel requestMdel) {
		EditDimensionRequest request = (EditDimensionRequest) requestMdel;
		dimensionRepository.save(request.getDimensionalObject());
		return new EmptyResponse();
	}
}