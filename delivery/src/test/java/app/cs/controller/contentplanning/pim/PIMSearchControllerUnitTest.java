package app.cs.controller.contentplanning.pim;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.contentplanning.pim.PIMSearch;
import app.cs.model.request.StringRequest;
import app.cs.model.response.StringResponse;

@RunWith(MockitoJUnitRunner.class)
public class PIMSearchControllerUnitTest {

	private PIMSearchController pimSearchController;

	@Mock
	private PIMSearch pimSearch;

	@Mock
	private StringRequest request;

	@Before
	public void setUp() {

		pimSearchController = new PIMSearchController(pimSearch, request);

	}

	@Test
	public void itShouldReturnSearchResultsFromPIMTree() {
		// given
		String key = "ABC";
		String result = "Search Result";
		StringResponse response = new StringResponse(result);

		// when
		when(pimSearch.execute(request)).thenReturn(response);

		String actualResult = pimSearchController.searchPIM(key);

		// then
		verify(pimSearch).execute(request);
		assertThat(actualResult).isEqualTo(result);

	}
}