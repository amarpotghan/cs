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
public class MAMSearchControllerUnitTest{
	
	private MAMSearchController mamSearchController;
	
	@Mock
	private MAMSearch mamSearch;
	
	@Mock
	private StringRequest request;
	
	@Before
	public void setUp(){
		
		mamSearchController = new MAMSearchController(mamSearch, request);
		
	}
	

	@Test
	public void itShouldReturnSearchResultsFromMAMTree(){
		//given
		
		
		
	}
}