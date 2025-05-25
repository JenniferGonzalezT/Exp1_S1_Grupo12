/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exp1_s1_grupo12;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Proyecto Gestion de cuentas Bank Boston
 * @author jennifer y guiselle
 */

public class Exp1_S1_Grupo12 {
    
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Cliente> clientes = new ArrayList<>();
    

    public static void main(String[] args) {
        System.out.println("¡BIENVENID@ A BANK BOSTON!");
        
        int opcion;
        
        do {            
            opcion = mostrarMenu();
            switch (opcion) {
                case 0:
                    System.out.println("\n\n==================== SALIR =====================");
                    System.out.println("Saliendo...\n¡Muchas gracias por su visita!");
                    break;
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    verDatosCliente();
                    break;
                case 3:
                    depositar();
                    break;
                case 4:
                    girar();
                    break;
                case 5:
                    consultarSaldo();
                    break; 
            }
        } while (opcion != 0);
        
        scanner.close();
    }
    
    
    static int mostrarMenu() {
        System.out.println("\n\n===================== MENÚ =====================");
        System.out.println("(1) Registrar cliente");
        System.out.println("(2) Ver datos cliente");
        System.out.println("(3) Depositar");
        System.out.println("(4) Girar");
        System.out.println("(5) Consultar saldo");
        System.out.println("(0) Salir");
        
        final int CANT_OPCIONES = 6;
        int opcion = 0;
        boolean opcionValida = false;
        String mensajeError = "La opción ingresada no es válida.\nRecuerde ingresar un número de 0 a " + (CANT_OPCIONES-1);
        
        while (!opcionValida) {
            System.out.print("\nIngrese el número correspondiente a su opción: ");
            try {
                opcion = scanner.nextByte();
                if (opcion >= 0 && opcion < CANT_OPCIONES) {
                    opcionValida = true;
                } else {
                    System.out.println(mensajeError);
                }
            } catch (InputMismatchException e) {
                System.out.println(mensajeError);
            } finally {
                scanner.nextLine();
            }
        }
        
        return opcion;
    }
    
    
    static void registrarCliente() {
        System.out.println("\n\n=============== REGISTRAR CLIENTE ==============");
        System.out.println("Para registrarse deberá ingresar los siguientes datos:");
        
        String rut = "";
        String nombre;
        String apellidoPaterno;
        String apellidoMaterno;
        String domicilio;
        String comuna;
        String telefono = "";
        String numeroCuenta = "";
        boolean datoValido = false;
        String mensajeError;
        
        //Rut
        mensajeError = "El Rut ingresado no es válido. Intente nuevamente.";
        while (!datoValido) {
            System.out.print("\n-> Rut (ej: 12.345.678-9): ");
            try {
                rut = scanner.next();
                if (rut.length() == 11 || rut.length() == 12) {
                    for (Cliente cliente : clientes) {
                        if (cliente.getRut().equals(rut)) {
                            System.out.println("Ya existe un cliente registrado con el Rut " + rut + ".");
                            System.out.println("Ahora regresaras al menú.");
                            return;
                        }
                    }
                    datoValido = true;
                } else {
                    System.out.println(mensajeError);
                }
            } catch (Exception e) {
                System.out.println(mensajeError);
            } finally {
                scanner.nextLine();
            }
        }
        
        
        //Nombre
        System.out.print("\n-> Nombre: ");
        nombre = scanner.next();
        scanner.nextLine();
        
        //Apellido Paterno
        System.out.print("\n-> Apellido Paterno: ");
        apellidoPaterno = scanner.next();
        scanner.nextLine();
        
        //Apellido Materno
        System.out.print("\n-> Apellido Materno: ");
        apellidoMaterno = scanner.next();
        scanner.nextLine();
        
        //Domicilio
        System.out.print("\n-> Domicilio: ");
        domicilio = scanner.nextLine();
        
        //Comuna
        System.out.print("\n-> Comuna: ");
        comuna = scanner.nextLine();
        
        //Telefono
        datoValido = false;
        mensajeError = "El Número de Teléfono ingresado no es válido. Intente nuevamente.";
        while (!datoValido) {
            System.out.print("\n-> Teléfono (ej: 123456789): ");
            try {
                telefono = scanner.next();
                if (telefono.length() == 9) {
                    datoValido = true;
                } else {
                    System.out.println(mensajeError);
                }
            } catch (Exception e) {
                System.out.println(mensajeError);
            } finally {
                scanner.nextLine();
            }
        }
        
        //Número de cuenta
        datoValido = false;
        mensajeError = "El Número de Cuenta ingresado no es válido. Intente nuevamente.";
        while (!datoValido) {
            System.out.print("\n-> Número de cuenta corriente (ej: 123456789): ");
            try {
                numeroCuenta = scanner.next();
                if (numeroCuenta.length() == 9) {
                    datoValido = true;
                } else {
                    System.out.println(mensajeError);
                }
            } catch (Exception e) {
                System.out.println(mensajeError);
            } finally {
                scanner.nextLine();
            }
        }
        
        CuentaCorriente cuenta = new CuentaCorriente(numeroCuenta);
        Cliente cliente = new Cliente(rut, nombre, apellidoPaterno, apellidoMaterno, domicilio, comuna, telefono, cuenta);
        clientes.add(cliente);
        System.out.println("\n¡Cliente registrado con éxito!");
    }
    
    
    static Cliente buscarCliente() {
        String rut;
        boolean rutValido = false;
        String mensajeError = "El Rut ingresado no es válido. Intente nuevamente.";
        
        while (!rutValido) {
            System.out.print("\nIngrese su Rut (ej: 12.345.678-9): ");
            try {
                rut = scanner.next();
                if (rut.length() == 11 || rut.length() == 12) {
                    for (Cliente cliente : clientes) {
                        if (cliente.getRut().equals(rut)) {
                            rutValido = true;
                            return cliente;
                        }
                    }
                    return null;
                } else {
                    System.out.println(mensajeError);
                }
            } catch (Exception e) {
                System.out.println(mensajeError);
            } finally {
                scanner.nextLine();
            }
        }
        return null;
    }
    
    
    static void verDatosCliente() {
        System.out.println("\n\n=============== VER DATOS CLIENTE ==============");
        
        if (clientes.isEmpty()) {
            System.out.println("Aún no hay clientes registrados. Volverá al menú.");
            return;
        }
        
        Cliente cliente = buscarCliente();
        if (cliente != null) {
            cliente.datosCliente();
        } else {
            System.out.println("Cliente no encontrado.\nAhora regresará al menú.");
        }
    }
    
    
    static void depositar() {
        System.out.println("\n\n=================== DEPOSITAR ==================");
        
        if (clientes.isEmpty()) {
            System.out.println("Aún no hay clientes registrados. Volverá al menú.");
            return;
        }
        
        Cliente cliente = buscarCliente();
        if (cliente != null) {
            cliente.getCuenta().depositar(scanner);
        } else {
            System.out.println("Cliente no encontrado.\nAhora regresará al menú.");
        }
    }
    
    
    static void girar() {
        System.out.println("\n\n===================== GIRAR ====================");
        
        if (clientes.isEmpty()) {
            System.out.println("Aún no hay clientes registrados. Volverá al menú.");
            return;
        }
        
        Cliente cliente = buscarCliente();
        if (cliente != null) {
            cliente.getCuenta().girar(scanner);
        } else {
            System.out.println("Cliente no encontrado.\nAhora regresará al menú.");
        }
    }
    
    
    static void consultarSaldo() {
        System.out.println("\n\n================ CONSULTAR SALDO ===============");
        
        if (clientes.isEmpty()) {
            System.out.println("Aún no hay clientes registrados. Volverá al menú.");
            return;
        }
        
        Cliente cliente = buscarCliente();
        if (cliente != null) {
            cliente.getCuenta().consultarSaldo();
        } else {
            System.out.println("Cliente no encontrado.\nAhora regresará al menú.");
        }
    }
    
}
