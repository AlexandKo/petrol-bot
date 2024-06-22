package bot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Slf4j
public abstract class ChatBotBase {

    protected SendMessage createSendMessage(long chatId, String messageText) {
        return SendMessage
                .builder()
                .chatId(chatId)
                .text(messageText)
                .build();
    }

    protected void sendMessage(TelegramClient telegramClient, SendMessage message) {
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            log.error("Failed to send message to chat ID {}: {}", message.getChatId(), message.getText(), e);
        }
    }
}
