package app.cs.data.business.model;

import app.cs.data.business.api.model.ICustomResponse;


/**
 * The Class CustomResponse.
 */
public class CustomResponse implements ICustomResponse {

	/** The html. */
	private String html;
	
	/** The events. */
	private String events;
	
	/** The elements. */
	private String elements;

	/**
	 * Instantiates a new custom response.
	 */
	public CustomResponse() {

	}

	/**
	 * Instantiates a new custom response.
	 *
	 * @param html the html
	 * @param events the events
	 * @param elements the elements
	 */
	public CustomResponse(String html, String events, String elements) {
		super();
		this.html = html;
		this.events = events;
		this.elements = elements;
	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.model.ICustomResponse#getHtml()
	 */
	@Override
	public String getHtml() {
		return html;
	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.model.ICustomResponse#setHtml(java.lang.String)
	 */
	@Override
	public void setHtml(String html) {
		this.html = html;
	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.model.ICustomResponse#getEvents()
	 */
	@Override
	public String getEvents() {
		return events;
	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.model.ICustomResponse#setEvents(java.lang.String)
	 */
	@Override
	public void setEvents(String events) {
		this.events = events;
	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.model.ICustomResponse#getElements()
	 */
	@Override
	public String getElements() {
		return elements;
	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.model.ICustomResponse#setElements(java.lang.String)
	 */
	@Override
	public void setElements(String elemnts) {
		this.elements = elemnts;
	}

}
