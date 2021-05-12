package pl.minecodes.minediscordstats.storage;

import pl.minecodes.minediscordstats.MineDiscordStats;
import space.arim.dazzleconf.ConfigurationFactory;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.ConfigFormatSyntaxException;
import space.arim.dazzleconf.error.InvalidConfigException;
import space.arim.dazzleconf.ext.snakeyaml.CommentMode;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions;
import space.arim.dazzleconf.helper.ConfigurationHelper;
import space.arim.dazzleconf.sorter.AnnotationBasedSorter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationManager<C> {

    private final ConfigurationHelper<C> configHelper;
    private volatile C configData;

    private ConfigurationManager(ConfigurationHelper<C> configHelper) {
        this.configHelper = configHelper;
    }


    public static <C> ConfigurationManager<C> create(Path configFolder, String fileName, Class<C> configClass) {
        SnakeYamlOptions yamlOptions = new SnakeYamlOptions.Builder()
                .commentMode(CommentMode.alternativeWriter())
                .build();
        ConfigurationFactory<C> configFactory = SnakeYamlConfigurationFactory.create(
                configClass,
                new ConfigurationOptions.Builder().sorter(new AnnotationBasedSorter()).build(),
                yamlOptions);
        return new ConfigurationManager<>(new ConfigurationHelper<>(configFolder, fileName, configFactory));
    }

    public void reloadConfig() {
        Logger logger = MineDiscordStats.getInstance().getLogger();
        try {
            configData = configHelper.reloadConfigData();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);

        } catch (ConfigFormatSyntaxException ex) {
            configData = configHelper.getFactory().loadDefaults();
            logger.log(Level.SEVERE, "The yaml syntax in your configuration is invalid. "
                    + "Check your YAML syntax with a tool such as https://yaml-online-parser.appspot.com/");
            ex.printStackTrace();

        } catch (InvalidConfigException ex) {
            configData = configHelper.getFactory().loadDefaults();
            logger.log(Level.SEVERE, "One of the values in your configuration is not valid. "
                    + "Check to make sure you have specified the right data types.");
            ex.printStackTrace();
        }
    }

    public C getConfigData() {
        C configData = this.configData;
        if (configData == null) {
            throw new IllegalStateException("Configuration has not been loaded yet");
        }
        return configData;
    }

}
