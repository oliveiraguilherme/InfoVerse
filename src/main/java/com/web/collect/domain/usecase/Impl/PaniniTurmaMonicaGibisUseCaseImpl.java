package com.web.collect.domain.usecase.Impl;

import com.web.collect.domain.usecase.PaniniTurmaMonicaGibisUseCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaniniTurmaMonicaGibisUseCaseImpl implements PaniniTurmaMonicaGibisUseCase {

    @Override
    public List<String> getAllCatalogPaniniTurmaMonicaGibis(){
        List<String> allGibis = new ArrayList<>();

        try {
            String baseUrl = "https://panini.com.br/mauricio-de-sousa-producoes/gibis-turma-da-monica/revistas-mensais?p=";
            int currentPage = 1;

            while (true) {
                String url = baseUrl + currentPage;
                Document doc = Jsoup.connect(url).get();

                Elements productItemLinkA = doc.select("a.product-item-link");

                for(Element product : productItemLinkA){
                    String productName = product.text();
                    allGibis.add(productName);
                }

                Element nextPage = doc.selectFirst("li.item.pages-item-next");
                if(nextPage == null){
                    break;
                }
                currentPage ++;
            }
        }catch  (IOException ex){
            ex.printStackTrace();
        }

        return allGibis;
    }
}
