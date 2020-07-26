package sol.lab1;

import java.util.Scanner;

public class SolLab2Prac {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DeviceManager deviceManager = new DeviceManager();
        
        System.out.println("Bienvenido al sistema de registro de equipos");

        while (true) {
            System.out.println("\nIndique la acción que desea realizar");
            System.out.println("1. Registrar equipos");
            System.out.println("2. Ver equipos");
            System.out.println("3. Buscar router");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:
                    boolean exitWhile = false;
                    do {
                        System.out.println("¿Qué equipo desea registrar?");
                        System.out.println("1. Modem");
                        System.out.println("2. Access Point");
                        System.out.println("3. Switch");
                        System.out.println("4. Router");
                        System.out.println("5. Regresar");
                        System.out.print("Opción: ");
                        option = Integer.parseInt(sc.nextLine());

                        switch (option) {
                            case 1:
                                Modem modem = deviceManager.fetchModemFromConsole();
                                deviceManager.addModem(modem);
                                break;
                            case 2:
                                deviceManager.addAccessPoint(deviceManager.fetchApFromConsole());
                                break;
                            case 3:
                                deviceManager.addSwitch(deviceManager.fetchSwitchFromConsole());
                                break;
                            case 4:
                                deviceManager.addRouter(deviceManager.fetchRouterFromConsole());
                                break;
                            case 5:
                                exitWhile = true;
                                break;
                            default:
                                System.out.println("Debe ingresar una opción válida!\n");

                        }
                    } while(!exitWhile);
                    break;
                case 2:
                    deviceManager.showAllDevices();
                    break;
                case 3:
                    deviceManager.searchRouters();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Debe ingresar una opción válida!\n");
            }
        }

    }

}
