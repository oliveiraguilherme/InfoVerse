package com.web.collect.teste;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class MinhaClasseContexto {
    private final List<MinhaClasse> strategies;

    public void executeStrategies() {
        for (MinhaClasse strategy : strategies) {
            strategy.metodo();
        }
    }
}
