package com.ps.todoapp;

import com.ps.todoapp.task.Priority;
import com.ps.todoapp.task.Task;
import com.ps.todoapp.task.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static com.ps.todoapp.task.Priority.HIGH;
import static com.ps.todoapp.task.Priority.LOW;
import static com.ps.todoapp.task.Priority.MEDIUM;


@SpringBootApplication
@EnableAspectJAutoProxy
public class TodoappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoappApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(TaskRepository repository) {
        return (args -> {
            System.out.println("Inserting test data");
            insertTestData(repository);
        });
    }

    void insertTestData(TaskRepository repository) {
        repository.save(new Task("Finish the report", "Complete the quarterly report by end of the day", HIGH));
        repository.save(new Task("Check emails", "Go through and respond to urgent emails", MEDIUM));
        repository.save(new Task("Organize desk", "Clean and organize your workspace", LOW));
        repository.save(new Task("Prepare presentation", "Create slides for the next client meeting", HIGH));
        repository.save(new Task("Team meeting", "Discuss project progress with the team", MEDIUM));
        repository.save(new Task("Plan vacation", "Research vacation options for the next holiday", LOW));
        repository.save(new Task("Buy groceries", "Purchase ingredients for dinner", MEDIUM));
        repository.save(new Task("Submit project", "Final submission of the current project", HIGH));
        repository.save(new Task("Write blog post", "Write a new article for the company blog", MEDIUM));
        repository.save(new Task("Read book", "Finish reading the book for personal development", LOW));
    }
}
