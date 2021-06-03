package net.cardinal.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class DiscordRP {
    
	private boolean running = true;
	private long created = 0;
	
	
	//Hooked to run on client startup
	public void start() {
		
		//Records the startup time
		this.created = System.currentTimeMillis();
		
		//Builds a new discord event handler
		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
			
			//Displays a welcome message in the console
			@Override
			public void apply(DiscordUser user) {
				System.out.println("Welcome " + user.username + "#" + user.discriminator + ".");
				
			}
		}).build();
		
		//Starts up the RPC
		DiscordRPC.discordInitialize("848034531419488296", handlers, true);
		
		new Thread("Discord RPC Callback") {
			
			@Override
			public void run() {
				while (running) {
					DiscordRPC.discordRunCallbacks();
				}
			}
		}.start();

		//Todo: Implement more mixins
		
		DiscordRichPresence.Builder b = new DiscordRichPresence.Builder("Cardinal Client");
		b.setBigImage("large", "");
		b.setStartTimestamps(created);
		DiscordRPC.discordUpdatePresence(b.build());

	}
}