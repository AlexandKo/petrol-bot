package bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

public abstract class ChatBotBase {
    private static final String ERROR_MESSAGE = "Chat bot command not found";
    private static final Logger log = LoggerFactory.getLogger(ChatBotBase.class);

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

    protected InlineKeyboardButton createChatBotButton(String text, String callbackData) {
        return InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callbackData)
                .build();
    }

    protected Command findCommand(List<Command> botCommands, String commandName) {
        return botCommands.stream()
                .filter(c -> c.getCommand().contains(commandName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
    }
}
