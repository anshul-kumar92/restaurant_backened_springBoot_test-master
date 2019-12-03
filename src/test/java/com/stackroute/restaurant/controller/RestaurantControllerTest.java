/**
 * 
 */
package com.stackroute.restaurant.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.stackroute.restaurant.model.Restaurant;
import com.stackroute.restaurant.service.RestaurantService;

/**
 * @author 26647
 *
 */
public class RestaurantControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private static final int UNKNOWN_ID = Integer.MAX_VALUE;

	@Mock
	private RestaurantService restaurentService;

//	@Mock
//	private Restaurant restaurant;

	private MockMvc mockMvc;

	@InjectMocks
	private RestaurantController restaurantController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).addFilters(new CorsFilter()).build();
	}

	@Test
	public void test_getAll() throws Exception {
		List<Restaurant> rest = Arrays.asList(new Restaurant(2, "Ajay", "itc", "veg", "img", "good"));
		when(restaurentService.findAll()).thenReturn(rest);

		// Running

//		mockMvc.perform(get("/api/getall")).andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
//		verify(restaurentService, times(1)).findAll();
//		verifyNoMoreInteractions(restaurentService);

		mockMvc.perform(get("/api/getall")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].id", is(2)))
				.andExpect(jsonPath("$[0].name", is("Ajay"))).andExpect(jsonPath("$[0].location", is("itc")))
				.andExpect(jsonPath("$[0].cuisines", is("veg"))).andExpect(jsonPath("$[0].image", is("img")))
				.andExpect(jsonPath("$[0].review", is("good")));
		verify(restaurentService, times(1)).findAll();
		verifyNoMoreInteractions(restaurentService);
	}

	@Test
	public void createNewUser_test() throws Exception {
		String url = "http://localhost:8081/api/restaurant";

		Restaurant rest = new Restaurant();
		rest.setId(3);
		rest.setName("tt");
		rest.setLocation("itc");
		rest.setImage("im");
		rest.setReview("rv");

		Gson gson = new Gson();
		String json = gson.toJson(rest);

		mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
				.andReturn();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test_update_user() throws Exception {
		// String url = "http://localhost:8081/api/update";

		Restaurant rest = new Restaurant();
		rest.setId(3);
		rest.setName("Ajay");
		rest.setLocation("itc");
		rest.setCuisines("veg");
		rest.setImage("img1");
		rest.setReview("good");

		when(restaurentService.findById(rest.getId())).thenReturn(rest);
		// doNothing().when(restaurentService).update(rest);
		mockMvc.perform(put("/api/update/{id}", rest.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(rest))).andExpect(status().isOk());
		// verify(restaurentService, times(1)).findById(rest.getId());
		verify(restaurentService, times(1)).update(Matchers.refEq(rest));
		verifyNoMoreInteractions(restaurentService);
	}

	@Test
	public void test_update_fail_404_not_found() throws Exception {
		Restaurant rest = new Restaurant(UNKNOWN_ID, "Ajay", "itc", "veg", "img1", "good");

		when(restaurentService.findById(rest.getId())).thenReturn(null);
		mockMvc.perform(put("/api/update/{id}", rest.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(rest))).andExpect(status().isNotFound());
		// .andExpect(status().isOk());
		verify(restaurentService, times(1)).findById(rest.getId());
		// verify(restaurentService, times(1)).update(Matchers.refEq(rest));
		verifyNoMoreInteractions(restaurentService);
	}

	@Test
	public void test_getRest_ById() throws Exception {
		Restaurant rest = new Restaurant();
		rest.setId(3);
		rest.setName("Ajay");
		rest.setLocation("itc");
		rest.setCuisines("veg");
		rest.setImage("img1");
		rest.setReview("good");

		when(restaurentService.findById(3)).thenReturn(rest);
		mockMvc.perform(get("/api/getall/{id}", 3)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id", is(3)));
		verify(restaurentService, times(1)).findById(3);
		verifyNoMoreInteractions(restaurentService);

	}

	@Test
	public void test_deleteRest() throws Exception {
		Restaurant rest = new Restaurant();
		rest.setId(1);
		rest.setName("Ajay");
		rest.setLocation("itc");
		rest.setCuisines("veg");
		rest.setImage("img1");
		rest.setReview("good");

		when(restaurentService.findById(rest.getId())).thenReturn(rest);
		//doNothing().when(restaurentService).delete(rest.getId());
		
		mockMvc.perform(
                delete("/users/{id}", rest.getId()))
                .andExpect(status().isOk());
		verify(restaurentService, times(1)).findById(rest.getId());
        verify(restaurentService, times(1)).delete(rest.getId());
        verifyNoMoreInteractions(restaurentService);
		
	}

	/*
	 * converts a Java object into JSON representation
	 */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
