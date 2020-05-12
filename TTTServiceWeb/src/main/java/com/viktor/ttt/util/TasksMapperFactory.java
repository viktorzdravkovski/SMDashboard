package com.viktor.ttt.util;

import com.viktor.ttt.model.UserDetails;
import com.viktor.ttt.taskTracker.v1.model.Comment;
import com.viktor.ttt.taskTracker.v1.model.Task;
import com.viktor.ttt.taskTracker.v1.model.TaskRequestBody;
import com.viktor.ttt.taskTracker.v1.model.User;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Factory class for mapping task resources.
 */
public final class TasksMapperFactory {

  /**
   * Private constructor to prevent instantiation.
   */
  private TasksMapperFactory() {
    throw new AssertionError("Do not instantiate!");
  }

  /**
   * Maps a {@link Task} object from {@link com.viktor.ttt.model.Task}.
   *
   * @param taskModel the task model.
   * @return a task resource.
   */
  public static Task mapTaskResourceFromModel(com.viktor.ttt.model.Task taskModel) {

    Integer id = taskModel.getId();
    String taskName = taskModel.getTaskName();
    String taskDescription = taskModel.getTaskDescription();
    List<com.viktor.ttt.model.User> users = taskModel.getUsers();
    List<User> usersResource = mapUserResourceListFromModelList(users);
    List<Comment> comments = extractCommentsFromTaskModel(taskModel);

    Task taskResource = new Task();
    taskResource.setId(id);
    taskResource.setName(taskName);
    taskResource.setDescription(taskDescription);
    taskResource.setAssignedUsers(usersResource);
    taskResource.setComments(comments);

    return taskResource;
  }

  /**
   * Maps a list of {@link Task} objects from a list of {@link com.viktor.ttt.model.Task}.
   *
   * @param taskList the list of task models.
   * @return a list of task resources.
   */
  public static List<Task> mapTaskResourceListFromModelList(List<com.viktor.ttt.model.Task> taskList) {
    return taskList.stream()
        .map(TasksMapperFactory::mapTaskResourceFromModel)
        .collect(Collectors.toList());
  }

  /**
   * Maps a {@link com.viktor.ttt.model.Task} object from {@link Task}.
   *
   * @param taskRequestBody the task resource.
   * @param userList        the user list.
   * @return a mapped Task model.
   */
  public static com.viktor.ttt.model.Task mapTaskModelFromResource(
      TaskRequestBody taskRequestBody,
      List<com.viktor.ttt.model.User> userList) {

    String name = taskRequestBody.getTaskName();
    String description = taskRequestBody.getTaskDescription();

    com.viktor.ttt.model.Task taskModel = new com.viktor.ttt.model.Task();
    taskModel.setTaskName(name);
    taskModel.setTaskDescription(description);
    taskModel.setUsers(userList);

    return taskModel;
  }

  /**
   * Maps a list of {@link User} objects from a list of {@link com.viktor.ttt.model.User}.
   *
   * @param userList the list of user models.
   * @return a list of user resources.
   */
  private static List<User> mapUserResourceListFromModelList(List<com.viktor.ttt.model.User> userList) {
    return userList.stream()
        .map(user -> {
          UserDetails userDetails = user.getUserDetails();
          Integer id = user.getId();
          String username = user.getUsername();
          String firstName = userDetails.getFirstName();
          String lastName = userDetails.getLastName();
          String fullName = String.format("%s %s", firstName, lastName);
          String email = userDetails.getEmail();
          String address = userDetails.getAddress();
          LocalDate dateOfBirth = userDetails.getDateOfBirth();
          OffsetDateTime createdAt = userDetails.getCreatedAt();

          User userResource = new User();
          userResource.setId(id);
          userResource.setEmail(email);
          userResource.setFullName(fullName);
          userResource.setUsername(username);
          userResource.setAddress(address);
          userResource.setDateOfBirth(dateOfBirth);
          userResource.setCreatedAt(createdAt);

          return userResource;
        })
        .collect(Collectors.toList());
  }

  /**
   * Extracts the comments from given model.
   *
   * @param task the {@link com.viktor.ttt.model.Task}.
   * @return a list of {@link Comment}.
   */
  private static List<Comment> extractCommentsFromTaskModel(com.viktor.ttt.model.Task task) {
    return task.getComments()
        .stream()
        .map(comment -> {
          int id = comment.getId();
          String content = comment.getContent();
          String user = comment.getUser().getUsername();

          Comment commentResource = new Comment();
          commentResource.setId(id);
          commentResource.setCreatedAt(OffsetDateTime.now());
          commentResource.setContent(content);
          commentResource.setUsername(user);

          return commentResource;
        })
        .collect(Collectors.toList());
  }

}
