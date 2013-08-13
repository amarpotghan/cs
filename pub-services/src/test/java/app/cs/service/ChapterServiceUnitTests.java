package app.cs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.model.HierarchicalObject;
import app.cs.repository.ChapterRepository;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChapterServiceUnitTests {

	private ChapterService service;

	@Mock
	private ChapterRepository chapterRepository;

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
		HierarchicalObject object = new HierarchicalObject();
		when(chapterRepository.save(object)).thenReturn(result);
		String actualResult = service.create(type, name, path, isFolder);

		// then
		verify(chapterRepository).save(object);
		assertThat(actualResult).isEqualTo(actualResult);

	}

	@Test
	public void itShouldDeleteAChapter() {
		// given
		String result = "success";
		String oldPath = "testpath";
		HierarchicalObject object = new HierarchicalObject();
		when(chapterRepository.delete(object, oldPath)).thenReturn(result);
		// when
		service.delete(object, oldPath);

		// then
		verify(chapterRepository).delete(object, oldPath);

	}

}
