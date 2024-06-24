package bot.command;

import bot.config.PetrolBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

@Component
public class BackCommand extends ChatBotBase implements Command {
    private static final String command = "callback_back";
    private final List<Command> botCommands;

    public BackCommand(List<Command> botCommands) {
        this.botCommands = botCommands;
    }

    @Override
    public void execute(TelegramClient telegramClient, long chatId) {
        Command command = findCommand(botCommands, "/start");
        command.execute(PetrolBot.telegramClient, PetrolBot.getChatId());
    }

    @Override
    public String getCommand() {
        return command;
    }
}
