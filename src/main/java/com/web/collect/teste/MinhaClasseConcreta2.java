package com.web.collect.teste;

import org.springframework.stereotype.Component;

@Component
public class MinhaClasseConcreta2 implements MinhaClasse{
    @Override
    public void metodo() {
        System.out.println("Método de MinhaClasseConcreta2");
    }
}
