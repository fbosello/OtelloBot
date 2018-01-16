package org.telegram.telegrambots;

import java.util.Calendar;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class OtelloBot extends TelegramLongPollingBot {

    public OtelloBot() {
		super();
	}

	@Override
    public String getBotToken() {
        return "402637317:AAHd95qUdECVmIXHcWfiVUJgLoVzLZJyB_s";
    }
 
    public String getBotUsername() {
        return "@fbosello_bot.";
    }
 
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
        	Calendar cal = Calendar.getInstance();
        	String messageStr = !((cal.getTimeInMillis() % 5) == 0) ? "You Said: " + update.getMessage().getText() : "Gives a shit";
            // Create a SendMessage object with mandatory fields
            SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId()).setText(messageStr);
            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
 
    }
}
