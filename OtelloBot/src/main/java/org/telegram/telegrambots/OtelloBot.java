package org.telegram.telegrambots;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
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
    	boolean hasMessage = update.hasMessage();
    	long chatId = hasMessage ? update.getMessage().getChatId() : -1;
        if (hasMessage && update.getMessage().hasText()) {
        	Calendar cal = Calendar.getInstance();
        	String messageStr = !((cal.getTimeInMillis() % 5) == 0) ? "You Said: " + update.getMessage().getText() : "Gives a shit";
        	// Create a SendMessage object with mandatory fields
            SendMessage message = new SendMessage().setChatId(chatId).setText(messageStr);
            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (hasMessage && update.getMessage().hasPhoto()) {
            // Message contains photo
            // Set variables

            // Array with photo objects with different sizes
            // We will get the biggest photo from that array
            List<PhotoSize> photos = update.getMessage().getPhoto();
            // Know file_id
            String fId = photos.stream()
                            .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                            .findFirst()
                            .orElse(null).getFileId();
            // Know photo width
            int f_width = photos.stream()
                            .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                            .findFirst()
                            .orElse(null).getWidth();
            // Know photo height
            int f_height = photos.stream()
                            .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                            .findFirst()
                            .orElse(null).getHeight();
            // Set photo caption
            String caption = "file_id: " + fId + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
            SendPhoto msg = new SendPhoto()
                            .setChatId(chatId)
                            .setPhoto(fId)
                            .setCaption(caption);
            try {
                sendPhoto(msg); // Call method to send the photo with caption
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
 
    }
}
