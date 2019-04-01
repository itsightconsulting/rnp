export class RepresentanteDto {

    private readonly codExtNoDom: string;
    private readonly razonSocial: string;
    private readonly nomPais: string;
    private readonly correo: string;
    private readonly correoFormateado: string;

    constructor(codExtNoDom, razonSocial, nomPais, correo, correoFormateado){
        this.codExtNoDom = codExtNoDom;
        this.razonSocial = razonSocial;
        this.nomPais = nomPais;
        this.correo = correo;
        this.correoFormateado = correoFormateado;
    }
}
