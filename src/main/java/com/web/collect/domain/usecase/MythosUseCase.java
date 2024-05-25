package com.web.collect.domain.usecase;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MythosUseCase {

    public List<String> getAllCatalogBonelli();
}
