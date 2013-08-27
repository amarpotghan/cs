package app.cs.actions.publicationplanning.dimension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.actions.publicationplanning.perspective.ITreeBuilder;
import app.cs.interfaces.chapter.IInMemoryViewStructure;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.model.MultiDimensionalObject;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

/**
 * The Class DimensionService. TODO remove out all annotation from class
 */
@Component
public class CreateDimension {

	/** The dimension repository. */
	private IDimensionRepository dimensionRepository;

	/** The contentobject. */
	private final String CONTENTOBJECT = "MultiDimensionalObject";

	/**
	 * Instantiates a new dimension service.
	 * 
	 * @param dimensionRepository
	 *            the dimension repository
	 * @param treeBuilder
	 *            the tree builder
	 */
	@Autowired
	public CreateDimension(IDimensionRepository dimensionRepository,
			ITreeBuilder treeBuilder, IInMemoryViewStructure cache) {

		this.dimensionRepository = dimensionRepository;

	}

	public ResponseModel execute(String type, String name, String path,
			boolean isFolder) {

		MultiDimensionalObject dimension = (MultiDimensionalObject) dimensionRepository
				.getDomain(CONTENTOBJECT);

		setDimensionAttributes(dimension, type, name, path, isFolder);
		return new StringResponse(
				dimensionRepository.createDimension(dimension));
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

}
