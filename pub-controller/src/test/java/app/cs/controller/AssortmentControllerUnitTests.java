package app.cs.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.assortment.AssortmentInteractions;
import app.cs.interfaces.model.Assortment;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssortmentControllerUnitTests {

	private AssortmentController assormentController;

	@Mock
	private AssortmentInteractions assortmentInteractions;

	@Mock
	private Assortment assortment;

	@Before
	public void setUp() {
		assormentController = new AssortmentController(assortmentInteractions);
	}

	@Test
	public void itShouldAcceptAssortment() {
		String path = "CP01,MP01,P01";

		String name = "testAssortment";
		// when
		assormentController.create(assortment, name, path);

		// then

		verify(assortmentInteractions).create(assortment, path);
	}

}
