package pe.gob.osce.rnp.seg.model.jpa.dto;

public class Respuesta<T> {

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
    private T d;

    public Respuesta(int responseCode, int intFlag) {
        this.responseCode = responseCode;
        this.flag = intFlag > 0;
        this.d = null;
    }

    public Respuesta(int responseCode, int intFlag, T d) {
        this.responseCode = responseCode;
        this.flag = intFlag > 0;
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

    public T getD() {
        return d;
    }

    public void setD(T d) {
        this.d = d;
    }
}

