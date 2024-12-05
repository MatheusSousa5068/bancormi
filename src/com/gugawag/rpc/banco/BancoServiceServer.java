package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        contas = new ArrayList<>();
        // Adicionando contas iniciais para teste
        contas.add(new Conta("1", 100.0));
        contas.add(new Conta("2", 156.0));
        contas.add(new Conta("3", 950.0));
    }

    @Override
    public double saldo(String numero) throws RemoteException {
        Conta conta = pesquisarConta(numero);
        if (conta != null) {
            return conta.getSaldo();
        }
        throw new RemoteException("Conta não encontrada.");
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public void adicionarConta(Conta conta) throws RemoteException {
        // Verifica se a conta já existe
        if (pesquisarConta(conta.getNumero()) != null) {
            throw new RemoteException("Conta já existe.");
        }
        contas.add(conta);
        System.out.println("Conta adicionada com sucesso: " + conta.getNumero());
    }

    @Override
    public Conta pesquisarConta(String numero) throws RemoteException {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                return conta;
            }
        }
        return null; // Caso não encontre a conta
    }

    @Override
    public void removerConta(String numero) throws RemoteException {
        Conta conta = pesquisarConta(numero);
        if (conta != null) {
            contas.remove(conta);
            System.out.println("Conta removida com sucesso: " + numero);
        } else {
            throw new RemoteException("Conta não encontrada.");
        }
    }

    @Override
    public List<Conta> listarContas() throws RemoteException {
        return contas;
    }
}
