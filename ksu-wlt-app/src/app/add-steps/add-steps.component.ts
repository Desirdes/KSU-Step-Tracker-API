import { Component } from '@angular/core';
import { AbstractControl, FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router, NavigationExtras } from '@angular/router';

@Component({
  selector: 'app-add-steps',
  templateUrl: './add-steps.component.html',
  styleUrls: ['./add-steps.component.css']
})
export class AddStepsComponent {

  //Success Message
  alert:boolean = false

  //min & max Date 
  minDate: Date;
  maxDate: Date;

  constructor(private fb: FormBuilder, private router: Router) {
    // Set the minimum to January 1st this year and December 31st this year.
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 0, 0, 1);
    this.maxDate = new Date(currentYear + 0, 11, 31);
   }

  //Steps Form Builder & Form Group
  addStepsForm = this.fb.group({
    steps: ['', [Validators.required]],
    date: ['', [Validators.required]]
  })

  getControl(name:any) : AbstractControl | null {
    return this.addStepsForm.get(name)
  }

  onSubmit() {
    this.alert = true
    this.addStepsForm.reset({})
  }

  closeAlert() {
    this.alert = false
  }
}
