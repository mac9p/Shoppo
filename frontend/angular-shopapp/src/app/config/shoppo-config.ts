export default {

  //octa configuration
  oidc:{
    clientId: '0oa57nnhqaZEfpQoy5d7',
    issuer: 'https://dev-53002285.okta.com/oauth2/default',
    redirectUrl: 'http://localhost:4200/login/callback',
    scopes: ['openid','profile','email'],
    pkce: true
  }
}
