public class Prenda {

    public static final String SEPARADOR = "@@@";
    private String nombre;
    private String color;
    private String descripcion;
    private long id;
    private double precio;

    public Prenda(long id, String nombre, String color, double precio, String descripcion) {
        this.nombre = nombre;
        this.color = color;
        this.descripcion = descripcion;
        this.id = id;
        this.precio = precio;
    }

    public Prenda(String prendaComoString){
        String[] atributos = prendaComoString.split(SEPARADOR);
        this.id = Long.parseLong(atributos[0]);
        this.nombre = atributos[1];
        this.color = atributos[2];
        this.precio = Double.parseDouble(atributos[3]);
        this.descripcion = atributos[4];

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "id=" + id + '\''+
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio;
    }

    public String convertirARegistroDeArchivo(){

        return this.id + SEPARADOR +
                this.nombre + SEPARADOR +
                this.color + SEPARADOR +
                this.precio + SEPARADOR +
                this.descripcion + "\n";


    }
    public static String convertirRegistroEnStringAPrendaComoString(String registro){

        String[] atributos = registro.split(SEPARADOR);

        return   "Id:" + atributos[0] +
                " nombre:" + atributos[1] +
                " color:" + atributos[2] +
                " precio:" + atributos[3] +
                " descripcion:" + atributos[4];

    }
}
