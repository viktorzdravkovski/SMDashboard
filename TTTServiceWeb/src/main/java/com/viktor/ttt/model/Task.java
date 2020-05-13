package com.viktor.ttt.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Hibernate entity for TASK.
 */
@Entity
@Table(name = "TASK")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TASK_ID")
  private Integer id;

  @Column(name = "TASK_NAME")
  private String taskName;

  @Column(name = "TASK_DESCRIPTION")
  private String taskDescription;

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "USER_TASK",
      joinColumns = {@JoinColumn(name = "TASK_ID")},
      inverseJoinColumns = {@JoinColumn(name = "AUTH_USER_ID")}
  )
  public List<User> users = new ArrayList<>();

  @OneToMany(
      targetEntity = Comment.class,
      mappedBy = "task",
      orphanRemoval = true,
      cascade = CascadeType.REMOVE)
  @Fetch(FetchMode.SUBSELECT)
  private List<Comment> comments = new ArrayList<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTaskDescription() {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public void addComment(Comment comment) {
    this.comments.add(comment);
  }
}