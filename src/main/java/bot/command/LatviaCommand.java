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
public class LatviaCommand extends ChatBotBase implements Command {
    private static final String command = "callback_latvia";

    @Override
    public void execute(TelegramClient telegramClient, long chatId) {
        List<InlineKeyboardRow> rowsInline = new ArrayList<>();

        InlineKeyboardButton circleButton = createChatBotButton("CircleK", "callback_circle_k");
        InlineKeyboardButton nesteButton = createChatBotButton("Neste", "callback_neste");
        InlineKeyboardButton gotikaButton = createChatBotButton("Gotika", "callback_gotika");
        InlineKeyboardButton viadaButton = createChatBotButton("Viada", "callback_viada");
        InlineKeyboardButton virshiButton = createChatBotButton("Virshi", "callback_virshi");
        InlineKeyboardButton backButton = createChatBotButton("⬅\uFE0F Back", "callback_back");

        rowsInline.add(new InlineKeyboardRow(circleButton, nesteButton, gotikaButton));
        rowsInline.add(new InlineKeyboardRow(viadaButton, virshiButton, backButton));

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup(rowsInline);
        markupInline.setKeyboard(rowsInline);

        SendMessage message = createSendMessage(chatId, "Latvian Petrol Stations");
        message.setReplyMarkup(markupInline);

        sendMessage(telegramClient, message);
    }

    @Override
    public String getCommand() {
        return command;
    }
}
