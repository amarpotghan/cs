package app.cs.controller.contentplanning.assortment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.contentplanning.assortment.CreateAssortment;
import app.cs.controller.contentplanning.assortment.CreateAssortmentController;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.model.request.CreateAssortmentRequest;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;
import app.cs.boundary.delivery.Interactor;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateAssortmentControllerUnitTest {

	private CreateAssortmentController createAssortmentController;

	@Mock
	private Interactor interactor;
	
	@Mock
	private CreateAssortmentRequest createAssortmentRequest;

	@Mock
	private Assortment assortment;

	@Before
	public void setUp() {
		createAssortmentController = new CreateAssortmentController(interactor , createAssortmentRequest);
	}

	@Test
	public void itShouldCreateAssortment() {
		
		Assortment assortment = new Assortment();
		String path = "CP01,MP01,P01";

		createAssortmentRequest.setNewPath(path);
		createAssortmentRequest.setAssortment(assortment);
		// when
		createAssortmentController.create(assortment, path);

		// then
		verify(interactor).execute(createAssortmentRequest);
	}
	
	
}
