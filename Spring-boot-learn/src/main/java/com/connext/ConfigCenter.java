package com.connext;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:application.properties"})
public interface ConfigCenter extends Config {
    @Key("com.connext.author")
    @DefaultValue("")
    String getAuthor();

    @Key("com.connext.title")
    @DefaultValue("")
    String getTitle();
}
