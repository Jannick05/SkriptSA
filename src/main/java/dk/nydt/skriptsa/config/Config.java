package dk.nydt.skriptsa.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;

@Getter
public class Config extends OkaeriConfig {
    @Comment("Update interval in seconds. Default is 30 seconds.")
    public int updateInterval = 30;
    @Comment("The name of the server. Default is 'Mint'.")
    public String serverName = "Mint";
}
