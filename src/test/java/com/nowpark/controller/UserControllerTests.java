package com.nowpark.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nowpark.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {

	private MockMvc mockMvc;

	@Resource
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void createUser() throws Exception {
		String userJson = User.builder()
				.name("aaa")
				.email("aaa@gmail.com")
				.build()
				.toJson();

		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status()
						.is2xxSuccessful())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.name").doesNotExist())
				.andExpect(jsonPath("$.email").doesNotExist())
				.andExpect(jsonPath("$.registeredAt").doesNotExist());
	}

	@Test
	public void getUser() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{id}", 1))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status()
						.is2xxSuccessful())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.name").isString())
				.andExpect(jsonPath("$.email").isString())
				.andExpect(jsonPath("$.registeredAt").isNotEmpty());
	}
}
