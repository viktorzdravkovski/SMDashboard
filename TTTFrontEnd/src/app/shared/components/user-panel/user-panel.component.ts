import { Component, NgModule, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DxListModule } from 'devextreme-angular/ui/list';
import { DxContextMenuModule } from 'devextreme-angular/ui/context-menu';
import { CurrentUserResponse } from 'projects/swagger-user-info-client/src';
import { UserInformationService } from 'swagger-user-info-client';
import notify from 'devextreme/ui/notify';

@Component({
  selector: 'app-user-panel',
  templateUrl: 'user-panel.component.html',
  styleUrls: ['./user-panel.component.scss']
})
export class UserPanelComponent {
  currentUser: string;
  @Input()
  menuItems: any;

  @Input()
  menuMode: string;

  constructor(private userInfo: UserInformationService) {
    userInfo.getCurrentUser().subscribe(
      res => {
        this.currentUser = res.fullName;
      },
      catchErr => {
        notify('Error.', 'error', 1600);
      }
    );
  }
}

@NgModule({
  imports: [DxListModule, DxContextMenuModule, CommonModule],
  declarations: [UserPanelComponent],
  exports: [UserPanelComponent]
})
export class UserPanelModule {}
