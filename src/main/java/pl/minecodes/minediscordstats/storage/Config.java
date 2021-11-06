package pl.minecodes.minediscordstats.storage;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfHeader;
import space.arim.dazzleconf.annote.SubSection;
import space.arim.dazzleconf.sorter.AnnotationBasedSorter;

@ConfHeader({"mineDiscordStats v1.0", "For help with configuration see our wiki."})
public interface Config {

    @SubSection
    @AnnotationBasedSorter.Order(10)
    Bot bot();

    @SubSection
    @AnnotationBasedSorter.Order(20)
    Stats stats();

    interface Bot {

        @AnnotationBasedSorter.Order(10)
        @ConfDefault.DefaultString("INSERT-YOUR-BOT-TOKEN")
        @ConfComments({"Insert your bot token here.", "If you don't have one, create new application at https://discord.com/developers/applications"})
        String token();

        @AnnotationBasedSorter.Order(10)
        @ConfDefault.DefaultString("mineDiscordStats")
        @ConfComments("You can set custom bot status here.")
        String status();

    }

    interface Stats {

        @AnnotationBasedSorter.Order(10)
        @ConfDefault.DefaultInteger(300)
        @ConfComments({"How often statistics should be refreshed? Time is provided in seconds.",
                "Avoid going too low or your server performance may hurt. You can also reach Discord's rate limit."})
        int refreshTime();

    }
}
