package br.com.edson.msorder.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErroDeRequestDto {
    private Integer status_code;
    private String message;
}
