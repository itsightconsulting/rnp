package pe.gob.osce.rnp.seg.model.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class Base01 {

    @Id
    @Column(name = "NO_EXP")
    private String noExp; 

    @Column(name = "TIPO")
    private String tipo; 

    @Column(name = "RAZSOCIAL")
    private String razonSocial; 

    @Column(name = "PN_PJ")
    private String pnPj; 

    @Column(name = "LE")
    private String le; 

    @Column(name = "LT_RUC")
    private String ltRuc; 

    @Column(name = "F_CONST")
    @Temporal(TemporalType.DATE)
    private Date fConst;

    @Column(name = "CAP_CONS")
    private float capCons; 

    @Column(name = "DIRECCION")
    private String direccion; 

    @Column(name = "DISTRITO")
    private String distrito; 

    @Column(name = "PROVIN")
    private String provin; 

    @Column(name = "DEPA")
    private String depa; 

    @Column(name = "TELEFONO")
    private String telefono; 

    @Column(name = "POSTAL")
    private String postal; 

    @Column(name = "MERCA")
    private String merca; 

    @Column(name = "\"AS\"")
    private String as;

    @Column(name = "FOL")
    private String fol; 

    @Column(name = "TOMO")
    private String tomo; 

    @Column(name = "FICHA")
    private String ficha; 

    @Column(name = "NO_REPRE")
    private String noRepre; 

    @Column(name = "DIR_REPRE")
    private String dirRepre; 

    @Column(name = "DIS_REPRE")
    private String disRepre; 

    @Column(name = "TEL_REPRE")
    private String telRepre; 

    @Column(name = "POST_REPRE")
    private float postRepre; 

    @Column(name = "CAPITAL")
    private float capital; 

    @Column(name = "CAP_CONT")
    private float capCont; 

    @Column(name = "F_APROB")
    @Temporal(TemporalType.DATE)
    private Date fAprob; 

    @Column(name = "F_VALIDEZ")
    @Temporal(TemporalType.DATE)
    private Date fValidez; 

    @Column(name = "RESTICCION")
    private String resticcion; 

    @Column(name = "ACC_COM")
    private String accCom; 

    @Column(name = "RESOL")
    private String resol; 

    @Column(name = "ID_FUENTE_RESOL")
    private int idFuenteResol; 

    @Column(name = "N_ING")
    private float nIng; 

    @Column(name = "CLAVE")
    private String clave; 

    @Column(name = "SANCION")
    private String sancion; 

    @Column(name = "SIGLA")
    private String sigla; 

    @Column(name = "NOTARIO")
    private String notario; 

    @Column(name = "NOT_DE")
    private String notDe; 

    @Column(name = "NUME")
    private float nume; 

    @Column(name = "F_PRESENTA")
    @Temporal(TemporalType.DATE)
    private Date fPresenta; 

    @Column(name = "COSTO")
    private float costo; 

    @Column(name = "N_RECIBO")
    private String nRecibo; 

    @Column(name = "PAIS")
    private String pais; 

    @Column(name = "E_1")
    private String e1; 

    @Column(name = "E_2")
    private String e2; 

    @Column(name = "E_3")
    private String e3; 

    @Column(name = "E_4")
    private String e4; 

    @Column(name = "E_5")
    private String e5; 

    @Column(name = "E_6")
    private String e6; 

    @Column(name = "E_7")
    private String e7; 

    @Column(name = "ID_TIPO_SOCIEDAD")
    private String idTipoSociedad; 

    @Column(name = "FAX")
    private String fax; 

    @Column(name = "PARTIDA")
    private String partida; 

    @Column(name = "RESTRI02")
    private String restrio02; 

    @Column(name = "RESTRI03")
    private String restrio03; 

    @Column(name = "COMENTA")
    private String comenta; 

    @Column(name = "RUC8")
    private String ruc8; 

    @Column(name = "DIS_CODDIST")
    private String disCoddist; 

    @Column(name = "DIS_NOMDIST")
    private String disNomdist; 

    @Column(name = "ID_DOCIDENT")
    private String idDocient; 

    @Column(name = "NRO_DOCIDENT")
    private String nroDocident; 

    @Column(name = "EMAIL")
    private String email; 

    @Column(name = "CIP")
    private int cip; 

    @Column(name = "CAP")
    private int cap; 

    @Column(name = "NU_ANO_TRAMITE")
    private String nuAnoTramite; 

    @Column(name = "ID_REGION")
    private String idRegion; 

    @Column(name = "NU_TRAMITE")
    private int nuTramite; 

    @Column(name = "ES_EXTERNO")
    private String esExterno; 

    @Column(name = "ID_TIPO_TRAMITE")
    private int idTipoTramite; 

    @Column(name = "F_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fRegistro; 

    @Column(name = "UID_REGISTRO")
    private String uidRegistro; 

    @Column(name = "IND_DIR_SUNAT")
    private String indDirSunat; 

    @Column(name = "NU_MONTO_OBRAS")
    private float nuMontoObras; 

    @Column(name = "SOCIEDAD")
    private String sociedad; 

    @Column(name = "F_ADEC_LEY")
    @Temporal(TemporalType.DATE)
    private Date fAdecLey; 

    @Column(name = "FH_CAMBIO_CAPITAL")
    @Temporal(TemporalType.DATE)
    private Date fhCambioCapital; 

    @Column(name = "NU_ACCIONES_EMPRESA")
    private float nuAccionesEmpresa; 

    @Column(name = "ID_ESTADO_RNC")
    private int idEstadoRnc; 

    @Column(name = "DE_OBSERVACIONES")
    private String deObservaciones; 

    @Column(name = "UID_VERIFICACION")
    private String uidVerificacion; 

    @Column(name = "NO_NOMBRE")
    private String noNonbre; 

    @Column(name = "AP_PATERNO")
    private String apPaterno; 

    @Column(name = "AP_MATERNO")
    private String apMaterno; 

    @Column(name = "CODPAIS")
    private int codPais; 

    @Column(name = "ID_TIPO_EMPRESA")
    private int idTipoEmpresa; 

    @Column(name = "VENTA_ANUAL")
    private float ventaAnual; 

    @Column(name = "NU_TRABAJADORES")
    private int nuTrabajadores; 

    @Column(name = "CODMONEDA")
    private int codMoneda; 

    @Column(name = "DE_RESOL")
    private String deResol; 

    @Column(name = "IND_NO_DOMIC")
    private String indNoDomic; 

    @Column(name = "NU_FOLIOS")
    private int nuFolios; 

    @Column(name = "F_VALIDEZ2")
    @Temporal(TemporalType.DATE)
    private Date fValidez2; 

    @Column(name = "CODMONEDA_CAPCONS")
    private int codMonedaCapCons; 

    @Column(name = "AMNISTIA1")
    private String anmistia1; 

    @Column(name = "AMNISTIA2")
    private String anmistia2; 

    @Column(name = "FH_FOLEO")
    @Temporal(TemporalType.DATE)
    private Date fhFoleo; 

    @Column(name = "UID_FOLEO")
    private String uidFoleo; 

    @Column(name = "ID_ZONA_REGISTRAL")
    private int idZonaRegistral; 

    @Column(name = "F_APROB_MANUAL")
    @Temporal(TemporalType.DATE)
    private Date fAprobManual; 

    @Column(name = "anoinicioActiv")
    private String anoinicioActiv; 

    @Column(name = "monto")
    private float monto; 

    @Column(name = "montosoles")
    private float montoSoles; 

    @Column(name = "otrotipomoneda")
    private String otroTipoMoneda; 

    @Column(name = "clasificacion")
    private String clasificacion; 

    @Column(name = "ley")
    private int ley; 

    @Column(name = "ntrabajadores")
    private int nTrabajadores; 

    @Column(name = "fescritura")
    @Temporal(TemporalType.DATE)
    private Date fEscritura; 

    @Column(name = "TMONEDA_MSUSCRITO")
    private int tMonedaMsuscrito; 

    @Column(name = "OMONEDA_MSUSCRITO")
    private String oMonedaMsuscrito; 

    @Column(name = "msuscrito")
    private float mSuscrito; 

    @Column(name = "vnominal")
    private float vNominal; 

    @Column(name = "nacionalidad")
    private String nacionalidad; 

    @Column(name = "notaria")
    private String notaria; 

    @Column(name = "eject_total")
    private float ejectTotal; 

    @Column(name = "Nombreact")
    private String NomBreact; 

    @Column(name = "descactividad")
    private String descactividad; 

    @Column(name = "mpagado")
    private float mPagado; 

    @Column(name = "totacciones")
    private float totAcciones; 

    @Column(name = "id_tipo_proveedor")
    private String idTipoProveedor; 

    @Column(name = "SOC_ACC_RESTANTES")
    private float socAccRestantes; 

    @Column(name = "SOC_TOT_ACCIONES")
    private float socTotAcciones; 

    @Column(name = "SOC_PORCENTAJE")
    private float socPorcentaje; 

    @Column(name = "CADENA_RNP")
    private String cadenaRnp; 

    @Column(name = "TEXTO_INFORME")
    private String textoInforme; 

    @Column(name = "TEXTO_RESOLUCION")
    private String textoResolucion; 

    @Column(name = "ID_COLEGIO")
    private int idColegio; 

    @Column(name = "NRO_MATRICULA")
    private String nroMatricula; 

    @Column(name = "F_INCORP_COLEGIO")
    @Temporal(TemporalType.DATE)
    private Date fIncorpColegio; 

    @Column(name = "ID_PROFESION")
    private int idProfesion; 

    @Column(name = "ES_REMYPE")
    private int esRemype; 

    @Column(name = "MONTO_CAPITAL_DEPOSITO")
    private float montoCapitaldeposito; 

    @Column(name = "NU_ANO_TRAMITE_FISC")
    private String nuAnoTramiteFisc; 

    @Column(name = "NU_TRAMITE_FISC")
    private int nuTramiteFisc; 
	
    @JsonBackReference
    @OneToMany(mappedBy = "base01", fetch = FetchType.LAZY)
    private List<Contacto> lstContacto;
    
    public Base01() {
    	
    }
}
