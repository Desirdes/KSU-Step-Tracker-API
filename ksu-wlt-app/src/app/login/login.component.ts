import { Component, forwardRef } from '@angular/core';
import { AbstractControl, FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { APIService } from '../shared/APIService';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private apiService: APIService,
    private appComponent: AppComponent
  ) { }

  loginForm = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]]
  })

  getControl(name:any) : AbstractControl | null {
    return this.loginForm.get(name)

  }

  //For backend
  get username() { return this.loginForm.get('username'); }
  get password() { return this.loginForm.get('password'); }

  public onsubmit() {
    this.loginUser();
  }

  private async loginUser(){
    // Login user then send to dashboard
    await this.apiService.loginUser(this.loginForm.get('username').value, this.loginForm.get('password').value).then(loginResponse => {
        // On successful login set the user basic auth
        this.apiService.userBasicAuth = btoa(this.loginForm.get('username').value + ":" + this.loginForm.get('password').value);

        // Set the personID and username for logged in user
        this.appComponent.currentPerson.id = loginResponse.personID;
        this.appComponent.currentPerson.username = loginResponse.username;

        // Route to dashboard
        this.router.navigate(['/user-dashboard']);
      }, error => {
        console.log("error: " + error);
          // handle error here
    });
  }
}
