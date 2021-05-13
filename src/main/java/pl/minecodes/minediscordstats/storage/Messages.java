package pl.minecodes.minediscordstats.storage;

import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfHeader;
import space.arim.dazzleconf.annote.SubSection;

@ConfHeader({"mineDiscordStats v1.0", "For help with configuration see our wiki."})
public interface Messages {

    @ConfDefault.DefaultString("&8>> &cUsage: &6/minediscordstats <reload/parse>")
    String helpCommand();

    @ConfDefault.DefaultString("&8>> &6Starting plugin reload, this might take a while...")
    String pendingReload();

    @ConfDefault.DefaultString("&8>> &aReload completed!")
    String reloadCompleted();

    @ConfDefault.DefaultString("&8>> &6Parsed message:")
    String messageParsed();

    @ConfDefault.DefaultString("&8>> &cPlease provide message to parse.")
    String parseMessageRequired();

    @SubSection
    Placeholders placeholders ();

    interface Placeholders {

        @ConfDefault.DefaultString("ONLINE")
        String statusOnline();

        @ConfDefault.DefaultString("OFFLINE")
        String statusOffline();

    }

}
