package app.cs.controller.chapter;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.publicationstructuring.chapter.MoveChapter;
import app.cs.controller.publicationstructuring.chapter.MoveChapterController;
import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.model.request.MoveChapterRequest;

@RunWith(MockitoJUnitRunner.class)
public class MoveChapterControllerUnitTest {

	private MoveChapterController moveChapterController;

	@Mock
	private MoveChapter moveChapter;

	@Mock
	private MoveChapterRequest moveChapterRequest;

	@Mock
	private DomainFactory factory;

	private IMultiDimensionalObject dimension;

	@Before
	public void setUp() {
		moveChapterController = new MoveChapterController(moveChapter,
				moveChapterRequest);

	}

	@Test
	public void itShouldChapterServiceToMoveAnObjectToGivenLocation() {

		// given
		String currentPath = "A-B-C";
		String newPath = "A-B-D";
		String name = "test";
		String path = "A,B";
		String type = "spread";
		boolean isFolder = true;

		IMultiDimensionalObject chapter = new MultiDimensionalObject();

		// when
		moveChapterController.execute(type, name, path, isFolder, newPath);

		// then
		verify(moveChapter).execute(moveChapterRequest);
	}
}
