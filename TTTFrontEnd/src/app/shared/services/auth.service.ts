import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from 'swagger-auth-client';
import notify from 'devextreme/ui/notify';

@Injectable()
export class AuthService {
  loggedIn = '';

  constructor(
    private router: Router,
    private http: HttpClient,
    private swaggerAuth: AuthenticationService
  ) {
    this.loggedIn = localStorage.getItem('active-user');
  }

  logIn(login, password) {
    this.swaggerAuth.loginUser({ username: login, password }).subscribe(
      res => {
        console.log(res);
        this.loggedIn = 'true';
        this.router.navigate(['/']);
        notify('Logged in!', 'success', 1600);
        this.http.get('http://localhost:8080/user').subscribe(res => {
          console.log(res);
        });
        localStorage.setItem('active-user', 'true');
      },
      errorRes => {
        notify('Error while logging in, please try again.', 'error', 1600);
      }
    );
    // this.router.navigate(['/']);
    // this.http.post('http://localhost:8080/login', form.value).subscribe(res => {
    //   console.log(res);
    // });
  }

  register(
    username: string,
    password: string,
    email: string,
    firstName: string,
    lastName: string
  ) {
    this.swaggerAuth
      .registerUser({ username, password, email, firstName, lastName })
      .subscribe(
        res => {
          notify('User created!', 'success', 1600);
          this.loggedIn = 'true';
          this.router.navigate(['/']);
        },
        errorRes => {
          notify('An error occurred.', 'error', 1600);
        }
      );
  }

  logOut() {
    this.loggedIn = '';
    notify('Logged out.', 'success', 1600);
    localStorage.setItem('active-user', '');
    this.router.navigate(['/login-form']);
  }

  get isLoggedIn() {
    if (!this.loggedIn) {
      return false;
    }
    return true;
  }
}

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const isLoggedIn = this.authService.isLoggedIn;
    const isLoginForm = route.routeConfig.path === 'login-form';

    if (isLoggedIn && isLoginForm) {
      this.router.navigate(['/']);
      return false;
    }

    if (!isLoggedIn && !isLoginForm) {
      this.router.navigate(['/login-form']);
    }

    return isLoggedIn || isLoginForm;
  }
}
