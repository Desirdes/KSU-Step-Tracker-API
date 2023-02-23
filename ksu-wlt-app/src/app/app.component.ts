import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient } from '@angular/common/http';
 
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ksu-wlt-app';
  selectedFile = null;

  constructor(private http: HttpClient) {

  }

  onFileSelected(event: any){
    this.selectedFile = event.target.files[0];
  }

  onUpload(){
    // this.http.
  }

}
