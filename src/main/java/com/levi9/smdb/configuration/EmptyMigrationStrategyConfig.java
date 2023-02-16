package com.levi9.smdb.configuration;

//import lombok.extern.log4j.Log4j2;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Log4j2
//@Configuration
//@ConditionalOnProperty(
//        value="smdb.db.enable-runtime-migrations",
//        havingValue = "true",
//        matchIfMissing = true
//)
//public class EmptyMigrationStrategyConfig {
//
//    @Bean
//    public FlywayMigrationStrategy flywayMigrationStrategy() {
//        return flyway -> log.info("flyway empty migration strategy");
//    }
//}