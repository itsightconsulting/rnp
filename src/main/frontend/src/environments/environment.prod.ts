const oauth2SunatBaseURL = 'https://api-seguridad-test.sunat.gob.pe:444/v1/clientessol';
const oauth2SunatClientId = 'a30ef7d7-cc25-4737-bf8a-167c5e387235';
const rnpServerPostSunatLogin = 'https://eap.osce.gob.pe/auth-sunatsol/externaluserauth&state=st';
//const rnpServerPostSunatLogin = 'https://200.123.25.107/auth-sunatsol/externaluserauth';
export const environment = {
    production: true,
    //baseUrl: 'https://200.123.25.107/segrnp-svc/api/v1',
    baseUrl: 'https://eap.osce.gob.pe/segrnp-svc/api/v1',
    oauth2SunatClientId: oauth2SunatClientId,
    baseUrlSunatSso: `${oauth2SunatBaseURL}/${oauth2SunatClientId}/oauth2/authen?client_id=${oauth2SunatClientId}&response_type=code&state=st&redirect_uri=${rnpServerPostSunatLogin}`
};
