package app.cs.controller.publicationplanning.dimension;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.cs.boundary.delivery.Interactor;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.EditDimensionRequest;

@Controller
public class EditDimensionController {
	private Interactor editDimension;
	private EditDimensionRequest request;

	public EditDimensionController(Interactor editDimension,
			EditDimensionRequest request) {
		this.editDimension = editDimension;
		this.request = request;
	}

	@RequestMapping(value = "/dimension/edit/{dimensionId}", method = RequestMethod.PUT)
	public void execute(MultiDimensionalObject dimensionalObject) {
		request.setDimensionalObject(dimensionalObject);
		editDimension.execute(request);

	}

}
