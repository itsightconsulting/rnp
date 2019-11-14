const oauth2SunatBaseURL = 'https://api-seguridad-desa.sunat.gob.pe:444/v1/clientessol';
const oauth2SunatClientId = '1081141b-2fea-4f62-9234-332f36f5d8a5';
const rnpServerPostSunatLogin = 'https://eap.osce.gob.pe/auth-sunatsol/externaluserauth&state=st';
export const environment = {
    production: true,
    // baseUrl: 'http://52.186.67.112:8090/segrnp-svc/api/v1'
    baseUrl: 'https://eap.osce.gob.pe/segrnp-svc/api/v1',
    oauth2SunatClientId: '1081141b-2fea-4f62-9234-332f36f5d8a5',
    baseUrlSunatSso: `${oauth2SunatBaseURL}/${oauth2SunatClientId}/oauth2/login?originalUrl=${rnpServerPostSunatLogin}`
};
