package com.ps.todoapp.service;

import com.ps.todoapp.entity.task.Priority;
import com.ps.todoapp.entity.task.SortBy;
import com.ps.todoapp.entity.task.Task;
import com.ps.todoapp.entity.user.User;
import com.ps.todoapp.repository.TaskRepository;
import com.ps.todoapp.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class TaskServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private UserDetails userDetails;
    private User user;
    private Task task1;
    private Task task2;

    @Before
    void setUp() {
        userDetails = mock(UserDetails.class);
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");
        task1.setPriority(Priority.HIGH);
        task1.setUser(user);

        task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");
        task2.setPriority(Priority.LOW);
        task2.setUser(user);
    }

    @Test
    void testGetAll_withPriority() {
        when(userRepository.findByUsername(userDetails.getUsername())).thenReturn(Optional.of(user));
        when(taskRepository.findAllByUserIdAndPriority(user.getId(), Priority.HIGH)).thenReturn(Collections.singletonList(task1));

        List<Task> tasks = taskService.getAll(userDetails, Priority.HIGH, null, false);

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
    }

    @Test
    void testGetAll_withoutPriority() {
        when(userRepository.findByUsername(userDetails.getUsername())).thenReturn(Optional.of(user));
        when(taskRepository.findAllByUserId(user.getId())).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.getAll(userDetails, null, null, false);

        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());
    }

    @Test
    void testGetAll_withSorting() {
        when(userRepository.findByUsername(userDetails.getUsername())).thenReturn(Optional.of(user));
        when(taskRepository.findAllByUserId(user.getId())).thenReturn(Arrays.asList(task2, task1));

        List<Task> tasks = taskService.getAll(userDetails, null, SortBy.TITLE, true);

        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        assertEquals("Task 2", tasks.get(0).getTitle());
        assertEquals("Task 1", tasks.get(1).getTitle());
    }

    @Test
    void testGetAll_withInvalidUser() {
        when(userRepository.findByUsername(userDetails.getUsername())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> taskService.getAll(userDetails, null, null, false));
    }

    @Test
    void testGetAll_withEmptyResult() {
        when(userRepository.findByUsername(userDetails.getUsername())).thenReturn(Optional.of(user));
        when(taskRepository.findAllByUserId(user.getId())).thenReturn(Collections.emptyList());

        List<Task> tasks = taskService.getAll(userDetails, null, null, false);

        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }
}
