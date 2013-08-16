package app.cs.impl.chapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.chapter.ChapterRepository;
import app.cs.impl.chapter.InMemoryViewStructure;
import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.dimension.InMemoryDimensionGroup;
import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.interfaces.model.MultiDimensionalObject;
import app.cs.utils.ArrayUtils;

import com.cs.data.core.nosql.mongodb.MongoRepository;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChapterRepositoryUnitTests {

	private ChapterRepository repository;

	@Mock
	private MongoRepository noSqlTemplateForMongo;

	@Mock
	private InMemoryViewStructure cache;

	@Mock
	private DomainFactory factory;

	MultiDimensionalObject publication;

	@Before
	public void setUp() {
		repository = new ChapterRepository(noSqlTemplateForMongo, cache,
				factory);
		publication = new MultiDimensionalObject("Test", "publication",
				"A,B,C,D,E", true);
		MultiDimensionalObject test = new MultiDimensionalObject("test01",
				"chapter", "A,B,C,D,E,publication", false);
		test.addchild(new MultiDimensionalObject("test03", "test", "test",
				true));
		publication.addchild(test);
		publication.addchild(new MultiDimensionalObject("test02", "test", "A",
				true));

	}

	@Test
	public void itShouldCreateAChapterInTheParentPublication() {
		// given
		MultiDimensionalObject chapter = new MultiDimensionalObject("test",
				"test", "A,B,C,D,E,test03",true);
		String result = "success";
		// when
		when(cache.getCurrentViewStructure()).thenReturn("C-M-P-D");
		when(noSqlTemplateForMongo.save(chapter)).thenReturn(result);
		when(
				noSqlTemplateForMongo.getObjectByKey("D",
						MultiDimensionalObject.class)).thenReturn(publication);
		String actualResult = repository.save(chapter);

		// then
		verify(noSqlTemplateForMongo).getObjectByKey("D",
				MultiDimensionalObject.class);
		verify(noSqlTemplateForMongo).save(chapter);
		System.out.println(publication);

	}

	@Test
	public void itShouldGetpublicationId() {
		//
		String path = "A,B,C,D,E,F";
		//
		String actualId = repository.getParentId(path);

		assertThat(actualId).isEqualTo("F");

	}

	@Test
	public void itShouldGetPublicationIdForGivenPath() {
		// given
		String path = "A,B,C,D,E";
		// when
		when(cache.getCurrentViewStructure()).thenReturn("C-M-P-D");
		String actualPublicationId = repository.getPublicationId(path);
		// then
		verify(cache).getCurrentViewStructure();
		assertThat(actualPublicationId).isEqualTo("D");

	}

	@Test
	public void itShouldGetLastIndexOfCurrentViewStructure() {
		String viewStructure = "C-M-D-P";
		int index = repository.getLastIndexOf(viewStructure);
		assertThat(index).isEqualTo(3);
	}

	@Test
	public void itShouldFindContentObjectWithGivenId() {
		// given
		String id = "test01";

		// when
		IMultiDimensionalObject object = repository.find(publication, id);

		// then
		assertThat(object.getId()).isEqualTo(id);

	}

	@Test
	public void itShouldDeleteGivenNode() {
		// given

		String result = "result";
		String oldPath = "A,B,C,D,E,test03";
		MultiDimensionalObject chapter = new MultiDimensionalObject("test",
				"test", "A,B,C,D,E", true);
		when(cache.getCurrentViewStructure()).thenReturn("C-M-P-D");
		when(noSqlTemplateForMongo.save(chapter)).thenReturn(result);
		when(
				noSqlTemplateForMongo.getObjectByKey("D",
						MultiDimensionalObject.class)).thenReturn(publication);

		// when
		repository.delete(chapter, oldPath);

		// then
		verify(noSqlTemplateForMongo).getObjectByKey("D",
				MultiDimensionalObject.class);
		verify(noSqlTemplateForMongo).save(publication);

	}

}
