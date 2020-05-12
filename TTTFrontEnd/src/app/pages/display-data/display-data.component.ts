import { Component, OnInit } from '@angular/core';
import DataSource from 'devextreme/data/data_source';
import { TaskTrackerService, Task, AddTaskRequestBody } from 'swagger-client';
import notify from 'devextreme/ui/notify';

@Component({
  templateUrl: 'display-data.component.html'
})
export class DisplayDataComponent implements OnInit {
  dataSource: DataSource;
  assignedUsersDataSource = [];
  constructor(private taskService: TaskTrackerService) {}

  ngOnInit() {
    this.taskService.getTasks().subscribe(res => {
      res.forEach(e => {
        this.assignedUsersDataSource.push(e.assignedUsers);
      });
      this.dataSource = new DataSource({
        store: res
      });
    });
  }

  renderUsers(rowData) {
    if (!rowData.assignedUsers) {
      return;
    }
    return rowData.assignedUsers.map(user => '\n' + user.fullName);
  }

  saveChanges(rowData) {
    console.log(rowData);
    const task: AddTaskRequestBody = {
      task: {
        taskName: rowData.data.name,
        taskDescription: rowData.data.description,
        assignedUsernames: rowData.data.assignedUsernames.map(e => e.username)
      }
    };
    console.log(task);
    this.taskService.addTask(task).subscribe(
      res => {
        notify('Values changed!', 'success', 1600);
      },
      errorRes => {
        notify('There was an error.', 'error', 1600);
      }
    );
  }

  deleteTask(rowData) {
    this.taskService.deleteTask(rowData.data.id).subscribe(
      res => {
        notify('Task deleted.', 'success', 1600);
      },
      errorRes => {
        notify('An error occurred.', 'error', 1600);
      }
    );
  }
}
