package com.web.collect.application.controller;

import com.web.collect.domain.dto.ComicsDTO;
import com.web.collect.domain.enumeration.StrategyTypeEnum;
import com.web.collect.domain.strategy.GibisStrategyContext;
import com.web.collect.domain.usecase.MythosUseCase;
import com.web.collect.domain.usecase.PaniniTurmaMonicaGibisUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("info")
@Tag(name = "Info Controller", description = "RESTful API para administrar times")
public class Controller {

//    private final MythosUseCase mythosUseCase;
//    private final PaniniTurmaMonicaGibisUseCase paniniTurmaMonicaGibisUseCase;

    private final GibisStrategyContext gibisStrategyContext;

    @Autowired
    public Controller(GibisStrategyContext gibisStrategyContext){
//        this.mythosUseCase = mythosUseCase;
//        this.paniniTurmaMonicaGibisUseCase = paniniTurmaMonicaGibisUseCase;
        this.gibisStrategyContext = gibisStrategyContext;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Deu certo, Agora eu só consigo acessar essa tela com o token, ou seja, se estiver logado" +
                "não irei me preocupar agora com cache e recarregamento de cache no localStorage, depois eu vejo, o importante é que está funcionando" +
                "quem sabe subir tudo num container e ver rodando num container!!!");
    }

    @GetMapping("/getAllOld")
    public ResponseEntity<List<ComicsDTO>> getAll(@RequestParam int page, @RequestParam int size){
        List<ComicsDTO> listaJunta = new ArrayList<>();
        for(StrategyTypeEnum strategyTypeEnum: StrategyTypeEnum.values()){
            List<ComicsDTO> comicsList = gibisStrategyContext.getStrategyByType(strategyTypeEnum).getAllCatalog(page, size);
            if(comicsList != null){
                listaJunta.addAll(comicsList);
            }
        }
        //var listaMythos = gibisStrategyContext.getStrategyByType(StrategyTypeEnum.PANINI).getAllCatalog();
//        List<String> listaJunta = Stream
//                .concat(mythosUseCase.getAllCatalogBonelli().stream(), paniniTurmaMonicaGibisUseCase.getAllCatalogPaniniTurmaMonicaGibis().stream())
//                .collect(Collectors.toList());
        return ResponseEntity.ok(Optional.of(listaJunta).orElseThrow(() -> new RuntimeException("No comics found in the Mythos catalog")));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ComicsDTO>> getAllMythos(@RequestParam int page, @RequestParam int size){
        return ResponseEntity
                .ok(Optional.ofNullable(gibisStrategyContext.getStrategyByType(StrategyTypeEnum.MYTHOS)
                        .getAllCatalog(page, size))
                        .orElseThrow(() -> new RuntimeException("No comics found in the Mythos catalog")));
    }

}
