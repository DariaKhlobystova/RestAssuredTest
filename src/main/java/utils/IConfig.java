package utils;

import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:test.properties",
        "classpath:url.properties"})
public interface IConfig extends Config {

    @Key("baseUri")
    String baseUri();

    @Key("timeout")
    String timeout();

    @Key("createBookingPath")
    String createBookingPath();

    @Key("username")
    String username();

    @Key("password")
    String password();
}
