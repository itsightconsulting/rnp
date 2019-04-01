import {Injectable} from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class Utilitarios{

    constructor(){

    }

    $urlParam(name){
        const results = new RegExp(`[\?&]${name}=([^&#]*)`).exec(window.location.href);
        if (results==null){
            return null;
        }
        else{
            return decodeURI(results[1]) || 0;
        }
    }
}
