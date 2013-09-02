package app.cs.actions.contentplanning.assortment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.assortment.AssortmentRepository;
import app.cs.impl.model.Assortment;

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
		// when
	/*	updateAssortment.execute(assortment);

		// then
		verify(assortmentRepository).update(assortment);*/

	}

}