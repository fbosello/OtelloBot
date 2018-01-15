package org.telegram.telegrambots;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class OtelloBot extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        return "402637317:AAHd95qUdECVmIXHcWfiVUJgLoVzLZJyB_s";
    }
 
    public String getBotUsername() {
        return "@fbosello_bot.";
    }
 
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Create a SendMessage object with mandatory fields
            SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId())
                    .setText("You Said: " + update.getMessage().getText());
            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
 
    }
 
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