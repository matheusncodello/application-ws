package com.sptech.applicationws.infra.configurations.mapper;

public class ListObj<T> {
    private T[] array;
    private int elementNumber;

    public ListObj(Integer size) {
        this.array = (T[]) new Object[size];
        this.elementNumber = elementNumber;
    }

    public void toAdd(T element) {
        if (elementNumber >= array.length) {
            System.out.println("The list is full\n");
        }
        else {
            array[elementNumber++] = element;
        }
    }

    public void exhibit() {
        if (elementNumber == 0) {
            System.out.println("\nA The list is empty.");
        }
        else {
            System.out.println("\nList elements:");
            for (int i = 0; i < elementNumber; i++) {
                System.out.println(array[i]);
            }
        }
    }

    public int search(T searchedElement) {
        for (int i = 0; i < elementNumber; i++) {
            if (array[i].equals(searchedElement)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeByIndex(int index) {
        if (index < 0 || index >= elementNumber) {
            System.out.println("\nInvalid index!");
            return false;
        }

        for (int i = index; i < elementNumber-1; i++) {
            array[i] = array[i+1];
        }

        elementNumber--;
        return true;
    }

    public boolean removeElement(T elementToRemove) {
        return removeByIndex(search(elementToRemove));
    }

    public int getSize() {
        return elementNumber;
    }

    public T getElement(int index) {
        if (index < 0 || index >= elementNumber) {
            return null;
        }
        else {
            return array[index];
        }
    }

    public void clear() {
        elementNumber = 0;
    }
}
