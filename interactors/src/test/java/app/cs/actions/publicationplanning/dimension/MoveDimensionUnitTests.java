package app.cs.actions.publicationplanning.dimension;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.model.request.MoveDimensionRequest;
import app.cs.model.request.RequestModel;

@RunWith(MockitoJUnitRunner.class)
public class MoveDimensionUnitTests {

	private MoveDimension dimension;

	@Mock
	private IDimensionRepository repository;

	@Test
	public void itShouldMoveTheGivenDimensionToNewPath() {

		dimension = new MoveDimension(repository);
		MoveDimensionRequest request = new MoveDimensionRequest();
		// when
		dimension.execute(request);
	}

}
