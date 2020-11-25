import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private AdministrarPrendas administrarPrendas = new AdministrarPrendas();

    public void abrirMenu() {
        int opcion = 0;
        do {
            System.out.println("Bienvenido/al control de prendas");
            System.out.println("Por favor seleccione la accion que desea realizar: ");
            System.out.println("1. Agregar prenda");
            System.out.println("2. Eliminar prenda");
            System.out.println("3. Actualizar información de una prenda");
            System.out.println("4. Mostrar todas las prendas");
            System.out.println("5. Salir");
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException ex) {
                ex.printStackTrace();
                opcion = -1;
            }
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    administrarPrendas.agregarPrenda();
                    break;
                case 2:
                    administrarPrendas.eliminarPrenda();
                    break;
                case 3:
                    administrarPrendas.ActualizarPrenda();
                    break;
                case 4:
                    administrarPrendas.mostrarPrenda();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Esta opcion no es válida");
            }
        } while (opcion != 5);
    }
}

