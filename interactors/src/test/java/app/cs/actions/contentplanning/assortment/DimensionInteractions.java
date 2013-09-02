package app.cs.actions.contentplanning.assortment;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.actions.publicationplanning.perspective.ITreeBuilder;
import app.cs.boundary.delivery.Service;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.interfaces.inmemory.IInMemoryViewStructure;

/**
 * The Class DimensionService.
 */
@Component
public class DimensionInteractions implements Service {

	/** The tree builder. */
	private ITreeBuilder treeBuilder;

	/** The dimension repository. */
	private IDimensionRepository dimensionRepository;

	/** The ViewStructure cache. */
	private IInMemoryViewStructure cache;

	/** The contentobject. */
	private final String CONTENTOBJECT = "MultiDimensionalObject";

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
	public DimensionInteractions(IDimensionRepository dimensionRepository,
			ITreeBuilder treeBuilder, IInMemoryViewStructure cache) {

		this.dimensionRepository = dimensionRepository;
		this.treeBuilder = treeBuilder;
		this.cache = cache;

	}

	@Override
	public String getAll() throws IOException, URISyntaxException {

		return dimensionRepository.getAllDimensions();
	}

	@Override
	public List<app.cs.impl.model.MultiDimensionalObject> getAllBy(String structure) {
		setCurrentViewStructure(structure);
		return treeBuilder.buildTree(structure);

	}

	@Override
	public void delete(IMultiDimensionalObject chapter, String path) {

	}

	@Override
	public String create(String type, String name, String path, boolean isFolder) {

		MultiDimensionalObject dimension = (MultiDimensionalObject) dimensionRepository
				.getDomain(CONTENTOBJECT);

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

	protected void setDimensionAttributes(MultiDimensionalObject dimension,
			String type, String name, String path, boolean isFolder) {
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
	public void setCurrentViewStructure(String currentViewStructure) {
		cache.setCurrentViewStructure(KEY, currentViewStructure);

	}

	@Override
	public void move(String type, String name, String path, boolean isFolder,
			String newpath) {

	}

}
