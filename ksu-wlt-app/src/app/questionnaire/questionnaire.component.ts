import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css']
})
export class QuestionnaireComponent {
  questionnaireForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    age: new FormControl(''),
    dateOfBirth: new FormControl(''),
    sex: new FormControl(''),
    race: new FormControl(''),
    height: new FormControl(''),
    weight: new FormControl(''),
    waistCircumference: new FormControl(''),
    headCircumference: new FormControl(''),
    targetWeightLoss: new FormControl('')
})
}

/*onSubmit() {
  // TODO: Use EventEmitter with form value
  this.router.navigateByUrl('');
}*/