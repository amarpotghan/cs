package app.cs.actions.contentplanning.assortment;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.assortment.AssortmentRepository;
import app.cs.impl.model.Assortment;

@RunWith(MockitoJUnitRunner.class)
public class AssortmentInteractionsUnitTests {

	@Mock
	private AssortmentRepository assortmentRepository;

	private AssortmentInteractions assortmentUseCases;

	@Mock
	private Assortment assortment;

	@Before
	public void setUp() {
		assortmentUseCases = new AssortmentInteractions(assortmentRepository);
	}

	@Test
	public void itShouldCreateAnAssortment() {
		// given
		String path = "Cp01,Mp01,P01";

		// when
		assortmentUseCases.create(assortment, path);

		// then
		verify(assortmentRepository).save(assortment, path);

	}

	@Test
	public void itShouldCopyAnAssortment() {

		// given
		String type = "assortment";
		String name = "AS01";
		String path = "Mp01,P01";
		String newPath = "Mp02,p02";

		// when
		assortmentUseCases.move(assortment, newPath);
		System.out.println(assortment);

		// then
		verify(assortmentRepository).copy(assortment, newPath);

	}
}
