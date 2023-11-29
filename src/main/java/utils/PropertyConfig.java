package utils;

import org.aeonbits.owner.ConfigCache;

public class PropertyConfig {
    public static IConfig getConfig(){
        return ConfigCache.getOrCreate(IConfig.class);
    }

    public static void main(String[] args) {
        System.out.println(PropertyConfig.getConfig().timeout());
    }

}
