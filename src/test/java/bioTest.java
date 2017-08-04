/**
 * Created by bing0ne on 03/08/2017.
 */

package de.l3s.boilerpipe.demo;

import java.net.URL;
import java.util.List;

import de.l3s.boilerpipe.BoilerpipeExtractor;
import de.l3s.boilerpipe.document.Media;
import de.l3s.boilerpipe.extractors.CommonExtractors;
import de.l3s.boilerpipe.sax.HtmlArticleExtractor;
import de.l3s.boilerpipe.sax.MediaExtractor;

/**
 * @author manuel.codiga@gmail.com
 */
public final class bioTest {
    public static void main(String[] args) throws Exception {
        URL url = new URL(
                "https://www.bing0ne.com/p/cs229_1/");
        final BoilerpipeExtractor extractor = CommonExtractors.ARTICLE_EXTRACTOR;

        final HtmlArticleExtractor htmlExtr = HtmlArticleExtractor.INSTANCE;

        String html = htmlExtr.process(extractor, url);

        System.out.println(html);

    }
}
