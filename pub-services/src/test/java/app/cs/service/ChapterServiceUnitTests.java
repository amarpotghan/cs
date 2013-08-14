package app.cs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.data.business.api.model.IMultiDimensionalObject;
import app.cs.data.business.factory.DomainFactory;
import app.cs.data.business.model.MultiDimensionalObject;
import app.cs.data.business.repository.ChapterRepository;
import app.cs.service.ChapterService;

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
		service = new ChapterService(chapterRepository, factory);

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
		when(factory.getDomainObject("MultiDimensionalObject")).thenReturn(
				object);
		String actualResult = service.create(type, name, path, isFolder);

		// then
		verify(factory).getDomainObject("MultiDimensionalObject");
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
