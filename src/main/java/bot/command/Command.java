package bot.command;

import org.telegram.telegrambots.meta.generics.TelegramClient;

public interface Command {
    void execute(TelegramClient telegramClient, long chatId);
    String getCommand();
}
