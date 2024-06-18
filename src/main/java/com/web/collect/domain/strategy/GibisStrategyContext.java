package com.web.collect.domain.strategy;

import com.web.collect.domain.enumeration.StrategyTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GibisStrategyContext {
    private final List<GibisStratey> strategies;

//    public void executeStrategies() {
//        for (GibisStratey strategy : strategies) {
//            strategy.getAllCatalog();
//        }
//    }

    public GibisStratey getStrategyByType(StrategyTypeEnum strategyTypeEnum){
        return strategies
                .stream()
                .filter(strategy -> strategy.getType().equals(strategyTypeEnum))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Estratégia não encontrada para o tipo " + strategyTypeEnum));
    }
}
