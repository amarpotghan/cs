package app.cs.actions.publicationplanning.dimension;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.interfaces.dimension.IDimensionRepository;

/**
 * The Class DimensionService. 
 */
@Component
public class GetDimensions {

	/** The dimension repository. */
	private IDimensionRepository dimensionRepository;

	/**
	 * Instantiates a new dimension service.
	 * 
	 * @param dimensionRepository
	 *            the dimension repository
	 * @param treeBuilder
	 *            the tree builder
	 */
	@Autowired
	public GetDimensions(IDimensionRepository dimensionRepository) {

		this.dimensionRepository = dimensionRepository;

	}

	public String execute() throws IOException, URISyntaxException {

		return dimensionRepository.getAllDimensions();
	}

}
