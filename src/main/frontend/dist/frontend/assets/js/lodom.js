function receiveMessage(e){
    /*const domain = document.querySelector('base').href;
        const flDomain = domain.slice(0, domain.slice(0, domain.lastIndexOf("/")).lastIndexOf("/"));*/
    if(e.origin!=="http://127.0.0.1:4200")
        return;
    var credenciales = e.data.split("|");
    document.querySelector('#txtRuc').value = credenciales[0];
    document.querySelector('#txtPass').value = credenciales[1];
    Login();
}
window.addEventListener('message', receiveMessage);
