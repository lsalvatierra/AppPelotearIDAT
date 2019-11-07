package pe.edu.idat.apppelotearidat.Modelo;

public class Reserva {
    private String NombreSede;
    private String DescLosa;
    private String UrlImgLosa;
    private String HoraIni;
    private String HoraFin;
    private String Fecha;

    public Reserva(String nombreSede, String descLosa, String urlImgLosa, String horaIni, String horaFin, String fecha) {
        NombreSede = nombreSede;
        DescLosa = descLosa;
        UrlImgLosa = urlImgLosa;
        HoraIni = horaIni;
        HoraFin = horaFin;
        Fecha = fecha;
    }

    public String getDescLosa() {
        return DescLosa;
    }

    public void setDescLosa(String descLosa) {
        DescLosa = descLosa;
    }
    public String getNombreSede() {
        return NombreSede;
    }

    public void setNombreSede(String nombreSede) {
        NombreSede = nombreSede;
    }

    public String getUrlImgLosa() {
        return UrlImgLosa;
    }

    public void setUrlImgLosa(String urlImgLosa) {
        UrlImgLosa = urlImgLosa;
    }

    public String getHoraIni() {
        return HoraIni;
    }

    public void setHoraIni(String horaIni) {
        HoraIni = horaIni;
    }

    public String getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(String horaFin) {
        HoraFin = horaFin;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
