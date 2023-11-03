import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        // Inicialize o serviço antes de cada teste
        taskService = new TaskService();
    }

    @Test
    public void testAddTask() {
        Task task = new Task();
        task.setDescription("Nova tarefa");
        task.setDueDate(null);

        Task addedTask = taskService.addTask(task);

        assertNotNull(addedTask.getId());
        assertEquals("Nova tarefa", addedTask.getDescription());

        // Verifique se a tarefa foi adicionada à lista
        List<Task> tasks = taskService.getAllTasks();
        assertTrue(tasks.contains(addedTask));
    }

    @Test
    public void testEditTask() {
        // Adicione uma tarefa para editar
        Task task = new Task();
        task.setDescription("Tarefa existente");
        task.setDueDate(null);
        Task addedTask = taskService.addTask(task);

        // Atualize a tarefa
        addedTask.setDescription("Tarefa atualizada");
        Task updatedTask = taskService.editTask(addedTask.getId(), addedTask);

        assertNotNull(updatedTask);
        assertEquals("Tarefa atualizada", updatedTask.getDescription());

        // Verifique se a tarefa foi atualizada corretamente
        List<Task> tasks = taskService.getAllTasks();
        Task foundTask = tasks.stream().filter(t -> t.getId().equals(addedTask.getId())).findFirst().orElse(null);
        assertNotNull(foundTask);
        assertEquals("Tarefa atualizada", foundTask.getDescription());
    }

    @Test
    public void testDeleteTask() {
        // Adicione uma tarefa para excluir
        Task task = new Task();
        task.setDescription("Tarefa para exclusão");
        task.setDueDate(null);
        Task addedTask = taskService.addTask(task);

        // Exclua a tarefa
        taskService.deleteTask(addedTask.getId());

        // Verifique se a tarefa foi removida da lista
        List<Task> tasks = taskService.getAllTasks();
        assertFalse(tasks.contains(addedTask));
    }
}
