import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class APIService {

  constructor(private http: HttpClient) { }

  // API root URL
  // Temporary localhost api url for testing
  private rootURL = 'http://localhost:8080';

  // Generated after login with format: btoa(username + ":" + password)
  public userBasicAuth = '';

  // User login
  public async loginUser(username, password){
      var data = {
        username: username,
        password: password
      };
      var body = JSON.stringify(data);
      const response = await fetch(this.rootURL + '/access/login', {
        method: 'POST',
        body: body,
        headers: {'Content-Type': 'application/json', 'Authorization': `Basic ` + btoa('user:password')}
      });

      if(response.ok){
        // Get json of return data
        const jsonValue = await response.json();
        return Promise.resolve(jsonValue);
      } else {
        // Get text for error message
        const errorText = await response.text();
        return Promise.reject("Error " + response.status + ": " + errorText);
      }
    }

  // User signup
  public async signupUser(fullName, email, username, password){
    var data = {
      full_name: fullName,
      email: email,
      username: username,
      password: password
    };
    var body = JSON.stringify(data);
    const response = await fetch(this.rootURL + '/access/signup', {
      method: 'POST',
      body: body,
      headers: {'Content-Type': 'application/json', 'Authorization': `Basic ` + btoa('user:password')}
    });

    if(response.ok){
      return Promise.resolve();
    } else {
      // Get text for error message
      const errorText = await response.text();
      return Promise.reject("Error " + response.status + ": " + errorText);
    }
  }

  // Get list of all persons and their data
  //public getPersons(){
  //  return this.http.get<any>(this.rootURL + '/persons', {headers: this.standardHeaders});
  //}

  // Get the data of a specific person using their ID number
  //public getPersonByID(personID){
  //  return this.http.get<any>(this.rootURL + '/persons/' + personID, {headers: this.standardHeaders});
  //}

  // Update person data based on personID
  public updatePerson(personID, personDetails){

   }
}
