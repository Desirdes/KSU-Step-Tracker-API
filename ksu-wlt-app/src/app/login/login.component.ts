import { Component, forwardRef } from '@angular/core';
import { AbstractControl, FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
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
    this.router.navigate(['/user-profile']);
  }

  constructor(private fb: FormBuilder, private router: Router) { }
}
