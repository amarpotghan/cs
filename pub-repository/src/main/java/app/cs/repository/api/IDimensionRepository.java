package app.cs.repository.api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import app.cs.model.HierarchicalObject;

public interface IDimensionRepository {

	/**
	 * Creates the dimension.
	 * 
	 * @param dimension
	 *            the dimension
	 * @return the string
	 */
	public abstract String createDimension(HierarchicalObject dimension);

	/**
	 * Gets the all dimensions.
	 * 
	 * @return the all dimensions
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             the uRI syntax exception
	 */
	public abstract String getAllDimensions() throws IOException,
			URISyntaxException;

	/**
	 * Gets the dimensions.
	 * 
	 * @return the dimensions
	 */
	public abstract List<HierarchicalObject> getDimensions();

	/**
	 * Gets the dimensions of type.
	 * 
	 * @param type
	 *            the type
	 * @return the dimensions of type
	 */
	public abstract List<HierarchicalObject> getDimensionsOfType(String type);

	/**
	 * Gets the dimensions by type.
	 * 
	 * @param type2
	 *            the type2
	 * @param groupIds
	 *            the group ids
	 * @return the dimensions by
	 */
	public abstract List<HierarchicalObject> getDimensionsBy(String type2,
			List<String> groupIds);

}