import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { Router } from '@angular/router';
import { CartService } from '../_services/cart.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {
  form: any = {
    username: null,
    email: null,
    password: null,
    age: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private cartService: CartService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { username, email, password, age } = this.form;

    this.authService.register(username, email, password, age).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        let userId = 0;
        this.authService.getUserId(username).subscribe(
          id => {
            userId = id;
            this.cartService.addCartForUser({}, userId).subscribe(
              data2 => {
                console.log(data2);
              },
              err2 => {
                console.error(err2);
              }
            );
          },
          error => {
            console.error(error);
          }
        );
        this.goToLogin();
      },

      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

}
