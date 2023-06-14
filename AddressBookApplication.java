import java.io.*;
import java.util.*;

public class AddressBookApplication {
    private static AddressBook addressBook = AddressBook.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayMenu();
    }

    public static void displayMenu() {
        boolean exit = false;

        while (!exit) {
            int count = 1 + 0;
            System.out.println();
            System.out.println("==== LIBRETA DE DIRECCIONES ====");
            System.out.println("a) Cargar Contactos y direcciones desde un archivo");
            System.out.println("b) Guardar Contactos y direcciones en un archivo");
            System.out.println("c) Agregar un Contacto y direccion ");
            System.out.println("d) Eliminar un Contacto y direccion");
            System.out.println("e) Buscar contacto por apellido");
            System.out.println("f) Mostrar todos los Contactos y direcciones");
            System.out.println("g) Salir");
            System.out.print("Seleccione una opción: ");

            String option = scanner.nextLine().toLowerCase();

            switch (option) {
                case "a":
                    loadEntriesFromFile();
                    break;
                case "b":
                    saveEntriesToFile();
                    break;
                case "c":
                    addEntry();
                    break;
                case "d":
                    deleteEntry();
                    break;
                case "e":
                    searchByLastName();
                    break;
                case "f":
                    displayAddresses();
                    break;
                case "g":
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }

            System.out.println();
        }
    }
    public static void loadEntriesFromFile() {
        System.out.print("Ingrese la ruta del archivo: ");
        String filePath = scanner.nextLine();

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] fields = line.split(";");

                if (fields.length == 8) {
                    String firstName = fields[0];
                    String lastName = fields[1];
                    String street = fields[2];
                    String city = fields[3];
                    String state = fields[4];
                    String postalCode = fields[5];
                    String email = fields[6];
                    String phone = fields[7];

                    AddressEntry entry = new AddressEntry(firstName, lastName, street, city, state, postalCode, email, phone);
                    addressBook.addEntry(entry);
                }
            }

            System.out.println("Carga de entradas desde archivo completada.");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe o no se puede acceder a él.");
        }


        System.out.println("Cargando entradas desde un archivo...");
    }
    public static void addEntry() {
        System.out.println("==== AGREGAR UNA DIRECCION ====");
        System.out.print("Nombre: ");
        String firstName = scanner.nextLine();
        System.out.print("Apellido: ");
        String lastName = scanner.nextLine();
        System.out.print("Calle: ");
        String street = scanner.nextLine();
        System.out.print("Ciudad: ");
        String city = scanner.nextLine();
        System.out.print("Estado: ");
        String state = scanner.nextLine();
        System.out.print("Código postal: ");
        String postalCode = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();

        AddressEntry entry = new AddressEntry(firstName, lastName, street, city, state, postalCode, email, phone);
        addressBook.addEntry(entry);

        System.out.println("Entrada agregada exitosamente.");
    }

    public static void deleteEntry() {
        System.out.println("==== ELIMINAR UNA ENTRADA ====");
        System.out.print("Ingrese el apellido para buscar la entrada: ");
        String lastName = scanner.nextLine();

        List<AddressEntry> entries = addressBook.searchByLastName(lastName);

        if (entries.isEmpty()) {
            System.out.println("No se encontraron entradas con ese apellido.");
        } else {
            System.out.println("Se encontraron las siguientes entradas:");

            for (int i = 0; i < entries.size(); i++) {
                System.out.println((i + 1) + ") " + entries.get(i));
            }

            System.out.print("Seleccione el número de la entrada que desea eliminar: ");
            int selection = Integer.parseInt(scanner.nextLine());
            if (selection >= 1 && selection <= entries.size()) {
                AddressEntry entryToDelete = entries.get(selection - 1);
                addressBook.deleteEntry(entryToDelete);
                System.out.println("Entrada eliminada exitosamente.");
            } else {
                System.out.println("Selección inválida. No se eliminó ninguna entrada.");
            }
        }
    }

    public static void searchByLastName() {
        System.out.println("==== BUSCAR ENTRADAS POR APELLIDO ====");
        System.out.print("Ingrese el inicio del apellido: ");
        String lastNamePrefix = scanner.nextLine();

        List<AddressEntry> entries = addressBook.searchByLastName(lastNamePrefix);

        if (entries.isEmpty()) {
            System.out.println("No se encontraron entradas con ese inicio de apellido.");
        } else {
            System.out.println("Se encontraron las siguientes entradas:");

            for (AddressEntry entry : entries) {
                System.out.println(entry);
            }
        }
    }

    public static void displayAddresses() {
        System.out.println("==== DIRECCIONES EN ORDEN ALFABÉTICO ====");
        List<AddressEntry> entries = addressBook.getEntriesOrderedByLastName();

        if (entries.isEmpty()) {
            System.out.println("No hay entradas en la libreta de direcciones.");
        } else {
            for (AddressEntry entry : entries) {
                System.out.println(entry);
            }
        }
    }
    public static void saveEntriesToFile() {
        System.out.print("Ingrese la ruta del archivo de destino: ");
        String filePath = scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            List<AddressEntry> entries = addressBook.getEntriesOrderedByLastName();

            for (AddressEntry entry : entries) {
                writer.println(entry.getFirstName() + ";" +
                        entry.getLastName() + ";" +
                        entry.getStreet() + ";" +
                        entry.getCity() + ";" +
                        entry.getState() + ";" +
                        entry.getPostalCode() + ";" +
                        entry.getEmail() + ";" +
                        entry.getPhone());
            }

            System.out.println("Guardado de entradas en archivo completado.");
        } catch (IOException e) {
            System.out.println("Error al guardar las entradas en el archivo.");
        }
    }
}



