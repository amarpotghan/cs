package app.cs.controller.publicationplanning.dimension;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.cs.actions.publicationplanning.dimension.MoveDimension;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.model.request.MoveDimensionRequest;

@RunWith(MockitoJUnitRunner.class)
public class MoveDimensionControllerSpecs {

	private MoveDimensionController moveDimensionController;

	private MoveDimensionRequest request;

	@Mock
	private MoveDimension moveDimension;

	@Test
	public void itShouldCallMoveInteractor() {

		// given

		String newPath = "Marketing Initiative01,Campaign,Sub-Campaign1";
		String oldPath = "Marketing Initiative01,Campaign,Sub-Campaign0";
		MultiDimensionalObject objectInMove = new MultiDimensionalObject(
				"CP01", "CP01",
				"Marketing Initiative01,Campaign,Sub-Campaign1", true);

		request = new MoveDimensionRequest();
		moveDimensionController = new MoveDimensionController(moveDimension,
				request);

		// when

		moveDimensionController.move(oldPath, newPath, objectInMove);
		// then

		verify(moveDimension).execute(request);

	}
}
