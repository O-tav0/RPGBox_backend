package br.com.rpgbox.RPGBox.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumPermissoes {

    ADMIN(1L,"ADMIN"),
    MANAGER(2L, "MANAGER"),
    COMMOM_USER(3L, "COMMOM_USER");

    private Long codigo;
    private String descricao;
}
