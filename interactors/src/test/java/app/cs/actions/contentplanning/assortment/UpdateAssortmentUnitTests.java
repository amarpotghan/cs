package app.cs.actions.contentplanning.assortment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.assortment.AssortmentRepository;
import app.cs.impl.model.Assortment;
import app.cs.model.request.UpdateAssortmentRequest;

@RunWith(MockitoJUnitRunner.class)
public class UpdateAssortmentUnitTests {

	@Mock
	private AssortmentRepository assortmentRepository;

	private UpdateAssortment updateAssortment;

	@Mock
	private Assortment assortment;

	@Before
	public void setUp() {
		updateAssortment = new UpdateAssortment(assortmentRepository);
	}


	@Test
	public void itShouldUpdateAnAssortment() {
		// given
		String type = "assortment";
		String name = "AS01";
		String path = "Mp01,P01";
		UpdateAssortmentRequest request = new UpdateAssortmentRequest();
		request.setAssortment(assortment);
		
		// when
		updateAssortment.execute(request);
				
		// then
		/*verify(assortmentRepository).update(assortment);*/


	}

}