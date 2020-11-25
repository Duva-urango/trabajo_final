import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdministrarPrendas {
    private Scanner scanner = new Scanner(System.in);
    private String nombre;
    private String color;
    private String descripcion;
    private long id;
    private double precio;

    public void agregarPrenda() {

        System.out.println("Ingrese el id de la prenda");
        id = scanner.nextLong();
        scanner.nextLine();
        if (verificarId(id)){
            System.out.println("Este id ya existe");
        }else {
            System.out.println("Escriba el nombre de la prenda que desea agregar");
            nombre = scanner.nextLine();
            System.out.println("Escriba el color de la prenda");
            color = scanner.nextLine();
            System.out.println("Escriba el precio de la prenda");
            precio = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Escriba la descripción de la prenda");
            descripcion = scanner.nextLine();

            Prenda prenda = new Prenda(id, nombre, color, precio, descripcion);

            try (BufferedWriter bufferedWriter =
                         Files.newBufferedWriter(Paths.get("src/prenda.txt"),
                                 StandardOpenOption.APPEND,
                                 StandardOpenOption.CREATE)) {
                bufferedWriter.append(prenda.convertirARegistroDeArchivo());
            } catch (IOException ex) {
                System.out.println("Hay un error al abrir este archivo");
                ex.printStackTrace();
            }
        }
    }

    public Prenda eliminarPrenda() {
        System.out.println("Escriba id de la prenda que desea eliminar");
        id = scanner.nextLong();

        String lineaPrendaEliminada = "";

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("src/prenda.txt"));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("src/prenda_tmp.txt"),
                     StandardOpenOption.CREATE_NEW)) {

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                long idLinea = Long.parseLong(linea.split(Prenda.SEPARADOR)[0]);

                if (idLinea != id) {
                    bufferedWriter.append(linea + '\n');
                } else {
                    lineaPrendaEliminada = linea;
                }
            }

            Files.move(Paths.get("src/prenda_tmp.txt"), Paths.get("src/prenda.txt"),
                    StandardCopyOption.REPLACE_EXISTING);

        }catch (IOException ex) {
            System.out.println("Hay un error con este archivo");
            ex.printStackTrace();
        }

        if(!lineaPrendaEliminada.equals("")) {
            Prenda prendaEliminada = new Prenda(lineaPrendaEliminada);
            System.out.println("La prenda " + prendaEliminada + " se elimino");
            return prendaEliminada;
        }
        else {
            System.out.println("La prenda con este id no existe");
            return null;
        }

    }

    public void  ActualizarPrenda(){
        System.out.println("Escriba id de la prenda que desea acutalizar");
        id = scanner.nextLong();
        scanner.nextLine();
        int opcion = 0;
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("src/prenda.txt"))){
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                Prenda prenda = new Prenda(linea);
                long idLinea = Long.parseLong(linea.split(Prenda.SEPARADOR)[0]);
                if(id == idLinea) {
                    do {
                        System.out.println("¿ Que accion desea realizar ?");
                        System.out.println("1. Actualizar nombre");
                        System.out.println("2. Actualizar color");
                        System.out.println("3. Actualizar precio");
                        System.out.println("4. Actualizar descripción");
                        System.out.println("5. Salir");
                        try {
                            opcion = scanner.nextInt();
                        } catch (InputMismatchException ex) {
                            opcion = -1;
                        }
                        scanner.nextLine();

                        switch (opcion) {
                            case 1:
                                System.out.println("Ingrese el nuevo nombre");
                                prenda.setNombre(scanner.nextLine());
                                break;
                            case 2:
                                System.out.println("Ingrese el nuevo color");
                                prenda.setColor(scanner.nextLine());
                                break;
                            case 3:
                                System.out.println("Ingrese el nuevo precio");
                                prenda.setPrecio(scanner.nextDouble());
                                scanner.nextLine();
                                break;
                            case 4:
                                System.out.println("Ingrese la nueva descripcion");
                                prenda.setDescripcion(scanner.nextLine());
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Esta opcion no es válida, intente nuevamente");
                        }
                    }while (opcion != 5);

                    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("src/prenda_tmp.txt"), StandardOpenOption.CREATE_NEW)) {
                        bufferedWriter.append(prenda.convertirARegistroDeArchivo());

                        Files.move(Paths.get("src/prenda_tmp.txt"), Paths.get("src/prenda.txt"), StandardCopyOption.REPLACE_EXISTING);

                    }catch (IOException ex) {
                        System.out.println("Hay un error con este archivo");
                        ex.printStackTrace();
                    }
                }
            }
        }catch (IOException ex){
            System.out.println("No existe ningun registro con este ID");
        }
    }

    public void mostrarPrenda(){

        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("src/prenda.txt"))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null){
                System.out.println(Prenda.convertirRegistroEnStringAPrendaComoString(linea));
            }
        }catch (IOException ex){
            System.out.println("Hay un error con este archivo");
            ex.printStackTrace();
        }
    }

    public Boolean verificarId (long id){
        Boolean existe = false;

        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("src/prenda.txt"))){
            String linea;
            while ((linea = bufferedReader.readLine()) != null){
                long idLinea = Long.parseLong(linea.split(Prenda.SEPARADOR)[0]);
                if (id == idLinea){
                    existe = true;
                    break;
                }
            }
        }catch (IOException ex){
            existe = false;
        }
        return existe;
    }
}