package app.cs.controller.publicationplanning.perspective;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.publicationplanning.perspective.SwitchPerspective;
import app.cs.boundary.delivery.Interactor;
import app.cs.controller.publicationplanning.perspective.SwitchPerspectiveController;
import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.model.request.CreateDimensionRequest;
import app.cs.model.request.StringRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;
import app.cs.model.response.TreeResponse;

@RunWith(MockitoJUnitRunner.class)
public class SwitchPerspectiveControllerUnitTests{
	
	private SwitchPerspectiveController switchPerspectiveController;	
	
	@Mock
	private Interactor switchPerspective;
	
    @Mock
	private StringRequest request;
    
    @Before
    public void setUp(){
    	switchPerspectiveController = new SwitchPerspectiveController(switchPerspective, request);
    }
	
    
    @Test
    public void itShouldGetDimensions(){
    	//given
   
    	TreeResponse treeResponse = new TreeResponse(null);
    	String structure = "MasterPublication-Campaign-Publication";
    	request.setStringRequest(structure);
    	
    	//when
    	when(switchPerspective.execute(request)).thenReturn(treeResponse);
    	switchPerspectiveController.getDimensionsBy(structure);
    	
    	//then
    	verify(switchPerspective).execute(request);
    	
    	
    }
}