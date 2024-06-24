package bot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class ChatBotBase {
    private static final String ERROR_MESSAGE = "Chat bot command not found";

    protected SendMessage createSendMessage(long chatId, String messageText) {
        return SendMessage
                .builder()
                .chatId(chatId)
                .text(messageText)
                .build();
    }

    protected SendMessage createErrorMessage(long chatId) {
        return SendMessage
                .builder()
                .chatId(chatId)
                .text("Request failed ❌ Try again later")
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

    protected void addBackCommand(InlineKeyboardButton backButton, SendMessage message) {
        List<InlineKeyboardRow> rowsInline = new ArrayList<>();
        rowsInline.add(new InlineKeyboardRow(backButton));

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup(rowsInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
    }
}
