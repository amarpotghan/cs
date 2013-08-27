package app.cs.actions.publicationplanning.perspective;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.interfaces.chapter.IInMemoryViewStructure;
import app.cs.model.MultiDimensionalObject;

/**
 * The Class DimensionService.
 */
@Component
public class SwitchPerspective {

	/** The tree builder. */
	private ITreeBuilder treeBuilder;

	/** The ViewStructure cache. */
	private IInMemoryViewStructure cache;

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
	public SwitchPerspective(ITreeBuilder treeBuilder,
			IInMemoryViewStructure cache) {

		this.treeBuilder = treeBuilder;
		this.cache = cache;

	}

	public List<MultiDimensionalObject> execute(String structure) {
		setCurrentViewStructure(structure);
		return treeBuilder.buildTree(structure);

	}

	public void setCurrentViewStructure(String currentViewStructure) {
		cache.setCurrentViewStructure(KEY, currentViewStructure);

	}

}
