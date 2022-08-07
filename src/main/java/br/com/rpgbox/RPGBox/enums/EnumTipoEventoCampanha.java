package br.com.rpgbox.RPGBox.enums;

public class EnumTipoEventoCampanha {

    private TipoEventoCampanhaEnum descricaoTipoCampanha;

    public enum TipoEventoCampanhaEnum {
        CRIACAO_CAMPANHA,
        ADICAO_ANOTACAO,
        ADICAO_PERSONAGEM,
        REMOCAO_PERSONAGEM,
        ADICAO_COMBATE;
    }

    public EnumTipoEventoCampanha(TipoEventoCampanhaEnum descricaoTipoCampanha) {
        this.descricaoTipoCampanha = descricaoTipoCampanha;
    }

    public Integer getCodigoTipoEvento() {
        int codigoTipoEvento = 0;
        switch(descricaoTipoCampanha) {
            case CRIACAO_CAMPANHA:
                codigoTipoEvento = 1;
                break;

            case ADICAO_PERSONAGEM:
                codigoTipoEvento = 3;
                break;

            case REMOCAO_PERSONAGEM:
                codigoTipoEvento = 4;
                break;

            case ADICAO_ANOTACAO:
                codigoTipoEvento = 5;
                break;

            case ADICAO_COMBATE:
                codigoTipoEvento = 6;
                break;
        }
        return codigoTipoEvento;
    }
}
