import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class MyHawkExtension implements BurpExtension {
    @Override
    public void initialize(MontoyaApi api) {
        // Устанавливает имя расширения
        api.extension().setName("Hawk Authentication Extension");
        api.logging().logToOutput("Powered by @undefinxd");
        // Создает экземпляр обработчика HTTP и регистрирует его
        MyHawkHttpHandler handler = new MyHawkHttpHandler();
        api.http().registerHttpHandler(handler);
    }
}
