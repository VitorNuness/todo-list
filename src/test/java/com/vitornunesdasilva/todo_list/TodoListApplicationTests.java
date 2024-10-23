package com.vitornunesdasilva.todo_list;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.vitornunesdasilva.todo_list.entity.Task;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql("/remove.sql")
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

    @Test
    @Sql("/import.sql")
    public void testListTasks() {
        webTestClient
                .get()
                .uri("/tasks")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(5);
    }

    @Test
    @Sql("/import.sql")
    public void testUpdateTaskSuccess()
    {
        var task = new Task(9999L, "task atualizada", "description task 1", false, 1, LocalDate.now());

        webTestClient
            .put()
            .uri("/tasks")
            .bodyValue(task)
            .exchange()
                .expectStatus().isOk()
            .expectBody()
            .jsonPath("$").isArray()
            .jsonPath("$.length()").isEqualTo(5)
            .jsonPath("$[4].name").isEqualTo(task.getName())
            .jsonPath("$[4].description").isEqualTo(task.getDescription())
            .jsonPath("$[4].complete").isEqualTo(task.isComplete())
            .jsonPath("$[4].priority").isEqualTo(task.getPriority())
            .jsonPath("$[4].date").isEqualTo(task.getDate().toString());
    }

    @Test
    @Sql("/import.sql")
    public void testUpdateTaskFailure()
    {
        var task = new Task(9999L, "", "", false, 1, LocalDate.now());

        webTestClient
                .put()
                .uri("/tasks")
                .bodyValue(task)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Sql("/import.sql")
    public void testDeleteTaskSuccessfull() {
        webTestClient
            .delete()
            .uri("/tasks/9999")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(4);
    }
}
