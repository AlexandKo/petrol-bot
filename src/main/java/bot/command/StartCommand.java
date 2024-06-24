package bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartCommand extends ChatBotBase implements Command {
    private static final String command = "/start";

    @Override
    public void execute(TelegramClient telegramClient, long chatId) {
        List<InlineKeyboardRow> rowsInline = new ArrayList<>();

        InlineKeyboardButton latviaButton = createChatBotButton("\uD83C\uDDF1\uD83C\uDDFB Latvia", "callback_lv");
        InlineKeyboardButton lithuaniaButton = createChatBotButton("\uD83C\uDDF1\uD83C\uDDF9 Lithuania", "callback_lt");
        InlineKeyboardButton swedenButton = createChatBotButton("\uD83C\uDDF8\uD83C\uDDEA Sweden", "callback_se");

        rowsInline.add(new InlineKeyboardRow(latviaButton, lithuaniaButton, swedenButton));

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup(rowsInline);
        markupInline.setKeyboard(rowsInline);

        SendMessage message = createSendMessage(chatId, "Welcome to Baltic Petrol");
        message.setReplyMarkup(markupInline);

        sendMessage(telegramClient, message);
    }

    @Override
    public String getCommand() {
        return command;
    }
}
