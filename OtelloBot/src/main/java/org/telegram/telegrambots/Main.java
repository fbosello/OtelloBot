package org.telegram.telegrambots;

import org.apache.log4j.BasicConfigurator;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {

	
	public static void main(String[] args) {
		BasicConfigurator.configure();
        ApiContextInitializer.init();
 
        TelegramBotsApi botsApi = new TelegramBotsApi();
 
        try {
            botsApi.registerBot(new OtelloBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
	
}
