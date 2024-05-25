package com.web.collect.domain.strategy;

import com.web.collect.domain.enumeration.StrategyTypeEnum;

import java.util.List;

public interface GibisStratey {
    StrategyTypeEnum getType();
    public List<String> getAllCatalog();
}
