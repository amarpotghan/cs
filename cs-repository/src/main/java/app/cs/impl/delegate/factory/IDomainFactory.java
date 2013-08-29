package app.cs.impl.delegate.factory;

import com.cs.data.api.core.GenericDomain;

public interface IDomainFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.factory.IDomainFactory#getDomainObject(java.lang.String)
	 */
	public abstract GenericDomain getDomainObject(String type);

}