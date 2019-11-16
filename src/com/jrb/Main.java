package com.jrb;

import org.jsoup.*;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {

    // 1. flyt jsoup til en main method
    // 2. få jsoup til at returnere indholdet af en website, og gem dette i en String webpageContent
    // 3. få jsoup opslaget til at benytte SearchBoxInput i stedet for en statisk værdi
    // 4. få Matcher til at bruge webpageContent som pattern

    public static void main(String[] args) throws IOException {


        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = args[0];
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("Hej \n %s",media.append("PQLsE"));

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

    public class WebpageContent {

        private String wepageContent;


        Document doc = Jsoup.connect("https://bulbapedia.bulbagarden.net/wiki/").get();

        String webpageContent = doc.data();


        // Skal denne exception sættes yderligere op?
        public WebpageContent() throws IOException {
        }

    }



}


