package bot.command;

import bot.infrastructure.PetrolRestClient;
import bot.model.CirclePetrolPriceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
@RequiredArgsConstructor
public class CircleLTCommand extends ChatBotBase implements Command {
    private static final String command = "callback_circle_lt";
    private final PetrolRestClient petrolRestClient;
    @Value("${bpp.baseurl}")
    private String baseUrl;

    @Override
    public void execute(TelegramClient telegramClient, long chatId) {
        SendMessage message = createSendMessage(chatId, "Send Request for CircleK petrol price ➡");
        sendMessage(telegramClient, message);

        ResponseEntity<?> response = petrolRestClient.getPetrolPrice(baseUrl + "/lt/petrol/circlek", CirclePetrolPriceModel.class);

        if (response.getStatusCode().isError()) {
            SendMessage errorMessage = createErrorMessage(chatId);
            sendMessage(telegramClient, errorMessage);
            return;
        }

        if (response.getBody() instanceof CirclePetrolPriceModel model) {
            SendMessage petrolMessage = createSendMessage(chatId,
                    String.format("✅ Petrol 95: %s € \n✅ Location: %s",
                            model.getPetrol(),
                            model.getPetrolBestPriceAddress()));
            sendMessage(telegramClient, petrolMessage);

            SendMessage petrolProMessage = createSendMessage(chatId,
                    String.format("✅ Petrol 98: %s € \n✅ Location: %s",
                            model.getPetrolPro(),
                            model.getPetrolProBestPriceAddress()));
            sendMessage(telegramClient, petrolProMessage);

            SendMessage dieselMessage = createSendMessage(chatId,
                    String.format("✅ Diesel: %s € \n✅ Location: %s",
                            model.getDiesel(),
                            model.getDieselBestPriceAddress()));
            sendMessage(telegramClient, dieselMessage);

            SendMessage dieselProMessage = createSendMessage(chatId,
                    String.format("✅ Diesel Pro: %s € \n✅ Location: %s",
                            model.getDieselPro(),
                            model.getDieselProBestPriceAddress()));
            sendMessage(telegramClient, dieselProMessage);

            SendMessage gasMessage = createSendMessage(chatId,
                    String.format("✅ Gas: %s € \n✅ Location: %s",
                            model.getGas(),
                            model.getGasBestPriceAddress()));

            InlineKeyboardButton backButton = createChatBotButton("⬅ Back", "callback_back");

            addBackCommand(backButton, gasMessage);

            sendMessage(telegramClient, gasMessage);
        }
    }

    @Override
    public String getCommand() {
        return command;
    }
}
