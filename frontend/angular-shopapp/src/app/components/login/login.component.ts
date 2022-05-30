import {Component, Inject, OnInit} from '@angular/core';

import shoppoConfig from '../../config/shoppo-config'
import OktaSignIn from "@okta/okta-signin-widget";
// @ts-ignore
import {OktaAuth} from "@okta/okta-auth-js"
import {OKTA_AUTH} from "@okta/okta-angular";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  oktaSignIn: any;

  constructor(@Inject(OKTA_AUTH) private oktaAuth: OktaAuth) {
    this.oktaSignIn = new OktaSignIn({
        logo: 'assets/images/logo.png',
        baseUrl: shoppoConfig.oidc.issuer.split('/oauth2')[0],
        clientId: shoppoConfig.oidc.clientId,
        redirectUri: shoppoConfig.oidc.redirectUrl,
        authParams: {
          pkce: shoppoConfig.oidc.pkce,
          issuer: shoppoConfig.oidc.issuer,
          scopes: shoppoConfig.oidc.scopes
        }
      }
    );
  }

  ngOnInit(): void {
    this.oktaSignIn.remove();

    this.oktaSignIn.renderEl({
      el: '#okta-sign-in-widget'
    },(response: { status: string; }) => {
      if (response.status == 'SUCCESS'){
        this.oktaAuth.signInWithRedirect();
      }
    },
        (error: any) => {
      throw error;
        }
    );
  }
  ngOnDestroy(): void{
    this.oktaSignIn.remove();
  }

}
