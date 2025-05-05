package com.s4lpicon.configManager;

public class ConfigManeger {
    private String configFilePath;
    private String configFileName;
    private String configFileType;
    private String configFileContent;

    public ConfigManeger(String configFilePath, String configFileName, String configFileType) {
        this.configFilePath = configFilePath;
        this.configFileName = configFileName;
        this.configFileType = configFileType;
        this.configFileContent = loadConfig();
    }

    private String loadConfig() {
        // Logic to load the configuration file
        return "Configuration loaded from " + configFilePath + "/" + configFileName + "." + configFileType;
    }

    public void saveConfig(String content) {
        // Logic to save the configuration file
        this.configFileContent = content;
        System.out.println("Configuration saved: " + content);
    }
}
