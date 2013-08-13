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


import com.cs.data.api.core.nosql.InMemoryNoSqlRepository;
import com.cs.data.core.nosql.mongodb.MongoRepository;

import app.cs.inmemory.InMemoryViewStructure;
import app.cs.model.HierarchicalObject;
import app.cs.repository.ChapterRepository;
import app.cs.repository.api.IChapterRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class ChapterIntegrationTests {

	@Autowired
	private MongoRepository noSqlRepository;

	List<HierarchicalObject> models = new ArrayList<HierarchicalObject>();

	private InMemoryViewStructure cache;
	private IChapterRepository chapterRepository;

	@Autowired
	private InMemoryNoSqlRepository inMemoryNosqlRepository;

	HierarchicalObject page01;

	@Before
	public void setUp() {

		page01 = new HierarchicalObject("page01", "page", "mp02,pg02,c02,p02",
				"false");
		HierarchicalObject chapter01 = new HierarchicalObject("chapter01", "page",
				"mp02,pg02,c02,p02", "false");
		HierarchicalObject chapter02 = new HierarchicalObject("chapter02", "page",
				"mp02,pg02,c02,p02,chapter01", "false");
		HierarchicalObject page02 = new HierarchicalObject("page02", "page",
				"mp02,pg02,c02,p02,chapter01,chapter02", "false");
		cache = new InMemoryViewStructure(inMemoryNosqlRepository);

		models.add(page01);
		models.add(chapter01);
		models.add(chapter02);
		models.add(page02);

	}

	@Test
	public void itShouldCreateMultipleDimensionGroupsForGivenModels() {

		cache = new InMemoryViewStructure(inMemoryNosqlRepository);
		for (HierarchicalObject dimension : models) {
			chapterRepository = new ChapterRepository(noSqlRepository, cache);
			String result = chapterRepository.save(dimension);

			assertThat(result).isNotNull();
			assertThat(result).isEqualTo("inserted");

		}

	}

	@Test
	public void itShouldRemoveAnObjectFromPublication() {
		chapterRepository = new ChapterRepository(noSqlRepository, cache);

		String result = chapterRepository.delete(page01, "mp02,pg02,c02,p02");

		assertThat(result).isEqualTo(page01.getId());

	}

}
