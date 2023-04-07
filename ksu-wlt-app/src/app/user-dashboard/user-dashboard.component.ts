import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute, ParamMap} from "@angular/router";
import { AppComponent } from '../app.component';
import { APIService } from '../shared/APIService';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit{
  constructor(
    private route: ActivatedRoute,
    private appComponent: AppComponent,
    private apiService: APIService
  ) {}

  ngOnInit(): void {

  }

  public currentPerson = this.appComponent.currentPerson;


  /*public calcData(targetWeight, targetBodyFatPercentage, stepsPerDay): void {
    this.targetWeight = targetWeight;
    this.targetBodyFatPercentage = targetBodyFatPercentage;
    this.stepsPerDay = stepsPerDay;
  }*/
}
