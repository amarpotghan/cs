package app.cs.controller.contentplanning.mam;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.contentplanning.mam.MamSearch;
import app.cs.model.request.StringRequest;
import app.cs.model.response.StringResponse;

@RunWith(MockitoJUnitRunner.class)
public class MAMSearchControllerUnitTest {

	private MAMSearchController mamSearchController;

	@Mock
	private MamSearch mamSearch;

	@Mock
	private StringRequest request;

	@Before
	public void setUp() {

		mamSearchController = new MAMSearchController(mamSearch, request);

	}

	@Test
	public void itShouldReturnSearchResultsFromMAMTree() {
		// given
		String key = "ABC";
		String result = "Search Result";
		StringResponse response = new StringResponse(result);

		// when
		when(mamSearch.execute(request)).thenReturn(response);

		String actualResult = mamSearchController.searchMAM(key);

		// then
		verify(mamSearch).execute(request);
		assertThat(actualResult).isEqualTo(result);

	}
}