package pe.edu.idat.apppelotearidat.Modelo;

public class Noticia {

    private String Titulo;
    private String UrlImagen;

    public Noticia(String titulo, String urlImagen) {
        Titulo = titulo;
        UrlImagen = urlImagen;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getUrlImagen() {
        return UrlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        UrlImagen = urlImagen;
    }
}
