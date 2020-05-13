import { Component, OnInit } from '@angular/core';
import { User, TaskTrackerService } from 'swagger-client';
import { UserInformationService } from 'swagger-user-info-client';
import { CurrentUserResponse } from 'projects/swagger-user-info-client/src';
import notify from 'devextreme/ui/notify';
import { HttpClient } from '@angular/common/http';

@Component({
  templateUrl: 'profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  employee: CurrentUserResponse;
  colCountByScreen: object;

  constructor(
    private userInfo: UserInformationService,
    private http: HttpClient
  ) {
    this.colCountByScreen = {
      xs: 1,
      sm: 2,
      md: 3,
      lg: 4
    };
  }

  ngOnInit() {
    this.userInfo.getCurrentUser().subscribe(
      res => {
        this.employee = res;
      },
      catchErr => {
        notify('An error occured.', 'error', 1600);
      }
    );

    this.http.get('http://localhost:8080/user').subscribe(res => {
      console.log(res);
    });
  }
}
