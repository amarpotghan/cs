package app.cs.interfaces.chapter;

public interface IInMemoryViewStructure {

	/**
	 * Gets the current view structure.
	 *
	 * @return the current view structure
	 */
	public abstract String getCurrentViewStructure();

	/**
	 * Sets the current view structure.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public abstract void setCurrentViewStructure(String key, String value);

}