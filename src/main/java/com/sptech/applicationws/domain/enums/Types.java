package com.sptech.applicationws.domain.enums;

public enum Types {
    GERAL("Geral"),
    CHAPEU("Chapéus"),
    REGATA("Regatas"),
    CAMISETA("Camisetas"),
    CAMISA("Camisas"),
    MOLETOM("Moletons"),
    JAQUETA("Jaquetas"),
    CALCA("Calças"),
    BERMUDA("Bermudas"),
    MEIA("Meias"),
    TENIS("Tênis");

    private final String description;

    Types(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
