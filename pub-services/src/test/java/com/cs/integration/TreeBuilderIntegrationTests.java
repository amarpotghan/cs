package com.cs.integration;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.cs.data.business.api.builder.ITreeBuilder;
import app.cs.data.business.api.dimension.IDimensionRepository;
import app.cs.data.business.api.dimension.IInMemoryDimensionGroup;
import app.cs.data.business.builder.TreeBuilder;
import app.cs.data.business.dimension.DimensionRepository;
import app.cs.data.business.dimension.InMemoryDimensionGroup;
import app.cs.data.business.dimension.MultiDimensionalObject;
import app.cs.utils.FileUtils;

import com.cs.data.api.core.nosql.redis.InMemoryNoSqlRepository;
import com.cs.data.core.nosql.mongodb.MongoRepository;
import com.cs.data.core.nosql.redis.RedisRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class TreeBuilderIntegrationTests {

	private ITreeBuilder builder;

	private InMemoryNoSqlRepository inMemoryNoSqlRepository;

	private MongoRepository noSqlRepository;

	@Autowired
	private MongoOperations mongoTemplate;

	@Autowired
	private RedisOperations redisTemplate;
	private FileUtils fileUtils;
	private IInMemoryDimensionGroup cache;
	private IDimensionRepository repository;

	@Test
	public void itShouldReturnTheWholeTree() {
		noSqlRepository = new MongoRepository(mongoTemplate);
		inMemoryNoSqlRepository = new RedisRepository(redisTemplate);
		fileUtils = new FileUtils();
		cache = new InMemoryDimensionGroup(inMemoryNoSqlRepository);
		repository = new DimensionRepository(fileUtils, cache, noSqlRepository);

		builder = new TreeBuilder(cache, repository);

		List<MultiDimensionalObject> models = builder
				.buildTree("Campaign-MasterPublication-PublicationGroup-Publication");
		System.out.println(models);

	}

}
