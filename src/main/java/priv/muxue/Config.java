package priv.muxue;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 */

public enum Config {
    /**
     *
     */
    SYSTEMCONFIG;
    private HashMap<String,String> config = new HashMap<>(10);
    Config(){
        try {
           config=readConfigMap();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
    private HashMap<String,String> readConfigMap() throws Exception{
            String contextPath = System.getProperty("user.dir", "/");
            Properties properties = new Properties();
            Optional<Path> optional = Files.walk(Paths.get(contextPath)).filter((path) -> "config.properties".equalsIgnoreCase(path.getFileName().toString())).findAny();
            Path configPath;
            if (optional.isPresent()) {
                configPath = optional.get();
            } else {
                throw new FileNotFoundException("找不到config.properties");
            }
            try (BufferedInputStream input = new BufferedInputStream(Files.newInputStream(configPath))) {
                properties.load(input);
            }
            HashMap<String,String> map=new HashMap<>(properties.size());
            properties.forEach((k,v)-> map.put(k.toString(),v.toString()));
            return map;
    }

    public Map<String,String> getConfigMap(){
        return Collections.unmodifiableMap(config);
    }
}
