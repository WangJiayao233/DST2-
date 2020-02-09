package cn.edu.zju.crawler;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

public class DrugLabelCrawler extends BaseCrawler {

    private static final Logger log = LoggerFactory.getLogger(DrugLabelCrawler.class);

    public static final String URL_DRUG_LABEL = "https://api.pharmgkb.org/v1/site/labelsByDrug";
    public static final String URL_DRUG_LABEL_DETAIL = "https://api.pharmgkb.org/v1/site/page/drugLabels/%s?view=base";
    private Path drugsPath = new File("drugs.data").toPath();
    private Path drugLabelsPath = new File("drugLabels.data").toPath();

    public void doCrawlerDrug() {
        String content = this.getURLContent(URL_DRUG_LABEL);

        try {
            Files.writeString(drugsPath, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doCrawlerDrugLabel() {
        try {
            if (Files.exists(drugLabelsPath)) {
                try {
                    Files.delete(drugLabelsPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Files.createFile(drugLabelsPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String drugContent = Files.readString(drugsPath);
            Gson gson = new Gson();
            Map drugs = gson.fromJson(drugContent, Map.class);
            List<Map> data = (List<Map>) drugs.get("data");
            data.stream().forEach(x -> {
                log.info("{}", x);
                String id = ((String) ((Map) x.get("drug")).get("id"));

                String content = this.getURLContent(String.format(URL_DRUG_LABEL_DETAIL, id));
                Map result = gson.fromJson(content, Map.class);
                Map map = (Map) result.get("data");
                List<Map> drugLabels = (List<Map>) map.get("drugLabels");
                log.info("Fetch label of drug {}", id);
                drugLabels.stream().forEach(y -> {
                    try {
                        Files.writeString(drugLabelsPath, gson.toJson(y), StandardOpenOption.APPEND);
                        Files.writeString(drugLabelsPath, "\n", StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
