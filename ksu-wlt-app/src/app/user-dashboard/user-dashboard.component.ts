import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute, ParamMap} from "@angular/router";

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit{

  public targetWeight = 0;
  public targetBodyFatPercentage = 0;
  public stepsPerDay = 0;

  constructor(private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
    this.targetWeight = params["targetWeight"];
    this.targetBodyFatPercentage = params["targetBodyFatPercentage"];
    this.stepsPerDay = params["stepsPerDay"];
  });
  }

  ngOnInit(): void {
    
  }
  public calcData(targetWeight, targetBodyFatPercentage, stepsPerDay): void {
    this.targetWeight = targetWeight;
    this.targetBodyFatPercentage = targetBodyFatPercentage;
    this.stepsPerDay = stepsPerDay;
  }
}
