package app.cs.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.builder.TreeBuilder;
import app.cs.factory.DomainFactory;
import app.cs.inmemory.InMemoryViewStructure;
import app.cs.model.HierarchicalObject;
import app.cs.repository.api.IDimensionRepository;

/**
 * The Class DimensionService.
 * TODO remove out all annotation from class 
 */
@Component
public class DimensionService implements Service {

	/** The tree builder. */
	private TreeBuilder treeBuilder;

	/** The dimension repository. */
	private IDimensionRepository dimensionRepository;

	/** The Domain factory. */
	private DomainFactory factory;

	/** The ViewStructure cache. */
	private InMemoryViewStructure cache;

	/** The contentobject. */
	private final String CONTENTOBJECT = "ContentObject";

	/** The Constant KEY. */
	private static final String KEY = "view";

	/**
	 * Instantiates a new dimension service.
	 * 
	 * @param dimensionRepository
	 *            the dimension repository
	 * @param treeBuilder
	 *            the tree builder
	 */
	@Autowired
	public DimensionService(IDimensionRepository dimensionRepository,
			TreeBuilder treeBuilder, DomainFactory factory,
			InMemoryViewStructure cache) {

		this.dimensionRepository = dimensionRepository;
		this.treeBuilder = treeBuilder;
		this.factory = factory;
		this.cache = cache;

	}

	/*
	 * Get all dimension.
	 * 
	 * @see com.cs.service.IService#getAll()
	 */
	@Override
	public String getAll() throws IOException, URISyntaxException {
		// TODO Auto-generated method stub

		return dimensionRepository.getAllDimensions();
	}

	/*
	 * Creates new dimension.
	 * 
	 * @see com.cs.service.IService#create(com.cs.model.DimensionModel)
	 */

	/*
	 * Get all chapters for given structure.
	 * 
	 * @see com.cs.service.IService#getAllBy(java.lang.String)
	 */
	@Override
	public List<HierarchicalObject> getAllBy(String structure) {
		// TODO Auto-generated method stub
		setCurrentViewStructure(structure);
		return treeBuilder.buildTree(structure);

	}

	/*
	 * Deletes the dimension.
	 * 
	 * @see com.cs.service.IService#delete(com.cs.model.ContentObject,
	 * java.lang.String)
	 */
	@Override
	public void delete(HierarchicalObject chapter, String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public String create(String type, String name, String path, String isFolder) {
		HierarchicalObject dimension = (HierarchicalObject) factory
				.getDomainObject(CONTENTOBJECT);

		setDimensionAttributes(dimension, type, name, path, isFolder);
		return dimensionRepository.createDimension(dimension);
	}

	/**
	 * Sets the dimension attributes.
	 * 
	 * @param dimension
	 *            the dimension
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 */

	protected void setDimensionAttributes(HierarchicalObject dimension, String type,
			String name, String path, String isFolder) {
		dimension.setId(name);
		dimension.setTitle(name);
		dimension.setIsFolder(isFolder);
		dimension.setPath(path);
		dimension.setName(name);
		dimension.setType(type);

	}

	/**
	 * Sets the current view structure.
	 * 
	 * @param currentViewStructure
	 *            the new current view structure
	 */
	protected void setCurrentViewStructure(String currentViewStructure) {
		cache.setCurrentViewStructure(KEY, currentViewStructure);

	}

	@Override
	public void move(String type, String name, String path, String isFolder,
			String newpath) {
		// TODO Auto-generated method stub

	}

}
