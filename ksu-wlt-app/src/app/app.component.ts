import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient } from '@angular/common/http';
import { APIService } from './shared/APIService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'ksu-wlt-app';
  selectedFile = null;

  constructor(
    private http: HttpClient,
    private apiService: APIService
  ) {

  }

  // In case we need to test things on application init
  ngOnInit(){
    // Testing API calls
    this.apiService.getPersonByID(1).subscribe((data: any[]) => {
      console.log(data);
    });
  }
  public targetWeight = 0;
  public targetBodyFatPercentage = 0;
  public stepsPerDay = 0;
  
  onFileSelected(event: any){
    this.selectedFile = event.target.files[0];
  }

  onUpload(){
    // this.http.
  }

}
