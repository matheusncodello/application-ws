package com.matheusncodello.applicationws.infra.configurations.utils;

public class FilaObj<T> {

    private int tamanho;
    private T[] fila;

    public FilaObj(int capacidade) {

        fila = (T[]) new Object[capacidade];
        tamanho = 0;

    }
    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {

        if(isFull()){

            throw new IllegalStateException();

        }else{

            fila[tamanho++] = info;

        }
    }

    public T peek() { return isEmpty()? null : fila[0]; }

    public T poll() {

        T retorno = fila[0];

        if(!isEmpty()){

            for(int i = 0; i < tamanho; i++){

                if(i+1 != tamanho) {

                    fila[i] = fila[i + 1];

                }
            }

            fila[tamanho-1] = null;
            tamanho--;
        }

        return retorno;
    }

    public void exibe() {

        if(isEmpty()){

            System.out.println("Fila Vazia");
            return;

        }

        StringBuilder str = new StringBuilder();

        str.append("[");
        for(int i =0; i < tamanho ; i++){

            str.append(fila[i]);

            if(tamanho-1 != i){

                str.append(", ");

            }

        }
        str.append("]");

        System.out.println(str);

    }

    public T[] getFila() {
        return fila;
    }
}