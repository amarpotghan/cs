package app.cs.controller.publicationplanning.dimension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.publicationplanning.dimension.CreateDimension;
import app.cs.model.request.CreateDimensionRequest;
import app.cs.model.response.StringResponse;

@RunWith(MockitoJUnitRunner.class)
public class CreateDimensionControllerUnitTests{
	
	private CreateDimensionController createDimensionController;
	
	@Mock
	private CreateDimension createDimension;
	
	@Mock
	private CreateDimensionRequest createDimensionRequest;
	
	@Before
	public void setUp() {
		createDimensionController = new CreateDimensionController(createDimension,
				createDimensionRequest);

	}
	
	@Test
	public void itShouldCreateDimension(){
		
		//given
		String type= "folder";
		String name= "CH02";
		String path= "MP01,P03";
		Boolean isFolder= true;
		StringResponse response = new StringResponse("success");
		
		createDimensionRequest.setFolder(isFolder);
		createDimensionRequest.setName(name);
		createDimensionRequest.setPath(path);
		createDimensionRequest.setType(type);
		
		//when
		when(createDimension.execute(createDimensionRequest)).thenReturn(response);
		createDimensionController.create(type, name, path, isFolder);
		
		//then
		verify(createDimension).execute(createDimensionRequest);
		
		
	}
	
}