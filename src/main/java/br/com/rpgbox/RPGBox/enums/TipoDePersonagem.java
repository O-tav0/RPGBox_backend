package br.com.rpgbox.RPGBox.enums;

public class TipoDePersonagem {

    private final TipoPersonagemEnum descricaoTipoPersonagem;

    public enum TipoPersonagemEnum {
        AVENTUREIRO,
        NPC,
        INIMIGO
    }

    public TipoDePersonagem(TipoPersonagemEnum descricaoTipoPersonagem) {
        this.descricaoTipoPersonagem = descricaoTipoPersonagem;
    }

    public Integer getCodigoTipoPersonagem() {
        int codigoTipoPersonagem = 0;
        switch(descricaoTipoPersonagem) {
            case AVENTUREIRO:
                codigoTipoPersonagem = 1;
                break;

            case NPC:
                codigoTipoPersonagem = 2;
                break;

            case INIMIGO:
                codigoTipoPersonagem = 3;
                break;
        }
        return codigoTipoPersonagem;
    }
}
