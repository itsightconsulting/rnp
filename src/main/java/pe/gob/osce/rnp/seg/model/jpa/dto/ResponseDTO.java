package pe.gob.osce.rnp.seg.model.jpa.dto;

public class ResponseDTO {

    /// <summary>
    /// Codigo de la petición
    /// </summary>
    private int responseCode;

    /// <summary>
    /// Booleano sobre el éxito de la petición de datos solicitada
    /// </summary>
    private boolean flag;

    /// <summary>
    /// Objeto que contiene toda la data
    /// </summary>
    private Object d;

    public ResponseDTO(int responseCode, boolean flag,  Object d) {
        this.responseCode = responseCode;
        this.flag = flag;
        this.d = d;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Object getD() {
        return d;
    }

    public void setD(Object d) {
        this.d = d;
    }
}

