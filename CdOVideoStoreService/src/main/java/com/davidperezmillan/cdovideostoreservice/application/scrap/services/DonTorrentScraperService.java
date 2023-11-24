package com.davidperezmillan.cdovideostoreservice.application.scrap.services;

import com.davidperezmillan.cdovideostoreservice.application.scrap.dtos.ScrapBeanResponse;
import com.davidperezmillan.cdovideostoreservice.application.scrap.rules.RulesDonTorrent;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@Service
public class DonTorrentScraperService {

    private String proxyDonTorrent;

    public List<ScrapBeanResponse> getTvShow(String title) {

        List<ScrapBeanResponse> scrapBeansResponse = new ArrayList<ScrapBeanResponse>();
        proxyDonTorrent = RulesDonTorrent.getProxyDonTorrent();
        try {
            for (int i = 1; i <= 30; i++) {
                String url = proxyDonTorrent + "/series/hd/letra-" + title.toLowerCase().charAt(0) + "/page/" + i;
                log.info("Scraping: " + url);

                Document doc = Jsoup.connect(url).get();
                Elements findElements = doc.select("a[href*=/serie/]");
                findElements.forEach(seriesLink -> {
                    ScrapBeanResponse scrapBeanResponse = RulesDonTorrent.extracted(seriesLink.text());
                    if ( scrapBeanResponse.getName() != null  && !scrapBeanResponse.getName().isEmpty()) {
                        scrapBeanResponse.setUrl(RulesDonTorrent.normalizeUrl(seriesLink.attr("href")));
                        scrapBeansResponse.add(scrapBeanResponse);
                    }
                });
                if (RulesDonTorrent.checkPagination(doc))
                    break;
            }
            return scrapBeansResponse;
        } catch (Exception e) {
            log.error("Error scraping: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<ScrapBeanResponse> getPremieres() {
        List<ScrapBeanResponse> scrapBeansResponse = new ArrayList<ScrapBeanResponse>();
        proxyDonTorrent = RulesDonTorrent.getProxyDonTorrent();
        try {
            String url = proxyDonTorrent + "/ultimos";
            log.info("Scraping: " + url);
            Document doc = Jsoup.connect(url).get();
            Element seriesDiv = doc.select("div.h5.text-dark:contains(SERIES:)").first();
            Elements findElements = seriesDiv.nextElementSiblings().select("a[href*=serie/]");
            for (Element element : findElements) {
                ScrapBeanResponse scrapBeanResponse = RulesDonTorrent.extracted(element.text());
                if (scrapBeanResponse != null && scrapBeanResponse.getName() != null
                        && !scrapBeanResponse.getName().isEmpty()) {
                    scrapBeanResponse.setUrl(RulesDonTorrent.normalizeUrl(element.attr("href")));
                    scrapBeansResponse.add(scrapBeanResponse);
                }
            }
            findElements.forEach(seriesLink -> {
                log.debug("Serie: " + seriesLink.text());
                ScrapBeanResponse scrapBeanResponse = RulesDonTorrent.extracted(seriesLink.text());
                if (scrapBeanResponse != null && scrapBeanResponse.getName() != null
                        && !scrapBeanResponse.getName().isEmpty()) {
                    scrapBeanResponse.setUrl(proxyDonTorrent + seriesLink.attr("href"));
                    scrapBeansResponse.add(scrapBeanResponse);
                }
            });
            return scrapBeansResponse;
        } catch (Exception e) {
            log.error("Error scraping: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public List<ScrapBeanResponse> getEpisode(String url) {
        List<ScrapBeanResponse> scrapBeansResponse = new ArrayList<ScrapBeanResponse>();
        proxyDonTorrent = RulesDonTorrent.getProxyDonTorrent();
        try {
            Document doc = Jsoup.connect(proxyDonTorrent + url).get();
            Elements linkElements = doc.select("a[id='download_torrent']");
            linkElements.forEach(linkElement -> {
                ScrapBeanResponse scrapBeanResponse = new ScrapBeanResponse();
                scrapBeanResponse.setUrl("https:" + linkElement.attr("href"));
                scrapBeanResponse.setName(doc.select("h1[class='text-center']").text());
                scrapBeanResponse.setSinopsis(normalizeBlob(doc.select("p[class='text-justify']").text()));
                log.debug("Serie:Sinopsis " + scrapBeanResponse.getSinopsis());
                int[] info = evaluateSessionEpisode(linkElement);
                scrapBeanResponse.setSession(info[0]);
                scrapBeanResponse.setEpisode(info[1]);
                scrapBeansResponse.add(scrapBeanResponse);
            });
            return scrapBeansResponse;
        } catch (Exception e) {
            log.error("Error scraping: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private int[] evaluateSessionEpisode(Element linkElement) {
        log.info("Evaluating session and episode: " + linkElement);
        String text = linkElement.parent().parent().select("td").first().text();
        int[] result = new int[] { 0, 0 };
        Pattern pattern = Pattern.compile("(\\d{1,2})x(\\d{2})");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            result[0] = Integer.parseInt(matcher.group(1).replaceAll("[^0-9]", ""));
            result[1] = Integer.parseInt(matcher.group(2).replaceAll("[^0-9]", ""));
            return result;
        }

        return result;
    }

    private String normalizeBlob(String blob) {
        blob = blob.replaceAll("Descripción:", "");
        blob = blob.replaceAll("Sinopsis:", "");
        blob = blob.trim();
        blob = blob.length() > 10000 ? blob.substring(0, 10000) : blob;
        return blob.replaceAll("\\\\", "");
    }

}
