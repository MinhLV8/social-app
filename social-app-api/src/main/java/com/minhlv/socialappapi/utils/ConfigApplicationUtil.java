package com.minhlv.socialappapi.utils;

import java.io.InputStream;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import lombok.Getter;

@Configuration
@Getter
public class ConfigApplicationUtil {

    private JSONObject value;

    public ConfigApplicationUtil() {
        InputStream prodYml = null;
        try {
            prodYml = Utils.getInputStreamFromClassPath(Constants.YML_PATH_CONFIG_EXTEND);
            if (prodYml == null) {
                prodYml = Utils.getInputStreamFromClassPath(Constants.YML_PATH_CONFIG);
            }
            Yaml yaml = new Yaml();
            Map<String, String> configurations = yaml.load(prodYml);
            this.value = new JSONObject(configurations);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
