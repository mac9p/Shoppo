import {Component, Inject, OnInit} from '@angular/core';
import {OktaAuth} from "@okta/okta-auth-js";
import {OKTA_AUTH} from "@okta/okta-angular";

@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['./login-status.component.css']
})
export class LoginStatusComponent implements OnInit {

  isAuthenticated: boolean= false;
  userName!: string;

  constructor(@Inject(OKTA_AUTH) private oktaAuth : OktaAuth) {
    this.oktaAuth.authStateManager.subscribe((isAuthenticated: boolean) => this.isAuthenticated = isAuthenticated);
  }

  async ngOnInit(){
    this.isAuthenticated = await this.oktaAuth.isAuthenticated()

    if (this.isAuthenticated) {
      const userClaim = await this.oktaAuth.getUser();
      this.userName = userClaim.name || "";
    }
    console.log("Autentication = " + this.isAuthenticated);
    console.log("Username = " + this.userName);
  }



  userLogout(){
    this.oktaAuth.closeSession();
    this.oktaAuth.tokenManager.clear();
    /*
    this.oktaAuth.signOut()*/
  }
}
