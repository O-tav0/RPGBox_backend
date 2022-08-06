package br.com.rpgbox.RPGBox.Enum;

public class EnumStatusCombate {

    private final StatusCombateEnum descricaoStatusCombate;

    public enum StatusCombateEnum {
        PENDENTE,
        FINALIZADO
    }

    public EnumStatusCombate(StatusCombateEnum descricaoStatusCombate) {
        this.descricaoStatusCombate = descricaoStatusCombate;
    }

    public Integer getCodigoStatusCombate() {
        int codigoStatusCombate = 0;
        switch(descricaoStatusCombate) {
            case PENDENTE:
                codigoStatusCombate = 1;
                break;

            case FINALIZADO:
                codigoStatusCombate = 2;
                break;

        }
        return codigoStatusCombate;
    }
}
