package com.web.collect.domain.strategy;

import com.web.collect.domain.dto.ComicsDTO;
import com.web.collect.domain.enumeration.StrategyTypeEnum;

import java.util.List;

public interface GibisStratey {
    StrategyTypeEnum getType();
    public List<ComicsDTO> getAllCatalog(int page, int size);
}
