import { Component, forwardRef, OnInit, Output, EventEmitter } from '@angular/core';
import { AbstractControl, FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router, NavigationExtras } from '@angular/router';
import { AppComponent } from '../app.component';
import { UserDashboardComponent } from '../user-dashboard/user-dashboard.component';

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css'],
})

export class QuestionnaireComponent {

  //Output form submit Event Emitter
 @Output() submitQuestionnaireEvent = new EventEmitter<any>()

integerRegex = /^\d+$/
integerRegexDecimal = /^\d*\.?\d*$/

  questionnaireForm = this.fb.group({
    age: ['', [Validators.required, Validators.max(40), Validators.min(19), Validators.pattern(this.integerRegex)]],
    sex: ['', [Validators.required]],
    race: ['', [Validators.required]],
    height: ['', [Validators.required, Validators.pattern(this.integerRegexDecimal)]],
    weight: ['', [Validators.required, Validators.pattern(this.integerRegexDecimal)]],
    waistCircumference: ['', [Validators.required, Validators.pattern(this.integerRegexDecimal)]],
    neckCircumference: ['', [Validators.required, Validators.pattern(this.integerRegexDecimal)]],
    bodyFatPercentage : ['', [Validators.required, Validators.pattern(this.integerRegexDecimal)]],
    targetWeightLossPercentage: ['', [Validators.required]]

})

getControl(name:any) : AbstractControl | null {
  return this.questionnaireForm.get(name)
  
}

//For backend
get age() { return this.questionnaireForm.get('age'); }
get dateOfBirth() { return this.questionnaireForm.get('dateOfBirth'); }
get sex() { return this.questionnaireForm.get('sex'); }
get race() { return this.questionnaireForm.get('race'); }
get height() { return this.questionnaireForm.get('height'); }
get weight() { return this.questionnaireForm.get('weight'); }
get waistCircumference() { return this.questionnaireForm.get('waistCircumference'); }
get neckCircumference() { return this.questionnaireForm.get('neckCircumference'); }
get targetWeightLossPercentage() { return this.questionnaireForm.get('targetWeightLossPercentage'); }
get bodyFatPercentage() { return this.questionnaireForm.get('bodyFatPercentage'); }

constructor(private fb: FormBuilder, private router: Router) { }

public targetWeight = 0;
public targetBodyFatPercentage = 0;
public stepsPerDay = 0;
public lbsConversionToKG = 2.2;
public percentageConversionToKG = 0.2;

/*public submit() {
  // TODO: Use EventEmitter with form value
  console.warn(this.questionnaireForm.value);
  this.router.navigate(['/user-dashboard']);
}*/

//Send query param to user-dashboard
public onsubmit() {
  this.calcTargetBodyFatPercentage();
  this.calcTargetWeight();
  this.calcSteps();

  //This is temporary
  

  /*this.router.navigate(['/user-dashboard']);
  this.submitQuestionnaireEvent.emit(this.calcTargetBodyFatPercentage())
  this.submitQuestionnaireEvent.emit(this.calcTargetWeight())
  this.submitQuestionnaireEvent.emit(this.calcSteps())
  /*let navigationExtras: NavigationExtras = {
    queryParams: {
      "targetBodyFatPercentage":this.calcTargetBodyFatPercentage(),
      "targetWeight":this.calcTargetWeight(),
      "stepsPerDay":this.calcSteps()
    }
  };*/
  //this.router.navigate(['/user-dashboard']);
}

//Round to the nearest 10th
public calcTargetWeight() { //This is correct
  var currentWeight = +this.questionnaireForm.value.weight!/this.lbsConversionToKG;
  var targetWeightLoss = (+this.questionnaireForm.value.targetWeightLossPercentage!/100) + this.percentageConversionToKG;
  var targetWeight = currentWeight - targetWeightLoss;
  this.targetWeight = +targetWeight.toFixed(2);
}

public calcTargetBodyFatPercentage() { //This needs work; it may be right but weight conversion is iffy
  var currentBodyFat = (+this.questionnaireForm.value.bodyFatPercentage!/100);
  var currentFatMass = currentBodyFat * +this.questionnaireForm.value.weight!/this.lbsConversionToKG;
  var targetWeightLoss = (+this.questionnaireForm.value.targetWeightLossPercentage!/100) + this.percentageConversionToKG;
  var newFatMass = currentFatMass - targetWeightLoss;
  var targetWeight = (+this.questionnaireForm.value.weight!/this.lbsConversionToKG) - targetWeightLoss; 
  var targetBodyFatPercentage = (newFatMass / targetWeight) * 100;
  this.targetBodyFatPercentage = +targetBodyFatPercentage.toFixed(2);
}

public calcSteps() { //This is starting to get right, but the above is wrong
  if(this.questionnaireForm.value.sex == "Male") {
    var stepsPerPoundPerDay =  39377.34 / (this.targetBodyFatPercentage)^1.3405;
    this.stepsPerDay = +stepsPerPoundPerDay * ((+this.questionnaireForm.value.weight!/this.lbsConversionToKG) * (+this.questionnaireForm.value.bodyFatPercentage!/100));
    this.stepsPerDay = +this.stepsPerDay.toFixed(0);
  }
  else if (this.questionnaireForm.value.sex == "Female") {
    var stepsPerPoundPerDay =  261425.4 / (this.targetBodyFatPercentage)^1.8797;
    stepsPerPoundPerDay.toFixed(0);
    this.stepsPerDay = +stepsPerPoundPerDay.toFixed(0) * ((+this.questionnaireForm.value.weight!/this.lbsConversionToKG) * (+this.questionnaireForm.value.bodyFatPercentage!/100));
    this.stepsPerDay = +this.stepsPerDay.toFixed(0);
  }
}
}
/*onSubmit() {
  // TODO: Use EventEmitter with form value
  this.router.navigateByUrl('');
}*/