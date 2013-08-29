package app.cs.actions.publicationplanning.perspective;

import java.util.List;

import app.cs.impl.model.MultiDimensionalObject;

public interface ITreeBuilder {

	/**
	 * Builds the tree.
	 *
	 * @param structure the structure
	 * @return the list
	 */
	public abstract List<MultiDimensionalObject> buildTree(String structure);

}