package app.cs.actions.publicationstructuring.chapter;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.chapter.ChapterRepository;
import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.model.MultiDimensionalObject;

@RunWith(MockitoJUnitRunner.class)
public class ChapterServiceUnitTests {

	private ChapterInteractions service;

	@Mock
	private ChapterRepository chapterRepository;

	@Mock
	private DomainFactory factory;

	@Before
	public void setUp() {
		service = new ChapterInteractions(chapterRepository);

	}

	@Test
	public void itShouldCreateAChapter() {

		// given

		String result = "success";
		String name = "test";
		String path = "A,B";
		String type = "spread";
		boolean isFolder = true;
		// when
		MultiDimensionalObject object = new MultiDimensionalObject();
		when(chapterRepository.save(object)).thenReturn(result);
		when(chapterRepository.getDomain("MultiDimensionalObject")).thenReturn(
				object);
		String actualResult = service.create(type, name, path, isFolder);

		// then
		verify(chapterRepository).getDomain("MultiDimensionalObject");
		verify(chapterRepository).save(object);
		assertThat(actualResult).isEqualTo(actualResult);

	}

	@Test
	public void itShouldMoveAChapter() {
		// given
		String result = "success";
		String oldPath = "testpath";

		String type = "Campaign";
		String name = "CP01";
		String path = "Mp01,P01";
		boolean isFolder = true;
		String newPath = "Mp02,p02";
		MultiDimensionalObject object = new MultiDimensionalObject(name, type,
				path, isFolder);
		// when
		when(chapterRepository.getDomain("MultiDimensionalObject")).thenReturn(
				object);
		service.move(type, name, path, isFolder, newPath);

		// then
		verify(chapterRepository).move(object, newPath);

	}

}
