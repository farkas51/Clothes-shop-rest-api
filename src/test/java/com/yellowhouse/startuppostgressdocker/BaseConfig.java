package com.yellowhouse.startuppostgressdocker;


import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface BaseConfig extends Config {
    String serverHostname();

    int serverPort();
}
