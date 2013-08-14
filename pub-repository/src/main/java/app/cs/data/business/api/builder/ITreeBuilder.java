package app.cs.data.business.api.builder;

import java.util.List;

import app.cs.data.business.model.MultiDimensionalObject;

public interface ITreeBuilder {

	/**
	 * Builds the tree.
	 *
	 * @param structure the structure
	 * @return the list
	 */
	public abstract List<MultiDimensionalObject> buildTree(String structure);

}