function receiveMessage(e){
    if(e.origin!=="http://127.0.0.1:4200")
        return;
    var credenciales = e.data.split("|");
    document.querySelector('#txtRuc').value = credenciales[0];
    document.querySelector('#txtPass').value = credenciales[1];
    Login();
}
window.addEventListener('message', receiveMessage);
