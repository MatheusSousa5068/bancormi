package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        // Verifica se o IP do servidor foi passado como argumento
//        if (args.length < 1) {
//            System.out.println("Erro: é necessário fornecer o IP do servidor.");
//            return;
//        }

        // O IP do servidor é passado como o primeiro argumento
        String servidorIP = "10.0.72.71";

        // Conecta ao RMI Registry do servidor remoto usando o IP fornecido
        Registry registry = LocateRegistry.getRegistry(servidorIP);
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while (opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    // Chamada ao método remoto para verificar saldo
                    System.out.println(banco.saldo(conta));
                    break;
                }
                case 2: {
                    // Chamada ao método remoto para verificar a quantidade de contas
                    System.out.println(banco.quantidadeContas());
                    break;
                }
                case 3: {
                    // Chamada ao método remoto para cadastrar uma nova conta
                    System.out.println("Digite o número da nova conta:");
                    String numero = entrada.next();
                    System.out.println("Digite o saldo inicial da conta:");
                    double saldoInicial = entrada.nextDouble();
                    try {
                        banco.adicionarConta(new Conta(numero, saldoInicial));
                    } catch (RemoteException e) {
                        System.out.println("Erro ao adicionar conta: " + e.getMessage());
                    }
                    break;
                }
                case 4: {
                    // Chamada ao método remoto para pesquisar uma conta
                    System.out.println("Digite o número da conta a ser pesquisada:");
                    String numero = entrada.next();
                    Conta conta = banco.pesquisarConta(numero);
                    if (conta != null) {
                        System.out.println("Conta encontrada: " + conta);
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;
                }
                case 5: {
                    // Chamada ao método remoto para remover uma conta
                    System.out.println("Digite o número da conta a ser removida:");
                    String numero = entrada.next();
                    try {
                        banco.removerConta(numero);
                    } catch (RemoteException e) {
                        System.out.println("Erro ao remover conta: " + e.getMessage());
                    }
                    break;
                }
                case 6: {
                    // Chamada ao método remoto para listar todas as contas
                    List<Conta> contas = banco.listarContas();
                    System.out.println("Lista de contas:");
                    for (Conta c : contas) {
                        System.out.println(c);
                    }
                    break;
                }
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI - by Matheus Pereira de Sousa ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Adicionar nova conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("6 - Listar contas");
        System.out.println("9 - Sair");
    }
}
