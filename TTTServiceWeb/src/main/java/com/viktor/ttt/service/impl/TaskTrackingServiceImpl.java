package com.viktor.ttt.service.impl;

import com.viktor.ttt.model.Comment;
import com.viktor.ttt.model.User;
import com.viktor.ttt.repository.CommentRepository;
import com.viktor.ttt.repository.TaskRepository;
import com.viktor.ttt.repository.UserRepository;
import com.viktor.ttt.service.TaskTrackingService;
import com.viktor.ttt.taskTracker.v1.model.AddCommentConfirmation;
import com.viktor.ttt.taskTracker.v1.model.AddTaskConfirmation;
import com.viktor.ttt.taskTracker.v1.model.CommentBody;
import com.viktor.ttt.taskTracker.v1.model.DeleteCommentConfirmation;
import com.viktor.ttt.taskTracker.v1.model.DeleteTaskConfirmation;
import com.viktor.ttt.taskTracker.v1.model.Task;
import com.viktor.ttt.taskTracker.v1.model.TaskRequestBody;
import com.viktor.ttt.util.TasksMapperFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link TaskTrackingService}.
 */
@Service
public class TaskTrackingServiceImpl implements TaskTrackingService {

  private final TaskRepository taskRepository;
  private final UserRepository userRepository;
  private final CommentRepository commentRepository;

  /**
   * The constructor with the mandatory parameters.
   *
   * @param taskRepository    the task repository.
   * @param userRepository    the user repository.
   * @param commentRepository the comment repository.
   */
  public TaskTrackingServiceImpl(
      TaskRepository taskRepository,
      UserRepository userRepository,
      CommentRepository commentRepository) {
    this.taskRepository = taskRepository;
    this.userRepository = userRepository;
    this.commentRepository = commentRepository;
  }

  @Override
  public List<Task> getAllTasks() {

    List<com.viktor.ttt.model.Task> tasks = taskRepository.findAll();
    return TasksMapperFactory.mapTaskResourceListFromModelList(tasks);
  }

  @Override
  public Task getTask(String id) {

    int taskId = Integer.parseInt(id);
    Optional<com.viktor.ttt.model.Task> optionalTask = taskRepository.findById(taskId);
    if (optionalTask.isPresent()) {

      com.viktor.ttt.model.Task task = optionalTask.get();
      return TasksMapperFactory.mapTaskResourceFromModel(task);
    }

    return null;
  }

  @Override
  public AddTaskConfirmation addTask(TaskRequestBody task) {

    List<String> assignedUsernames = task.getAssignedUsernames();
    List<User> allUsersByUsername = userRepository.findAllByUsernameIsIn(assignedUsernames);

    com.viktor.ttt.model.Task taskModel = TasksMapperFactory.mapTaskModelFromResource(task, allUsersByUsername);
    taskRepository.save(taskModel);

    AddTaskConfirmation addTaskConfirmation = new AddTaskConfirmation();
    addTaskConfirmation.setMessage("Task added successfully");
    return addTaskConfirmation;
  }

  @Override
  public DeleteTaskConfirmation deleteTask(String id) {

    int taskId = Integer.parseInt(id);
    taskRepository.deleteById(taskId);

    DeleteTaskConfirmation deleteTaskConfirmation = new DeleteTaskConfirmation();
    deleteTaskConfirmation.setMessage("Task deleted successfully");
    return deleteTaskConfirmation;
  }

  @Override
  public AddCommentConfirmation addComment(CommentBody commentBody) {

    Integer taskId = commentBody.getTaskId();
    com.viktor.ttt.model.Task task = taskRepository.getOne(taskId);
    Integer userId = commentBody.getUserId();
    User user = userRepository.getOne(userId);
    String content = commentBody.getContent();

    Comment comment = new Comment();
    comment.setTask(task);
    comment.setUser(user);
    comment.setContent(content);
    commentRepository.save(comment);

    AddCommentConfirmation addCommentConfirmation = new AddCommentConfirmation();
    addCommentConfirmation.setMessage("Added comment successfully");
    return addCommentConfirmation;
  }

  @Override
  public DeleteCommentConfirmation deleteComment(String id) {

    int taskId = Integer.parseInt(id);
    commentRepository.deleteById(taskId);

    DeleteCommentConfirmation deleteCommentConfirmation = new DeleteCommentConfirmation();
    deleteCommentConfirmation.setMessage("Comment deleted successfully");
    return deleteCommentConfirmation;
  }
}
