package com.web.collect.domain.usecase.Impl;

import com.web.collect.domain.dto.ComicsDTO;
import com.web.collect.domain.enumeration.StrategyTypeEnum;
import com.web.collect.domain.strategy.GibisStratey;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class MythosUseCaseImpl implements GibisStratey {

    @Override
    public StrategyTypeEnum getType() {
        return StrategyTypeEnum.MYTHOS;
    }

    public List<ComicsDTO> getAllCatalog(int page, int size){
        List<ComicsDTO> allProducts = new ArrayList<>();

        try{
            String baseUrl = "https://www.lojamythos.com.br/loja/catalogo.php?loja=1119494&categoria=2235&pg=";
            int currentPage = 1;

            while (true){
                String url = baseUrl + currentPage;
                Document doc = Jsoup.connect(url).get();

                Elements itemFlexElements = doc.select("li.item.flex");

                for(Element itemFlexElement : itemFlexElements  ){
                    String productName = itemFlexElement.select("div.product-name").text();
                    System.out.println(productName);
                    Element imageElement = itemFlexElement.selectFirst("a.space-image img.lazyload");
                    String img = imageElement != null ? imageElement.attr("data-src") : "";
                    String description = null;
                    String author = null;
                    String artist = null;
                    String productValue = itemFlexElement.select("div.price-off").text().trim();
                    //double value = Double.parseDouble(productValue.replace("R$", "").replace(".", "").replace(",", ".").trim());
                    ComicsDTO comicsDTO = ComicsDTO.builder()
                            .tittle(productName)
                            .img(img)
                            .description(description)
                            .author(author)
                            .artist(artist)
                            .value(productValue)
                            .build();
                    allProducts.add(comicsDTO   );
                }

                Element nextPageSpan = doc.selectFirst("span.page-next.page-link");
                if(nextPageSpan == null){
                    break;
                }

                currentPage++;

            }

            System.out.println("Todos os produtos");
            for (ComicsDTO product : allProducts){
                System.out.println(product);
            }


        }catch (IOException ex){
            ex.printStackTrace();
        }

        allProducts.sort(Comparator.comparing(ComicsDTO::getTittle));

        int fromIndex = Math.min(page * size, allProducts.size());
        int toIndex = Math.min((page + 1) * size, allProducts.size());

        return  allProducts.subList(fromIndex, toIndex);
    }
}
