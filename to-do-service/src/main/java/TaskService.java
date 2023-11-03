@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task addTask(Task task) {
        // Validação de entrada
        if (task.getId() != null) {
            throw new IllegalArgumentException("O ID da tarefa deve ser nulo ao adicionar uma nova tarefa.");
        }

        task.setId(nextId++);
        tasks.add(task);
        return task;
    }

    public Task editTask(Long taskId, Task updatedTask) {
        // Validação de entrada
        if (updatedTask.getId() == null) {
            throw new IllegalArgumentException("O ID da tarefa deve ser fornecido ao editar uma tarefa.");
        }

        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                // Atualize os detalhes da tarefa
                task.setDescription(updatedTask.getDescription());
                task.setDueDate(updatedTask.getDueDate());
                task.setCompleted(updatedTask.isCompleted());
                return task;
            }
        }
        throw new TaskNotFoundException("Tarefa não encontrada com o ID: " + taskId);
    }

    public void deleteTask(Long taskId) {
        tasks.removeIf(task -> task.getId().equals(taskId));
    }
}
