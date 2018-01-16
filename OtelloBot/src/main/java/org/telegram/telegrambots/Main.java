package org.telegram.telegrambots;

import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {

	public static void main(String[] args) {
        ApiContextInitializer.init();
 
        TelegramBotsApi botsApi = new TelegramBotsApi();
 
        try {
            botsApi.registerBot(new OtelloBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
	
}
