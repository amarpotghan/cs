package app.cs.integration;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.cs.boundary.chapter.IChapterRepository;
import app.cs.boundary.chapter.IInMemoryViewStructure;
import app.cs.boundary.model.MultiDimensionalObject;
import app.cs.chapter.ChapterRepository;
import app.cs.chapter.InMemoryViewStructure;
import app.cs.impl.delegate.factory.DomainFactory;

import com.cs.data.api.core.nosql.redis.InMemoryNoSqlRepository;
import com.cs.data.core.nosql.mongodb.MongoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class ChapterIntegrationTests {

	@Autowired
	private MongoRepository noSqlRepository;

	List<MultiDimensionalObject> models = new ArrayList<MultiDimensionalObject>();

	private IInMemoryViewStructure cache;
	private IChapterRepository chapterRepository;

	@Autowired
	private InMemoryNoSqlRepository inMemoryNosqlRepository;

	MultiDimensionalObject page01;

	private DomainFactory factory = new DomainFactory();

	@Before
	public void setUp() {

		page01 = new MultiDimensionalObject("page01", "page",
				"mp02,pg02,c02,p02", true);
		MultiDimensionalObject chapter01 = new MultiDimensionalObject(
				"chapter01", "page", "mp02,pg02,c02,p02", false);
		MultiDimensionalObject chapter02 = new MultiDimensionalObject(
				"chapter02", "page", "mp02,pg02,c02,p02,chapter01", false);
		MultiDimensionalObject page02 = new MultiDimensionalObject("page02",
				"page", "mp02,pg02,c02,p02,chapter01,chapter02", false);
		cache = new InMemoryViewStructure(inMemoryNosqlRepository);

		models.add(page01);
		models.add(chapter01);
		models.add(chapter02);
		models.add(page02);

	}

	/*
	 * @Test public void itShouldCreateMultipleDimensionGroupsForGivenModels() {
	 * 
	 * cache = new InMemoryViewStructure(inMemoryNosqlRepository); for
	 * (MultiDimensionalObject dimension : models) { chapterRepository = new
	 * ChapterRepository(noSqlRepository, cache); String result =
	 * chapterRepository.save(dimension);
	 * 
	 * assertThat(result).isNotNull(); assertThat(result).isEqualTo("inserted");
	 * 
	 * }
	 * 
	 * }
	 */

	@Test
	public void itShouldRemoveAnObjectFromPublication() {
		chapterRepository = new ChapterRepository(noSqlRepository, cache,
				factory);

		// /String result = chapterRepository.delete(page01,
		// "mp02,pg02,c02,p02");

		// /assertThat(result).isEqualTo(page01.getId());

	}

}
