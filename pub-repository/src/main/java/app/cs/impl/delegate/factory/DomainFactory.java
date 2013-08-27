package app.cs.impl.delegate.factory;

import org.springframework.stereotype.Component;

import app.cs.model.MultiDimensionalObject;

import com.cs.data.api.core.GenericDomain;

/**
 * A factory for creating Domain objects.
 */
@Component
public class DomainFactory implements IDomainFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.factory.IDomainFactory#getDomainObject(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * app.cs.impl.delegate.factory.IDomainFactory#getDomainObject(java.lang
	 * .String)
	 */
	@Override
	public GenericDomain getDomainObject(String type) {
		if (type.equals("MultiDimensionalObject"))
			return new MultiDimensionalObject();
		if (type.equals("HierarchicalObjet"))
			return new MultiDimensionalObject();

		return null;
	}

}
