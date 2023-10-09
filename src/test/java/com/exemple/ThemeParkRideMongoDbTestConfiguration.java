package com.exemple;

import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThemeParkRideMongoDbTestConfiguration {
    private static final String IP = "localhost";
    private static final int PORT = 28017; 
    @Bean
    public MongodConfig embeddedMongoConfiguration() throws IOException {
        return MongodConfig.builder()
                .version(Version.V4_0_12)
                .net(new Net(IP, PORT, Network.localhostIsIPv6()))
                .build();
    }
}
