package com.web.collect;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@OpenAPIDefinition(servers = { @Server(url = "/", description = "Default Server URL") })
@SpringBootApplication
public class CollectApplication {

	public static void main(String[] args) {
		try{
			Document doc = Jsoup.connect("https://www.lojamythos.com.br/hqs-livro?loja=1119494&categoria=73&categories%5B%5D=AS%2BGRANDES%2BAVENTURAS%2BDE%2BTEX").get();

			Elements productNameDivs = doc.select("div.product-name");

			List<String> agatexProducts = new ArrayList<>();

			Pattern pattern = Pattern.compile("AGATEX.*", Pattern.CASE_INSENSITIVE);

			for(Element productNameDiv : productNameDivs){
				String productName = productNameDiv.text();

				Matcher matcher = pattern.matcher(productName);
				if(matcher.matches()){
					agatexProducts.add(productName);
				}
			}

			agatexProducts.sort(Comparator.naturalOrder());

			System.out.println("Produtos AGATEX:");
			for(String product : agatexProducts){
				System.out.println(product);
			}

//			Element body = doc.body();
//
//			Elements divs = body.getElementsByTag("div");
//
//			for(Element div : divs){
//				Elements paragraphs = div.getElementsByTag("p");
//				if(!paragraphs.isEmpty()){
//					System.out.println("Conteúdo dentro da div:");
//					for(Element paragraph : paragraphs){
//						System.out.println(paragraph.text());
//					}
//				}
//			}
		}catch (IOException ex){
			ex.printStackTrace();
		}


		SpringApplication.run(CollectApplication.class, args);
	}

}
