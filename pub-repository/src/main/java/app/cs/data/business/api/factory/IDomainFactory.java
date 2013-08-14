package app.cs.data.business.api.factory;

import com.cs.data.api.core.GenericDomain;

public interface IDomainFactory {

	/**
	 * Gets the domain object.
	 * 
	 * @param type
	 *            the type
	 * @return the domain object
	 */
	public abstract GenericDomain getDomainObject(String type);

}