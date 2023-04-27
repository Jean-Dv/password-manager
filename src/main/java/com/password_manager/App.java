package com.password_manager;

import java.util.Scanner;

import com.password_manager.controller.SecurePasswordImplement;
import com.password_manager.controller.UserControllerImplement;
import com.password_manager.controller.VaultControllerImplement;
import com.password_manager.models.SecurePassword;
import com.password_manager.models.Vault;

/**
 * Hello world!
 *
 */
public class App {
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void addNewVaultMenu(Scanner sc, String username) {
        System.out.println("[*] Enter the name of the vault: ");
        String vaultName = sc.nextLine();
        System.out.println("[*] Enter the username: ");
        String vaultUsername = sc.nextLine();
        System.out.println("[*] Enter the password: ");
        String vaultPassword = sc.nextLine();
        System.out.println("[*] Enter the url: ");
        String vaultUrl = sc.nextLine();
        VaultControllerImplement vaultController = new VaultControllerImplement(username);
        vaultController.addVault(vaultName, vaultUsername, vaultPassword, vaultUrl);
    }

    public static void getVaultMenu(Scanner sc, String username) {
        VaultControllerImplement vaultController = new VaultControllerImplement(username);
        System.out.println("[*] Enter the name of the vault: ");
        String vaultName = sc.nextLine();
        System.out.println(vaultController.getVault(vaultName).toString());
    }

    public static void deleteVaultMenu(Scanner sc, String username) {
        VaultControllerImplement vaultController = new VaultControllerImplement(username);
        System.out.println("[*] Enter the name of the vault: ");
        String vaultName = sc.nextLine();
        vaultController.removeVault(vaultName);
    }

    public static void updateVaultMenu(Scanner sc, String username) {
        VaultControllerImplement vaultController = new VaultControllerImplement(username);
        System.out.println("[*] Enter the name of the vault: ");
        String vaultName = sc.nextLine();
        Vault vault = vaultController.getVault(vaultName);
        System.out.println("[*] What do you want to update?");
        System.out.println("[*] 1. Update the name of the vault");
        System.out.println("[*] 2. Update the username of the vault");
        System.out.println("[*] 3. Update the password of the vault");
        System.out.println("[*] 4. Update the url of the vault");
        System.out.println("[*] Please enter your choice: ");
        byte choice = sc.nextByte();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.println("[*] Enter the new name of the vault: ");
                vaultName = sc.nextLine();
                vault.setName(vaultName);
                break;
            case 2:
                System.out.println("[*] Enter the new username of the vault: ");
                String vaultUsername = sc.nextLine();
                vault.setUsername(vaultUsername);
                break;
            case 3:
                System.out.println("[*] Enter the new password of the vault: ");
                String vaultPassword = sc.nextLine();
                vault.setPassword(vaultPassword);
                break;
            case 4:
                System.out.println("[*] Enter the new url of the vault: ");
                String vaultUrl = sc.nextLine();
                vault.setUrl(vaultUrl);
                break;
            default:
                System.out.println("[*] Invalid choice!");
                break;
        }
        vaultController.updateVault(vault, vaultName);
    }

    public static void createPasswordSecure(Scanner sc) {
        SecurePasswordImplement securePasswordController = new SecurePasswordImplement();
        boolean hasNumbers = false;
        boolean hasSymbols = false;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        System.out.println("[*] Please enter the number of digits: ");
        int length = sc.nextInt();
        sc.nextLine();
        System.out.println("[*] Please enter y/n if your want numbers: ");
        String numbers = sc.nextLine();
        if (numbers.equals("y")) {
            hasNumbers = true;
        }
        System.out.println("[*] Please enter y/n if your want special characters: ");
        String symbols = sc.nextLine();
        if (symbols.equals("y")) {
            hasSymbols = true;
        }
        System.out.println("[*] Please enter y/n if your want uppercase characters: ");
        String uppercaseCharacters = sc.nextLine();
        if (uppercaseCharacters.equals("y")) {
            hasUppercase = true;
        }
        System.out.println("[*] Please enter y/n if your want lowercase characters: ");
        String lowercaseCharacters = sc.nextLine();
        if (lowercaseCharacters.equals("y")) {
            hasLowercase = true;
        }
        SecurePassword securePassword = new SecurePassword(length, hasNumbers, hasSymbols, hasUppercase, hasLowercase);
        System.out.println("[*] Your password is: " + securePasswordController.generatePassword(securePassword));
    }

    public static void main(String[] args) {
        UserControllerImplement userController = new UserControllerImplement();
        Scanner sc = new Scanner(System.in);
        System.out.println("[*] Welcome to the password manager!");
        System.out.println("[*] Please enter your username: ");
        String username = sc.nextLine();
        System.out.println("[*] Please enter your password: ");
        String password = sc.nextLine();
        boolean isAuthenticated = userController.signIn(username, password);
        if (isAuthenticated) {
            clearConsole();
            System.out.println("[*] Welcome back " + username);
            System.out.println("[*] 1. Add a new vault");
            System.out.println("[*] 2. Get a vault");
            System.out.println("[*] 3. Remove a vault");
            System.out.println("[*] 4. Update a vault");
            System.out.println("[*] 5. Generate password secure");
            System.out.println("[*] Please enter your choice: ");
            byte choice = sc.nextByte();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addNewVaultMenu(sc, username);
                    break;
                case 2:
                    getVaultMenu(sc, username);
                    break;
                case 3:
                    deleteVaultMenu(sc, username);
                    break;
                case 4:
                    updateVaultMenu(sc, username);
                    break;
                case 5:
                    createPasswordSecure(sc);
                    break;
                case 6:
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("[*] Invalid choice!");
                    break;
            }
        }
        sc.close();
    }
}
