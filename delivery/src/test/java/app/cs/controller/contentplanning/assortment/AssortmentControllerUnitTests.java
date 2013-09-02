/*package app.cs.controller.contentplanning.assortment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.contentplanning.assortment.AssortmentInteractions;
import app.cs.controller.contentplanning.assortment.AssortmentController;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IMultiDimensionalObject;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssortmentControllerUnitTests {

	private AssortmentController assortmentController;

	@Mock
	private AssortmentInteractions assortmentInteractions;

	@Mock
	private Assortment assortment;

	@Before
	public void setUp() {
		assortmentController = new AssortmentController(assortmentInteractions);
	}

	@Test
	public void itShouldAcceptAssortment() {
		String path = "CP01,MP01,P01";

		String name = "testAssortment";
		// when
		assortmentController.create(assortment, path);

		// then

		verify(assortmentInteractions).create(assortment, path);
	}
	
	@Test
	public void itShouldCopyAssortmentToGivenLocation() {

		// given
		String newPath = "A,B,C,D,E,test01";
		String name = "test";
		

		Assortment assortment =new Assortment();
		
		// when
		assortmentController.copy(assortment, newPath);

		// then
		verify(assortmentInteractions).copy(assortment, newPath);
	}
	
	@Test
	public void itShouldUpdateAnAssortment() {

		// given
		Assortment assortment = new Assortment();
		
		// when
		assortmentController.update(assortment);

		// then
		verify(assortmentInteractions).update(assortment);
	}

}
*/