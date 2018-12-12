package pe.gob.osce.rnp.seg.model.jpa.dto;

public class DataResponseDTO {

    /// <summary>
    /// Booleano sobre el éxito de la petición de datos solicitada
    /// </summary>
    private boolean Flag;

    /// <summary>
    /// Message del estado de la petición
    /// </summary>
    private String Message;

    /// <summary>
    /// Codigo de la petición
    /// </summary>
    private int ResponseCode;

    /// <summary>
    /// Objeto que contiene toda la data
    /// </summary>
    private Object Data;

    public DataResponseDTO(){}

    public boolean isFlag() {
        return Flag;
    }

    public void setFlag(boolean flag) {
        Flag = flag;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode) {
        ResponseCode = responseCode;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }
}

