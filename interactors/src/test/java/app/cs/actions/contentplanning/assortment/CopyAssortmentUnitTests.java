package app.cs.actions.contentplanning.assortment;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.assortment.AssortmentRepository;
import app.cs.model.request.CopyAssortmentRequest;
import app.cs.impl.model.Assortment;

@RunWith(MockitoJUnitRunner.class)
public class CopyAssortmentUnitTests {

	@Mock
	private AssortmentRepository assortmentRepository;

	private CopyAssortment copyAssortment;

	@Mock
	private Assortment assortment;

	@Before
	public void setUp() {
		copyAssortment = new CopyAssortment(assortmentRepository);
	}

	@Test
	public void itShouldCopyAnAssortment() {

		// given
		String type = "assortment";
		String name = "AS01";
		String path = "Mp01,P01";
		String newPath = "Mp02,p02";
		CopyAssortmentRequest request = new CopyAssortmentRequest();
		request.setAssortment(assortment);
		request.setNewPath(newPath);

		// when
		copyAssortment.execute(request);
		
		// then
		verify(assortmentRepository).copy(assortment, newPath);

	}
}