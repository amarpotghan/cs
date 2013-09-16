package app.cs.actions.contentplanning.mam;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.mam.MamRepository;
import app.cs.model.request.StringRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@RunWith(MockitoJUnitRunner.class)
public class MAMSearchUnitTest {

	private MamSearch mamSearch;

	@Mock
	private MamRepository mamRepository;
	

	@Before
	public void setUp() {
		mamSearch = new MamSearch(mamRepository);
	}

	@Test
	public void itShouldSearchAssetsForGivenKey() {

		String expectedSearch = "Search Result";
		String id = "ABC";
		
		StringRequest stringRequest= new StringRequest(id);
		// when
		when(mamRepository.getSearchResults(id)).thenReturn(expectedSearch);
		ResponseModel searchResult = mamSearch.execute(stringRequest);

		StringResponse response = (StringResponse) searchResult;
	
		// then
		verify(mamRepository).getSearchResults(id);
		assertThat(response.getResponseString()).isEqualTo(expectedSearch);

	}
}