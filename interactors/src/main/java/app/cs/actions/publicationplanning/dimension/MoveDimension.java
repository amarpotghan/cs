package app.cs.actions.publicationplanning.dimension;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.dimension.DimensionRepository;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.model.request.MoveDimensionRequest;
import app.cs.model.request.RequestModel;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

public class MoveDimension implements Interactor {

	private IDimensionRepository dimensionRepository;

	public MoveDimension(IDimensionRepository dimensionRepository) {

		this.dimensionRepository = dimensionRepository;
	}

	public ResponseModel execute(RequestModel request) {

		MoveDimensionRequest dimensionRequest = (MoveDimensionRequest) request;
		dimensionRepository.move(dimensionRequest.getOldPath(),
				dimensionRequest.getNewPath(),
				dimensionRequest.getObjectInMove());
		return new EmptyResponse();

	}
}
