package util.applitools;

import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.Mutable;

@LoadPolicy(LoadType.MERGE)
@Sources(
{
  "file:config/dev-applitools.properties", "file:config/applitools.properties"
})

public interface ApplitoolsConfiguration extends Mutable
{

    @Key("applitools.api_key")
    String apiKeyFromProps();

    @Key("applitools.project_name")
    String projectName();

    static String apiKey()
    {
        String apiKey = ConfigFactory.create(ApplitoolsConfiguration.class).apiKeyFromProps();
        if (apiKey == null)
        {
            return System.getenv("APPLITOOLS_API_KEY");
        }
        return apiKey;
    }

}
