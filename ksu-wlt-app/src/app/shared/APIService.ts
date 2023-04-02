import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class APIService {

  constructor(private http: HttpClient) { }

  // API root URL
  // Temporary localhost api url for testing
  rootURL = 'http://localhost:8080';

  // HTTP Headers
  // Security will be updated for proper user check after testing.
  standardHeaders = new HttpHeaders({'Authorization': `Basic ` + btoa('user:password')});

  // Get list of all persons and their data
  public getPersons(){
    return this.http.get<any>(this.rootURL + '/persons', {headers: this.standardHeaders});
  }

  // Get the data of a specific person using their ID number
  public getPersonByID(personID){
    return this.http.get<any>(this.rootURL + '/persons/' + personID, {headers: this.standardHeaders});
  }

  // Update person data based on personID
  public updatePerson(personID, personDetails){

   }
}
