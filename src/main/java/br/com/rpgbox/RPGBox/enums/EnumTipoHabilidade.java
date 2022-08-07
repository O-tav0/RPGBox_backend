package br.com.rpgbox.RPGBox.enums;


public class EnumTipoHabilidade {

    public enum TipoHabilidadeEnum {
        ATAQUE,
        SUPORTE
    }

    private final TipoHabilidadeEnum descricaoTipoHabilidade;

    public EnumTipoHabilidade(TipoHabilidadeEnum descricaoTipoHabilidade) {
        this.descricaoTipoHabilidade = descricaoTipoHabilidade;
    }

    public Integer getCodigoTipoHabilidade() {
        int codigoTipoHabilidade = 0;
        switch (descricaoTipoHabilidade) {
            case ATAQUE:
                codigoTipoHabilidade = 1;
                break;

            case SUPORTE:
                codigoTipoHabilidade = 2;
                break;
        }
        return codigoTipoHabilidade;
    }
}
