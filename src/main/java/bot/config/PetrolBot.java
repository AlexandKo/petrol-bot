package bot.config;

import bot.command.ChatBotBase;
import bot.command.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.AfterBotRegistration;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

@Slf4j
@Component
public class PetrolBot extends ChatBotBase implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    private final String token;
    private final List<Command> botCommands;
    public static TelegramClient telegramClient = null;
    private static long chatId;

    public PetrolBot(@Value("${bot.token}") final String token,
                     @Autowired final List<Command> botCommands) {
        this.token = token;
        this.botCommands = botCommands;
        telegramClient = new OkHttpTelegramClient(token);
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            Command command = findCommand(botCommands, messageText);

            executeCommand(chatId, command);
        }
        if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            Command command = findCommand(botCommands, callData);

            executeCommand(chatId, command);
        }
    }

    public static synchronized long getChatId() {
        return chatId;
    }

    @AfterBotRegistration
    public void afterRegistration(BotSession botSession) {
        log.info("Bot running state is: {}", botSession.isRunning());
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    private static synchronized void setChatId(long id) {
        chatId = id;
    }

    private static void executeCommand(long chatId, Command command) {
        setChatId(chatId);
        command.execute(telegramClient, chatId);
    }
}
