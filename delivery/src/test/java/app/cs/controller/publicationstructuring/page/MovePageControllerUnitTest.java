package app.cs.controller.publicationstructuring.page;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.publicationstructuring.page.MovePage;
import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.model.request.MovePageRequest;

@RunWith(MockitoJUnitRunner.class)
public class MovePageControllerUnitTest {

	private MovePageController movePageController;

	@Mock
	private MovePage movePage;

	@Mock
	private MovePageRequest movePageRequest;

	@Mock
	private DomainFactory factory;

	private IMultiDimensionalObject dimension;

	@Before
	public void setUp() {
		movePageController = new MovePageController(movePage,
				movePageRequest);

	}

	@Test
	public void itShouldPageServiceToMoveAnObjectToGivenLocation() {

		// given
		String currentPath = "A-B-C";
		String newPath = "A-B-D";
		String name = "test";
		String path = "A,B";
		String type = "spread";
		boolean isFolder = true;

		IMultiDimensionalObject page = new MultiDimensionalObject();

		// when
		movePageController.execute(type, name, path, isFolder, newPath);

		// then
		verify(movePage).execute(movePageRequest);
	}
}
