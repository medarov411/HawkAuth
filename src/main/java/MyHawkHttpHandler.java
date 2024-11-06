import burp.api.montoya.http.handler.*;
import burp.api.montoya.http.handler.HttpRequestToBeSent;
import burp.api.montoya.http.message.requests.HttpRequest;
import net.jalg.hawkj.HawkContext;
import net.jalg.hawkj.Algorithm;
import net.jalg.hawkj.AuthorizationHeader;
import net.jalg.hawkj.util.Charsets;


public class MyHawkHttpHandler implements HttpHandler {
    private final String id = "YOUR-VALUE";
    private final String secret = "YOUR-VALUE";

    @Override
    public RequestToBeSentAction handleHttpRequestToBeSent(HttpRequestToBeSent httpRequestToBeSent) {
        // Получаем метод и путь запроса
        String method = httpRequestToBeSent.method();
        String path = httpRequestToBeSent.path();
        String bodyreq = httpRequestToBeSent.bodyToString();
        // Создание контекста Hawk
        HawkContext hawkContext = HawkContext.request(method, path, "YOUR-HOST", 443)
                .credentials(id, secret, Algorithm.SHA_256)
                .body(bodyreq.getBytes(Charsets.UTF_8), "application/json")
                .build();
        // Создание заголовка авторизации
        AuthorizationHeader authHeader = hawkContext.createAuthorizationHeader();
        HttpRequest request = httpRequestToBeSent.withAddedHeader("Authorization", authHeader.toString());

        return RequestToBeSentAction.continueWith(request); // Возвращаем измененный запрос
        }

    @Override
    public ResponseReceivedAction handleHttpResponseReceived(HttpResponseReceived httpResponseReceived) {
        // Здесь можно обработать ответ, если это необходимо
        return null; // Возвращаем null, если не хотим обрабатывать ответ
    }
}
