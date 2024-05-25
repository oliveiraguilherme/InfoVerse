package com.web.collect.domain.usecase.Impl;

import com.web.collect.domain.enumeration.StrategyTypeEnum;
import com.web.collect.domain.strategy.GibisStratey;
import com.web.collect.domain.usecase.MythosUseCase;
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
public class MythosUseCaseImpl implements GibisStratey {

    @Override
    public StrategyTypeEnum getType() {
        return StrategyTypeEnum.MYTHOS;
    }

    public List<String> getAllCatalog(){
        List<String> allProducts = new ArrayList<>();

        try{
            String baseUrl = "https://www.lojamythos.com.br/loja/catalogo.php?loja=1119494&categoria=2235&pg=";
            int currentPage = 1;

            while (true){
                String url = baseUrl + currentPage;
                Document doc = Jsoup.connect(url).get();

                Elements productNamesDivs = doc.select("div.product-name");

                for(Element product : productNamesDivs){
                    String productName = product.text();
                    allProducts.add(productName);
                }

                Element nextPageSpan = doc.selectFirst("span.page-next.page-link");
                if(nextPageSpan == null){
                    break;
                }

                currentPage++;

            }

            System.out.println("Todos os produtos");
            for (String product : allProducts){
                System.out.println(product);
            }


        }catch (IOException ex){
            ex.printStackTrace();
        }

        allProducts.sort(Comparator.naturalOrder());

        return allProducts;
    }
}
