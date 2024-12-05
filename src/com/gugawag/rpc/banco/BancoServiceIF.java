package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BancoServiceIF extends Remote {
    double saldo(String conta) throws RemoteException;

    int quantidadeContas() throws RemoteException;

    // Novo método para adicionar uma conta
    void adicionarConta(Conta conta) throws RemoteException;

    // Novo método para pesquisar uma conta
    Conta pesquisarConta(String numero) throws RemoteException;

    // Novo método para remover uma conta
    void removerConta(String numero) throws RemoteException;

    // Novo método para listar todas as contas
    List<Conta> listarContas() throws RemoteException;
}
