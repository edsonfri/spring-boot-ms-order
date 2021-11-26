package br.com.edson.msorder.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeRequestDto> handle(MethodArgumentNotValidException exception){
        List<ErroDeRequestDto> dto = new ArrayList<>();
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();

        fieldError.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeRequestDto erro = new ErroDeRequestDto(HttpStatus.BAD_REQUEST.value(), mensagem);
            dto.add(erro);
        });

        return dto;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ErroDeRequestDto> notFoundHandle(RuntimeException exception) {
        ErroDeRequestDto dto = new ErroDeRequestDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);

    }
    
}
