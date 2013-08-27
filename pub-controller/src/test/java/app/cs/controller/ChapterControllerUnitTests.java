package app.cs.controller;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.chapter.ChapterInteractions;
import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.model.MultiDimensionalObject;

@RunWith(MockitoJUnitRunner.class)
public class ChapterControllerUnitTests {

	private ChapterController chapterController;

	@Mock
	private ChapterInteractions chapterService;

	@Mock
	private DomainFactory factory;

	private IMultiDimensionalObject dimension;

	@Before
	public void setUp() {
		chapterController = new ChapterController(chapterService);

	}

	@Test
	public void itShouldCallChapterServiceToCreateAChapter() {

		// given
		String name = "test";
		String path = "A,B";
		String type = "spread";
		boolean isFolder = true;

		IMultiDimensionalObject chapter = new MultiDimensionalObject(name, type, path, isFolder);

		// when
		when(chapterService.create(type, name, path, isFolder))
				.thenReturn(name);
		String actualName = chapterController
				.create(type, name, path, isFolder);
		// then
		verify(chapterService).create(type, name, path, isFolder);
		assertThat(actualName).isEqualTo(name);

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
		chapterController.move(type, name, path, isFolder, newPath);

		// then
		verify(chapterService).move(type, name, path, isFolder, newPath);
	}
}
