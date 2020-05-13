import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { AuthService, AppInfoService } from '../../services';
import { DxButtonModule } from 'devextreme-angular/ui/button';
import { DxCheckBoxModule } from 'devextreme-angular/ui/check-box';
import { DxTextBoxModule } from 'devextreme-angular/ui/text-box';
import { DxValidatorModule } from 'devextreme-angular/ui/validator';
import { DxValidationGroupModule } from 'devextreme-angular/ui/validation-group';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {
  loginMode = true;
  login = '';
  password = '';
  email = '';
  firstName = '';
  lastName = '';

  constructor(
    private authService: AuthService,
    public appInfo: AppInfoService,
    private router: Router
  ) {}

  onLoginClick(args) {
    if (!args.validationGroup.validate().isValid) {
      return;
    }
    console.log(args);
    const formGroup = new FormGroup({
      username: new FormControl(this.login),
      password: new FormControl(this.password)
    });
    this.authService.logIn(this.login, this.password);
    args.validationGroup.reset();
  }

  onRegisterClick(args) {
    if (!args.validationGroup.validate().isValid) {
      return;
    }

    this.authService.register(
      this.login,
      this.password,
      this.email,
      this.firstName,
      this.lastName
    );
    args.validationGroup.reset();
  }

  toggleLoginMode() {
    this.loginMode = !this.loginMode;
  }
}
@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    DxButtonModule,
    DxCheckBoxModule,
    DxTextBoxModule,
    DxValidatorModule,
    DxValidationGroupModule
  ],
  declarations: [LoginFormComponent],
  exports: [LoginFormComponent]
})
export class LoginFormModule {}
