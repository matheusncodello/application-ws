package com.sptech.applicationws.infra.configurations.utils;

public class PilhaObj<T> {

    private T[] pilha;
    private int topo;

    public PilhaObj(int capacidade) {

        this.pilha = (T[]) new Object[capacidade];
        topo = -1;

    }

    public Boolean isEmpty() {
        return topo == -1;
    }

    public Boolean isFull() {
        return pilha.length == topo+1;
    }

    public void push(T info) {

        if(isFull()){

            throw new IllegalStateException();

        }else {

            pilha[++topo] = info;

        }
    }

    public T pop() { return isEmpty()? null : pilha [topo--]; }

    public T peek() { return isEmpty()? null : pilha [topo]; }

    public void exibe() {

        for(T TdaVez: pilha){

            System.out.println(TdaVez);

        }

    }

    public int tamanho(){
        return topo;}
}