package org.xunmeng.notify;

import com.google.gson.Gson;
import de.codecentric.boot.admin.event.ClientApplicationEvent;
import de.codecentric.boot.admin.notify.AbstractStatusChangeNotifier;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class DingTalkStatusChangeNotifier extends AbstractStatusChangeNotifier {
    private Expression text;
    static Gson gson = new Gson();
    private RestTemplate restTemplate = new RestTemplate();
    private String webhookToken;
    private final SpelExpressionParser parser = new SpelExpressionParser();

    public DingTalkStatusChangeNotifier(String webHookToken){
        this.webhookToken = webHookToken;
        this.text = parser.parseExpression("#{application.name} (#{application.id}) status changed from #{from.status} to #{to.status}  #{application.healthUrl}", ParserContext.TEMPLATE_EXPRESSION);
    }

    @Override
    protected void doNotify(ClientApplicationEvent event) throws Exception {
        EvaluationContext context = new StandardEvaluationContext(event);
        String message = text.getValue(context, String.class);
        this.restTemplate.postForEntity(webhookToken, createMessage(message), Void.class);
    }

    private HttpEntity<String> createMessage(String message) {
        Map<String, Object> messageJson = new HashMap<>();
        Map<String, Object> context = new HashMap<>();
        context.put("content", message);
        messageJson.put("text", gson.toJson(context));
        messageJson.put("msgtype", "text");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<String>(gson.toJson(messageJson), headers);
    }
}
