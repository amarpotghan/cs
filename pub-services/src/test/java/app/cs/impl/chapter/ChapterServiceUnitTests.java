package app.cs.impl.chapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.chapter.ChapterRepository;
import app.cs.impl.chapter.ChapterService;
import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.interfaces.model.MultiDimensionalObject;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChapterServiceUnitTests {

	private ChapterService service;

	@Mock
	private ChapterRepository chapterRepository;

	@Mock
	private DomainFactory factory;

	@Before
	public void setUp() {
		service = new ChapterService(chapterRepository);

	}

	@Test
	public void itShouldCreateAChapter() {

		// given

		String result = "success";
		String name = "test";
		String path = "A,B";
		String type = "spread";
		String isFolder = "true";
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
	public void itShouldDeleteAChapter() {
		// given
		String result = "success";
		String oldPath = "testpath";
		IMultiDimensionalObject object = new MultiDimensionalObject();
		when(chapterRepository.delete(object, oldPath)).thenReturn(result);
		// when
		service.delete(object, oldPath);

		// then
		verify(chapterRepository).delete(object, oldPath);

	}

}
