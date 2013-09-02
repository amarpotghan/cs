package app.cs.controller.contentplanning.mam;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.fest.assertions.Assertions.assertThat;

import app.cs.actions.contentplanning.mam.GetMAMAssets;
import app.cs.model.request.StringRequest;
import app.cs.model.response.StringResponse;

@RunWith(MockitoJUnitRunner.class)
public class MAMControllerUnitTests{
	
	private GetMAMAssetController mamController;
	
	@Mock
	private GetMAMAssets getMAMAssets;
	
	@Mock
	private StringRequest request;
	
	@Before
	public void setUp(){
		
		mamController = new GetMAMAssetController(getMAMAssets,request);
		
	}
	
	@Test
	public void itShouldReturnMediaDataFromMAM(){
		
		// given
		String restURLForPIM = "/list";
		String id = "62";
		String result = "success";
		StringResponse response = new StringResponse(result);
		
		// when
		when(getMAMAssets.execute(request)).thenReturn(response);
		
		String actualResult = mamController.listForGivenId(id);
		
		//then
		verify(getMAMAssets).execute(request);
		assertThat(actualResult).isEqualTo(result);
	}
	
	@Test
	public void itShouldReturnMediaDataFromMAMWhenIdIsNotGiven() {

		// given
		String restURLForMAM = "/list";
		String result = "success";
		StringResponse response = new StringResponse(result);
		
		// when
		when(getMAMAssets.execute(null)).thenReturn(null);

		String actualResult = mamController.list();
		// then

		verify(getMAMAssets).execute(null);
		assertThat(actualResult).isEqualTo(result);

	}
	
	
	
	
}