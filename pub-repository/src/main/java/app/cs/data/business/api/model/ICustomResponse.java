package app.cs.data.business.api.model;

public interface ICustomResponse {

	/**
	 * Gets the html.
	 *
	 * @return the html
	 */
	public abstract String getHtml();

	/**
	 * Sets the html.
	 *
	 * @param html the new html
	 */
	public abstract void setHtml(String html);

	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	public abstract String getEvents();

	/**
	 * Sets the events.
	 *
	 * @param events the new events
	 */
	public abstract void setEvents(String events);

	/**
	 * Gets the elements.
	 *
	 * @return the elements
	 */
	public abstract String getElements();

	/**
	 * Sets the elements.
	 *
	 * @param elemnts the new elements
	 */
	public abstract void setElements(String elemnts);

}