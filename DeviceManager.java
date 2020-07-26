/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.lab1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Stuardo
 */
public class DeviceManager {

    private ArrayList<Equipo> listaEquipos = new ArrayList<>();

    private final Scanner sc = new Scanner(System.in);

    public void fetchEquipoFromConsole(Equipo equipo) {

        System.out.print("Fabricante: ");
        String marca = sc.nextLine();
        equipo.setFabricante(marca);

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        equipo.setFabricante(modelo);
    }

    public Modem fetchModemFromConsole() {
        Modem modem = new Modem();

        System.out.println("Ingrese la información del modem");

        this.fetchEquipoFromConsole(modem);

        System.out.print("Tecnología: ");
        String tecnologia = sc.nextLine();
        modem.setTecnologia(tecnologia);

        return modem;
    }

    public AccessPoint fetchApFromConsole() {
        AccessPoint ap = new AccessPoint();

        System.out.println("Ingrese la información del Access Point");

        this.fetchEquipoFromConsole(ap);

        System.out.print("Número de antenas: ");
        String antenas = sc.nextLine();
        ap.setNumeroAntenas(Integer.parseInt(antenas));

        return ap;
    }

    public AccessPoint fetchApFromConsole(String fabricante) {
        AccessPoint ap = new AccessPoint();

        System.out.println("Ingrese la información del Access Point");

        ap.setFabricante(fabricante);

        System.out.print("Número de antenas: ");
        String antenas = sc.nextLine();
        ap.setNumeroAntenas(Integer.parseInt(antenas));

        return ap;
    }

    public Switch fetchSwitchFromConsole() {
        Switch sw = new Switch();

        System.out.println("Ingrese la información del Switch como trama");

        System.out.print("Trama: ");
        String trama = sc.nextLine();
        String[] modeloPuertos = trama.split("_");
        sw.setFabricante(modeloPuertos[0]);
        sw.setModelo(modeloPuertos[1]);
        sw.setNumeroPuertos(Integer.parseInt(modeloPuertos[2]));

        return sw;
    }

    public Router fetchRouterFromConsole() {
        Router router = new Router();

        System.out.println("Ingrese la información del Router");

        this.fetchEquipoFromConsole(router);

        System.out.print("IP de gestión: ");
        String ip = sc.nextLine();
        router.setIp(ip);

        System.out.print("¿Tiene Switch integrado? (S/N)");
        boolean option = sc.nextLine().equalsIgnoreCase("S");
        if (option) {
            Switch sw = this.fetchSwitchFromConsole();
            router.setSw(sw);
        }

        System.out.print("¿Tiene Access Point integrado? (S/N)");
        option = sc.nextLine().equalsIgnoreCase("S");
        if (option) {
            AccessPoint ap = this.fetchApFromConsole(router.getFabricante());
            router.setAp(ap);
        }

        return router;
    }

    public void addModem(Modem modem) {
        listaEquipos.add(modem);
        System.out.println("--Modem agregado--\n");
    }

    public void addAccessPoint(AccessPoint accessPoint) {
        listaEquipos.add(accessPoint);
        System.out.println("--Access Point agregado--\n");
    }

    public void addSwitch(Switch sw) {
        if (sw.getNumeroPuertos() % 2 == 0) {
            listaEquipos.add(sw);
            System.out.println("--Switch agregado--\n");
        }
    }

    public void addRouter(Router router) {
        boolean routerIpFound = false;

        for (Equipo e : listaEquipos) {
            if (e instanceof Router) {
                Router r = (Router) e;
                if (r.getIp().equals(router.getIp())) {
                    routerIpFound = true;
                    break;
                }
            }
        }
        if (!routerIpFound) {
            listaEquipos.add(router);
            System.out.println("--Router agregado--\n");
        }
    }

