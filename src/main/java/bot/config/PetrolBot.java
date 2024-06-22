package bot.config;

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
public class PetrolBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    private final String token;
    private final TelegramClient telegramClient;
    private final List<Command> botCommands;

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

            Command command = botCommands.stream()
                    .filter(c -> c.getCommand().contains(messageText))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Chat bot command not found"));

            command.execute(telegramClient, chatId);
        }
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
}
