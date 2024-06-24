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
public class SwedenCommand extends ChatBotBase implements Command {
    private static final String command = "callback_se";

    @Override
    public void execute(TelegramClient telegramClient, long chatId) {
        List<InlineKeyboardRow> rowsInline = new ArrayList<>();

        InlineKeyboardButton circleButton = createChatBotButton("⛽ CircleK", "callback_circle_se");
        InlineKeyboardButton backButton = createChatBotButton("⬅ Back", "callback_back");

        rowsInline.add(new InlineKeyboardRow(circleButton));
        rowsInline.add(new InlineKeyboardRow(backButton));

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup(rowsInline);
        markupInline.setKeyboard(rowsInline);

        SendMessage message = createSendMessage(chatId, "Sweden Petrol Stations");
        message.setReplyMarkup(markupInline);

        sendMessage(telegramClient, message);
    }

    @Override
    public String getCommand() {
        return command;
    }
}
