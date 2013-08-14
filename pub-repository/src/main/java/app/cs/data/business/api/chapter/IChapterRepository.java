package app.cs.data.business.api.chapter;

import app.cs.data.business.api.dimension.IMultiDimensionalObject;
import app.cs.data.business.dimension.MultiDimensionalObject;


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

}