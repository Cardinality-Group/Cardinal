package net.cardinal;

import net.fabricmc.api.*;

import net.cardinal.discord.*;

public class Cardinal implements ModInitializer {

    public final static String name = "Cardinal", version = "1.02";
    public final static Cardinal cardinal = new Cardinal();
    
    public DiscordRP discordRP = new DiscordRP();

    @Override
    public void onInitialize() {
        System.out.println("Starting " + name + " v" + version);

        discordRP.start();
    }

    

}