    public void showAllDevices() {
        System.out.println("\nLista de equipos");

        int cantidadModems = 0;
        int cantidadAP = 0;
        int cantidadSw = 0;
        int cantidadRouters = 0;
        for (Equipo e : listaEquipos) {
            if (e instanceof Modem) {
                cantidadModems++;
            } else if (e instanceof AccessPoint) {
                cantidadAP++;
            } else if (e instanceof Switch) {
                cantidadSw++;
            } else if (e instanceof Router) {
                cantidadRouters++;
            }
        }

        //Lista de Modems
        if (cantidadModems > 0) {
            System.out.println("Modems... Encontrados: " + cantidadModems);
            for (Equipo e : listaEquipos) {
                if (e instanceof Modem) {
                    System.out.print("Fabricante: " + e.getFabricante() + "/ Modelo: " + e.getModelo());

                    Modem m = (Modem) e;
                    System.out.println(" / Tecnología: " + m.getTecnologia());
                }
            }
        } else {
            System.out.println("No hay modems registrados");
        }

        if (cantidadAP > 0) {
            System.out.println("Access Points... Encontrados: " + cantidadAP);
            for (Equipo e : listaEquipos) {
                if (e instanceof AccessPoint) {
                    System.out.print("Fabricante: " + e.getFabricante() + "/ Modelo: " + e.getModelo());

                    AccessPoint ap = (AccessPoint) e;
                    System.out.println(" / Antenas: " + ap.getNumeroAntenas());
                }
            }
        } else {
            System.out.println("No hay APs registrados");
        }

        if (cantidadSw > 0) {
            System.out.println("Switches... Encontrados: " + cantidadSw);
            for (Equipo e : listaEquipos) {
                if (e instanceof Switch) {
                    System.out.print("Fabricante: " + e.getFabricante() + "/ Modelo: " + e.getModelo());

                    Switch sw = (Switch) e;
                    System.out.println(" / Puertos: " + sw.getNumeroPuertos());
                }
            }
        } else {
            System.out.println("No hay Switches registrados");
        }

        if (cantidadRouters > 0) {
            System.out.println("Routers... Encontrados: " + cantidadRouters);
            for (Equipo e : listaEquipos) {
                if (e instanceof Router) {
                    System.out.print("Fabricante: " + e.getFabricante() + "/ Modelo: " + e.getModelo());

                    Router r = (Router) e;

                    System.out.println(" / IP: " + r.getIp());
                    if (r.getSw() != null) {
                        System.out.println("Switch del router:" + " Modelo: " + r.getSw().getModelo() + " / Puertos: " + r.getSw().getNumeroPuertos());
                    }
                    if (r.getAp() != null) {
                        System.out.println("AP del router:" + " Fabricante: " + r.getAp().getFabricante() + " / Antenas: " + r.getAp().getNumeroAntenas());
                    }
                }
            }
        } else {
            System.out.println("No hay routers registrados");
        }
    }

    public void searchRouters() {

        ArrayList<Router> listaRoutersEnc = new ArrayList<>();
        for (Equipo e : listaEquipos) {
            if (e instanceof Router) {
                listaRoutersEnc.add((Router) e);
            }
        }

        if (listaRoutersEnc.size() > 0) {
            System.out.print("Ingrese la palabra a buscar: ");
            String name = sc.nextLine();

            //Router[] routerFound = new Router[contadorRouter];
            //int numberRouterFound = 0;
            for (Router router : listaRoutersEnc) {
                if (!router.getFabricante().equalsIgnoreCase(name)
                        && !router.getIp().equalsIgnoreCase(name)) {
                    listaRoutersEnc.remove(router);
                }
            }

            if (listaRoutersEnc.size() > 0) {
                System.out.println("Se encontraron los siguientes " + listaRoutersEnc.size() + " routers:");
                for (Router router : listaRoutersEnc) {
                    System.out.println("Fabricante: " + router.getFabricante() + "/ Modelo: " + router.getModelo() + " / IP: " + router.getIp());

                    if (router.getSw() != null) {
                        System.out.println("Switch del router:" + " Modelo: " + router.getSw().getModelo() + " / Puertos: " + router.getSw().getNumeroPuertos());
                    }
                    if (router.getAp() != null) {
                        System.out.println("AP del router:" + " Fabricante: " + router.getAp().getFabricante() + " / Antenas: " + router.getAp().getNumeroAntenas());
                    }
                }
            } else {
                System.out.println("No se encontró ningun router");
            }
        } else {
            System.out.println("No hay routers registrados");
        }
    }

}
