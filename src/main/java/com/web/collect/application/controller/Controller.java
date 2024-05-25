package com.web.collect.application.controller;

import com.web.collect.domain.usecase.MythosUseCase;
import com.web.collect.domain.usecase.PaniniTurmaMonicaGibisUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("info")
@Tag(name = "Info Controller", description = "RESTful API para administrar times")
public class Controller {

    private final MythosUseCase mythosUseCase;
    private final PaniniTurmaMonicaGibisUseCase paniniTurmaMonicaGibisUseCase;

    @Autowired
    public Controller(MythosUseCase mythosUseCase, PaniniTurmaMonicaGibisUseCase paniniTurmaMonicaGibisUseCase){
        this.mythosUseCase = mythosUseCase;
        this.paniniTurmaMonicaGibisUseCase = paniniTurmaMonicaGibisUseCase;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Deu certo, Agora eu só consigo acessar essa tela com o token, ou seja, se estiver logado" +
                "não irei me preocupar agora com cache e recarregamento de cache no localStorage, depois eu vejo, o importante é que está funcionando" +
                "quem sabe subir tudo num container e ver rodando num container!!!");
    }

    @GetMapping("/getAll")
    public List<String> getAll(){
        List<String> listaJunta = Stream
                .concat(mythosUseCase.getAllCatalogBonelli().stream(), paniniTurmaMonicaGibisUseCase.getAllCatalogPaniniTurmaMonicaGibis().stream())
                .collect(Collectors.toList());
        return listaJunta;
    }

}
