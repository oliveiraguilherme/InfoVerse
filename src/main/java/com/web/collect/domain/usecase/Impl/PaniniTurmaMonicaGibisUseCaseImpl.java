package com.web.collect.domain.usecase.Impl;

import com.web.collect.domain.dto.ComicsDTO;
import com.web.collect.domain.enumeration.StrategyTypeEnum;
import com.web.collect.domain.strategy.GibisStratey;
import com.web.collect.domain.usecase.PaniniTurmaMonicaGibisUseCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class PaniniTurmaMonicaGibisUseCaseImpl implements GibisStratey {

    @Override
    public StrategyTypeEnum getType() {
        return StrategyTypeEnum.PANINI;
    }

    @Override
    public List<ComicsDTO> getAllCatalog(int page, int size){
        List<ComicsDTO> allGibis = new ArrayList<>();

        try {
            String baseUrl = "https://panini.com.br/mauricio-de-sousa-producoes/gibis-turma-da-monica/revistas-mensais?p=";
            int currentPage = 1;

            while (true) {
                String url = baseUrl + currentPage;
                Document doc = Jsoup.connect(url).get();

                Elements productItens = doc.select("li.item.product.product-item");

                for(Element item : productItens){
                    String tittle = item.select("a.product-item-link").text();
                    System.out.println(tittle);
                    String img = item.select("img.product-image-photo").attr("data-srcset");
                    String description = null;
                    String author = null;
                    String artist = null;
                    String price = item.select("span.price").first().text().replace("R$", "").replace(",",".");
                    //double value = Double.parseDouble(price);

                    ComicsDTO comicsDTO = ComicsDTO.builder()
                            .tittle(tittle)
                            .img(img)
                            .description(description)
                            .author(author)
                            .artist(artist)
                            .value(price)
                            .build();

                    allGibis.add(comicsDTO);
                }

                Element nextPage = doc.selectFirst("li.item.pages-item-next");
                if(nextPage == null){
                    break;
                }
                currentPage ++;
            }
            System.out.println("Todos os produtos");
            for (ComicsDTO product : allGibis){
                System.out.println(product);
            }
        }catch  (IOException ex){
            ex.printStackTrace();
        }

        allGibis.sort(Comparator.comparing(ComicsDTO::getTittle));

        int fromIndex = Math.min(page * size, allGibis.size());
        int toIndex = Math.min((page + 1) * size, allGibis.size());

        return allGibis.subList(fromIndex, toIndex);
    }
}
