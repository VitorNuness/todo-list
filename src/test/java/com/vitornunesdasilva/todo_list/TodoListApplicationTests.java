package com.vitornunesdasilva.todo_list;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.vitornunesdasilva.todo_list.entity.Task;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class TodoListApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTaskSuccess() {
		var task = new Task("task 1", "description task 1", false, 1, LocalDate.now());

		webTestClient
			.post()
            .uri("/tasks")
            .bodyValue(task)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$").isArray()
            .jsonPath("$.length()").isEqualTo(1)
            .jsonPath("$[0].name").isEqualTo(task.getName())
            .jsonPath("$[0].description").isEqualTo(task.getDescription())
            .jsonPath("$[0].complete").isEqualTo(task.isComplete())
            .jsonPath("$[0].priority").isEqualTo(task.getPriority())
            .jsonPath("$[0].date").isEqualTo(task.getDate().toString());
	}

    @Test
	void testCreateTaskFailure() {
		var task = new Task("", "", false, 0, LocalDate.now());

		webTestClient
			.post()
            .uri("/tasks")
            .bodyValue(task)
            .exchange()
            .expectStatus().isBadRequest();
	}
}
