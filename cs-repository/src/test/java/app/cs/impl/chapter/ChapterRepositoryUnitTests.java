package app.cs.impl.chapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.chapter.ChapterRepository;
import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.helper.Finder;
import app.cs.impl.model.MultiDimensionalObject;

import com.cs.data.core.nosql.mongodb.MongoRepository;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChapterRepositoryUnitTests {

	private ChapterRepository repository;

	@Mock
	private MongoRepository noSqlTemplateForMongo;

	@Mock
	private Finder finder;

	@Mock
	private DomainFactory factory;

	MultiDimensionalObject publication;

	MultiDimensionalObject test;

	@Before
	public void setUp() {
		repository = new ChapterRepository(noSqlTemplateForMongo, factory,
				finder);
		publication = new MultiDimensionalObject("Test", "publication",
				"A,B,C,D,E", true);
		test = new MultiDimensionalObject("test01", "chapter",
				"A,B,C,D,E,publication", false);
		test.addchild(new MultiDimensionalObject("test03", "test", "test", true));
		publication.addchild(test);
		publication.addchild(new MultiDimensionalObject("test02", "test", "A",
				true));

	}

	@Test
	public void itShouldCreateAChapterInTheParentPublication() {
		// given
		MultiDimensionalObject chapter = new MultiDimensionalObject("test",
				"test", "A,B,C,D,E,test03", true);
		String result = "success";
		// when
		when(noSqlTemplateForMongo.save(chapter)).thenReturn(result);
		when(finder.getPublicationId(chapter.getPath())).thenReturn("D");
		when(
				noSqlTemplateForMongo.getObjectByKey("D",
						MultiDimensionalObject.class)).thenReturn(publication);

		when(finder.getParentId(chapter.getPath())).thenReturn("test");
		when(finder.find(publication, "test")).thenReturn(test);
		String actualResult = repository.save(chapter);

		// then
		verify(noSqlTemplateForMongo).getObjectByKey("D",
				MultiDimensionalObject.class);
		verify(noSqlTemplateForMongo).save(publication);

	}

	@Test
	public void itShouldDeleteGivenNode() {
		// given

		String result = "result";
		MultiDimensionalObject chapter = new MultiDimensionalObject("test",
				"test", "A,B,C,D,E,test01", true);
		when(noSqlTemplateForMongo.save(chapter)).thenReturn(result);
		when(finder.getPublicationId(chapter.getPath())).thenReturn("D");
		when(
				noSqlTemplateForMongo.getObjectByKey("D",
						MultiDimensionalObject.class)).thenReturn(publication);

		when(finder.getParentId(chapter.getPath())).thenReturn("test");
		when(finder.find(publication, "test")).thenReturn(test);
		// when
		repository.delete(chapter);

		// then
		verify(noSqlTemplateForMongo).getObjectByKey("D",
				MultiDimensionalObject.class);
		verify(noSqlTemplateForMongo).save(publication);

	}

	@Test
	public void itShouldMoveChapterFromOneLocationToOther() {
		String result = "result";
		String newPath = "A,B,C,D,E,test02";
		MultiDimensionalObject chapter = new MultiDimensionalObject("test01",
				"test", "A,B,C,D,E,test01", true);
		// when
		when(finder.getPublicationId(chapter.getPath())).thenReturn("D");
		when(finder.getPublicationId(newPath)).thenReturn("D");
		when(
				noSqlTemplateForMongo.getObjectByKey("D",
						MultiDimensionalObject.class)).thenReturn(publication);

		when(finder.getParentId(newPath)).thenReturn("test");
		when(finder.getParentId(chapter.getPath())).thenReturn("test");
		when(finder.find(publication, "test")).thenReturn(test);
		when(finder.find(publication, chapter.getId())).thenReturn(test);
		when(finder.find(publication, "test")).thenReturn(test);
		repository.move(chapter, newPath);

		// then
		verify(noSqlTemplateForMongo, times(2)).save(publication);

	}
}
