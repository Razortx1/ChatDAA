package Modelo;

public class Curso {
    private Integer id_curso;
    private String nombre_curso;
    private String id_docentes;
    private String id_alumnos;

    public Curso() {
    }

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    public String getId_docentes() {
        return id_docentes;
    }

    public void setId_docentes(String id_docentes) {
        this.id_docentes = id_docentes;
    }

    public String getId_alumnos() {
        return id_alumnos;
    }

    public void setId_alumnos(String id_alumnos) {
        this.id_alumnos = id_alumnos;
    }
}
