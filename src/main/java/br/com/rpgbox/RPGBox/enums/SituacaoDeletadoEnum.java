package br.com.rpgbox.RPGBox.enums;

public class SituacaoDeletadoEnum {
    private final SituacaoEnum stDeletado;

    public enum SituacaoEnum {
        SIM
    }

    public SituacaoDeletadoEnum(SituacaoEnum stDeletado) {
        this.stDeletado = stDeletado;
    }

    public String getSituacao() {
        String sitauacao = "";
        switch(stDeletado) {
            case SIM:
                sitauacao = "S";
                break;
        }
        return sitauacao;
    }
}
