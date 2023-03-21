import { Component } from '@angular/core';
import { AbstractControl, FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signupForm = this.fb.group({
    name: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    username: ['', [Validators.required]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    confirmPassword: ['', [Validators.required]]
  },
  
  {
    validator: this.MustMatch('password', 'confirmPassword')
  })

  //Get ControlName function
  getControl(name:any) : AbstractControl | null {
    return this.signupForm.get(name)
    
  }

  //Must Match Validator function
  MustMatch(controlName: string, matchingControlName: string){
    return(formGroup:FormGroup)=>{
      const control =formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if(matchingControl.errors && !matchingControl.errors['MustMatch']){
        return
      }
      if(control.value !== matchingControl.value){
        matchingControl.setErrors({MustMatch:true});
      }
      else {
        matchingControl.setErrors(null)
      }
    }
  }

  constructor(private fb: FormBuilder, private router: Router) { }

  //For backend
  get name() { return this.signupForm.get('name'); }
  get email() { return this.signupForm.get('email'); }
  get username() { return this.signupForm.get('username'); }
  get password() { return this.signupForm.get('password'); }
  get confirmPassword() { return this.signupForm.get('confirmPassword'); }


  public onsubmit() {
    this.router.navigate(['/questionnaire']);
  }

}

