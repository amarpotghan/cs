package app.cs.integration;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.cs.builder.TreeBuilder;

import com.cs.data.api.core.nosql.InMemoryNoSqlRepository;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.data.core.nosql.redis.RedisRepository;

import app.cs.inmemory.InMemoryDimensionGroup;
import app.cs.model.HierarchicalObject;
import app.cs.repository.DimensionRepository;
import app.cs.repository.api.IDimensionRepository;
import app.cs.utils.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class TreeBuilderIntegrationTests {

	private TreeBuilder builder;

	private InMemoryNoSqlRepository inMemoryNoSqlRepository;

	private MongoRepository noSqlRepository;

	@Autowired
	private MongoOperations mongoTemplate;

	@Autowired
	private RedisOperations redisTemplate;
	private FileUtils fileUtils;
	private InMemoryDimensionGroup cache;
	private IDimensionRepository repository;

	@Test
	public void itShouldReturnTheWholeTree() {
		noSqlRepository = new MongoRepository(mongoTemplate);
		inMemoryNoSqlRepository = new RedisRepository(redisTemplate);
		fileUtils = new FileUtils();
		cache = new InMemoryDimensionGroup(inMemoryNoSqlRepository);
		repository = new DimensionRepository(fileUtils, cache, noSqlRepository);

		builder = new TreeBuilder(cache, repository);

		List<HierarchicalObject> models = builder
				.buildTree("Campaign-MasterPublication-PublicationGroup-Publication");
		System.out.println(models);

	}

}
