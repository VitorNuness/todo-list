    package com.vitornunesdasilva.todo_list.entity;

    import java.time.LocalDate;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
    import jakarta.persistence.Table;
    import jakarta.validation.constraints.NotBlank;

    @Entity
    @Table(name = "tasks")
    public class Task {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotBlank
        private String name;
        @NotBlank
        private String description;
        private boolean isComplete;
        private int priority;
        private LocalDate date;

        public Task() {
        }

        public Task(Long id, String name, String description, boolean isComplete, int priority, LocalDate date) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.isComplete = isComplete;
            this.priority = priority;
            this.date = (date != null) ? date : LocalDate.now();
        }

        public Task(String name, String description, boolean isComplete, int priority, LocalDate date) {
            this.name = name;
            this.description = description;
            this.isComplete = isComplete;
            this.priority = priority;
            this.date = (date != null) ? date : LocalDate.now();
        }

        @PrePersist
        public void prePersistDate() {
            if (this.date == null) {
                this.date = LocalDate.now();
            }
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isComplete() {
            return isComplete;
        }

        public void setComplete(boolean isComplete) {
            this.isComplete = isComplete;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = (date != null) ? date : LocalDate.now();
        }
    }
