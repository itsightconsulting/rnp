// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
const oauth2SunatBaseURL = 'https://api-seguridad-desa.sunat.gob.pe:444/v1/clientessol';
const oauth2SunatClientId = '1081141b-2fea-4f62-9234-332f36f5d8a5';
const rnpServerPostSunatLogin = 'https://eap.osce.gob.pe/auth-sunatsol/externaluserauth&state=st';

export const environment = {
  production: false,
  //baseUrl: 'http://127.0.0.1:8080/api/v1'
  // baseUrl: 'http://52.186.67.112:8090/segrnp-svc/api/v1',
  baseUrl: 'http://200.123.25.107/segrnp-svc/api/v1',
  baseUrlSunatSso: `${oauth2SunatBaseURL}/${oauth2SunatClientId}/oauth2/login?originalUrl=${rnpServerPostSunatLogin}`
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
