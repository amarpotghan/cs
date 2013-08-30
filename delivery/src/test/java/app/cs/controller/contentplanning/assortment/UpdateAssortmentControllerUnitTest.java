package app.cs.controller.contentplanning.assortment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.contentplanning.assortment.UpdateAssortment;
import app.cs.controller.contentplanning.assortment.UpdateAssortmentController;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.model.request.CopyAssortmentRequest;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;
import app.cs.model.request.UpdateAssortmentRequest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateAssortmentControllerUnitTest {

	private UpdateAssortmentController updateAssortmentController;

	@Mock
	private UpdateAssortment updateAssortment;
	
	@Mock
	private Assortment assortment;
	
	@Mock
	private UpdateAssortmentRequest updateAssortmentRequest;

	@Before
	public void setUp() {
		updateAssortmentController = new UpdateAssortmentController(updateAssortment, updateAssortmentRequest);
	}

	@Test
	public void itShouldCopyAnAssortment() {
		
		Assortment assortment = new Assortment();
		updateAssortmentRequest.setAssortment(assortment);
				
		// when
		updateAssortmentController.execute(assortment);

		// then
		verify(updateAssortment).execute(updateAssortmentRequest);
	}
	
	
}
