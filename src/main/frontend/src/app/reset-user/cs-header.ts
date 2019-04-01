import {HttpHeaders, HttpParams} from "@angular/common/http";

export class CsHeader{

    private readonly headers: HttpHeaders;
    private readonly params: HttpParams;

    constructor(headers, params){
        this.headers = headers;
        this.params = params;
    }

    public getHeaders(){
        return this.headers;
    }

    public getParams(){
        return this.params;
    }
}
