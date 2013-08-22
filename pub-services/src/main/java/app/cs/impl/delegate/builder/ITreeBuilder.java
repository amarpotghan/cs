package app.cs.impl.delegate.builder;

import java.util.List;

import app.cs.interfaces.model.MultiDimensionalObject;

public interface ITreeBuilder {

	/**
	 * Builds the tree.
	 *
	 * @param structure the structure
	 * @return the list
	 */
	public abstract List<MultiDimensionalObject> buildTree(String structure);

}