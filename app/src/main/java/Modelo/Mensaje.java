package Modelo;

public class Mensaje {
    private Integer Id_mensaje;
    private String id_Remitente;
    private String id_emisor;
    private String Mensaje;

    public Mensaje() {
    }

    public Integer getId_mensaje() {
        return Id_mensaje;
    }

    public void setId_mensaje(Integer id_mensaje) {
        Id_mensaje = id_mensaje;
    }

    public String getId_Remitente() {
        return id_Remitente;
    }

    public void setId_Remitente(String id_Remitente) {
        this.id_Remitente = id_Remitente;
    }

    public String getId_emisor() {
        return id_emisor;
    }

    public void setId_emisor(String id_emisor) {
        this.id_emisor = id_emisor;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }
}
