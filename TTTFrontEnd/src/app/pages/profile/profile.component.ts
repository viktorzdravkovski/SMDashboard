import { Component, OnInit } from '@angular/core';
import { User, TaskTrackerService } from 'swagger-client';

@Component({
  templateUrl: 'profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  employee: User;
  colCountByScreen: object;

  constructor(private taskService: TaskTrackerService) {
    this.colCountByScreen = {
      xs: 1,
      sm: 2,
      md: 3,
      lg: 4
    };
  }

  ngOnInit() {}
}
