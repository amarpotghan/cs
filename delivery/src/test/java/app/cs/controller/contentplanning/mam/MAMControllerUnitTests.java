package app.cs.controller.contentplanning.mam;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.fest.assertions.Assertions.assertThat;

import app.cs.actions.contentplanning.assortment.MAMInteractions;
import app.cs.controller.contentplanning.mam.MAMController;

@RunWith(MockitoJUnitRunner.class)
public class MAMControllerUnitTests{
	
	private MAMController mamController;
	
	@Mock
	private MAMInteractions mamInteractions;
	
	@Before
	public void setUp(){
		
		mamController = new MAMController(mamInteractions);
		
	}
	
	@Test
	public void itShouldReturnMediaDataFromMAM(){
		
		// given
		String restURLForPIM = "/list";
		String id = "62";
		String result = "success";
		
		// when
		when(mamInteractions.getAssets(id)).thenReturn(result);
		
		String actualResult = mamController.listForGivenId(id);
		
		//then
		verify(mamInteractions).getAssets(id);
		assertThat(actualResult).isEqualTo(result);
	}
	
	@Test
	public void itShouldReturnMediaDataFromMAMWhenIdIsNotGiven() {

		// given
		String restURLForMAM = "/list";

		String result = "success";
		// when
		when(mamInteractions.getAssets(null)).thenReturn(result);

		String actualResult = mamController.list();
		// then

		verify(mamInteractions).getAssets(null);
		assertThat(actualResult).isEqualTo(result);

	}
	
	
	
	
}