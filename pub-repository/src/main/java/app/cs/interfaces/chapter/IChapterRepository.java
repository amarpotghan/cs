package app.cs.interfaces.chapter;

import com.cs.data.api.core.GenericDomain;

import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.interfaces.model.MultiDimensionalObject;


public interface IChapterRepository {

	/**
	 * Save given chapter.
	 * 
	 * @param chapter
	 *            the chapter
	 * @return the string
	 */
	public abstract String save(MultiDimensionalObject chapter);

	/**
	 * Deletes given chapter for given old path.
	 * 
	 * @param chapter
	 *            the chapter
	 * @param oldPath
	 *            the old path
	 * @return the string
	 */
	public abstract String delete(IMultiDimensionalObject chapter,
			String oldPath);

	GenericDomain getDomain(String type);

}