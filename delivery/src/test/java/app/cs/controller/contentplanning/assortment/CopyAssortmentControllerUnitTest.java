package app.cs.controller.contentplanning.assortment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.contentplanning.assortment.CopyAssortment;
import app.cs.controller.contentplanning.assortment.CopyAssortmentController;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.model.request.CopyAssortmentRequest;
import app.cs.model.response.EmptyResponse;
import app.cs.model.response.ResponseModel;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CopyAssortmentControllerUnitTest {

	private CopyAssortmentController copyAssortmentController;

	@Mock
	private CopyAssortment copyAssortment;

	@Mock
	private CopyAssortmentRequest copyAssortmentRequest;

	@Mock
	private Assortment assortment;

	@Before
	public void setUp() {
		copyAssortmentController = new CopyAssortmentController(copyAssortment,
				copyAssortmentRequest);
	}

	@Test
	public void itShouldCopyAnAssortment() {

		Assortment assortment = new Assortment();
		String newPath = "CP01,MP01,P01";

		copyAssortmentRequest.setNewPath(newPath);
		copyAssortmentRequest.setAssortment(assortment);
		// when
		copyAssortmentController.copy(assortment, newPath);

		// then
		verify(copyAssortment).execute(copyAssortmentRequest);
	}

}
