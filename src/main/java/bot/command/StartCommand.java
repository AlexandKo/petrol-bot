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

//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardRow> rowsInline = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = InlineKeyboardButton.builder()
                .text("Latvia")
                .callbackData("callback_latvia")
                .build();

        InlineKeyboardButton inlineKeyboardButton2 = InlineKeyboardButton.builder()
                .text("Lithuania")
                .callbackData("callback_lithuania")
                .build();

        rowsInline.add(new InlineKeyboardRow(inlineKeyboardButton1, inlineKeyboardButton2));

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup(rowsInline);
        markupInline.setKeyboard(rowsInline);

        SendMessage message = createSendMessage(chatId, "Welcome");
        message.setReplyMarkup(markupInline);

        sendMessage(telegramClient, message);
    }

    @Override
    public String getCommand() {
        return command;
    }
}
