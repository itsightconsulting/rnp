export class Representante {

    private codExtNoDom: string;
    private razonSocial: string;
    private nomPais: string;
    private correo: string;
    private correoFormateado: string;

    constructor(codExtNoDom, razonSocial, nomPais, correo, correoFormateado){
        this.codExtNoDom = codExtNoDom;
        this.razonSocial = razonSocial;
        this.nomPais = nomPais;
        this.correo = correo;
        this.correoFormateado = correoFormateado;
    }
}
