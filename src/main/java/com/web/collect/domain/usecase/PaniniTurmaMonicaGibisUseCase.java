package com.web.collect.domain.usecase;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface PaniniTurmaMonicaGibisUseCase {
    public List<String> getAllCatalogPaniniTurmaMonicaGibis();
}
