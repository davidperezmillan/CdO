package com.davidperezmillan.cdovideostoreservice.application.scrap.rules;

import com.davidperezmillan.cdovideostoreservice.application.scrap.dtos.CalidadEnum;
import com.davidperezmillan.cdovideostoreservice.application.scrap.dtos.ScrapBeanResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RulesDonTorrent {

    private static Pattern patron = Pattern.compile("^(.+) - (\\d+)ª Temporada \\[(\\d+p)\\]$");

    public static ScrapBeanResponse extracted(String texto) {
        texto = texto.split(":")[0];
        Matcher matcher = patron.matcher(texto);
        ScrapBeanResponse scrapBeanResponse = new ScrapBeanResponse();
        if (matcher.matches()) {
            scrapBeanResponse.setName(matcher.group(1) != null ? matcher.group(1) : "");
            scrapBeanResponse.setSession(Integer.parseInt(matcher.group(2)));
            scrapBeanResponse.setQuality(matcher.group(3).contains("720p") || matcher.group(3).contains("1080p")
                    ? CalidadEnum.HD : CalidadEnum.SD);

        }
        return scrapBeanResponse;
    }

    public static boolean checkPagination(Document doc) {
        if (doc.select("li[class='page-item disabled']").last() != null
                && doc.select("li[class='page-item disabled']").last().text().contains("Siguiente")) {
            return true;
        }
        return false;
    }

    public static String getProxyDonTorrent() {
        try {
            String url = "https://donproxies.com/";
            Document doc = Jsoup.connect(url).get();
            return doc.select("a[title='DonTorrent']").get(0).attr("href");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String normalizeUrl(String href) {
        // return href with / at the beginning
        return href.startsWith("/") ? href : "/" + href;
    }
}
