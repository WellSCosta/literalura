package com.wellscosta.literalura.service.converte;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);
}
