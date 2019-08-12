package com.airbnb.android.lib.payments.digitalriver;

import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.airbnb.android.core.models.payments.DigitalRiverCreditCard;
import com.airbnb.android.lib.C0880R;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DigitalRiverTokenizer implements DigitalRiverApi {
    private static final String TOKENIZER = "Tokenizer";
    private Context context;
    private final String digitalRiverMerchantId;
    private final String digitalRiverPublicKey;
    private DigitalRiverTokenizerListener listener;
    private ObjectMapper objectMapper;
    private final String scriptFilename;

    public static class EncodableDigitalRiverCreditCard {
        @JsonProperty("cvCode")
        private String cVV;
        @JsonProperty("cardNumber")
        private String cardNumber;
        @JsonProperty("expirationMonth")
        private String expirationMonth;
        @JsonProperty("expirationYear")
        private String expirationYear;
        @JsonProperty("firstName")
        private String firstName;
        @JsonProperty("fullName")
        private String fullName;
        @JsonProperty("lastName")
        private String lastName;

        @JsonCreator
        public EncodableDigitalRiverCreditCard(DigitalRiverCreditCard creditCard, String firstName2, String lastName2) {
            this.cardNumber = creditCard.getCardNumber().replaceAll("\\s+", "");
            this.expirationMonth = creditCard.getExpiryMonth();
            this.expirationYear = getFullExpirationYear(creditCard.getExpiryYear());
            this.cVV = creditCard.getSecurityCode();
            this.fullName = firstName2 + " " + lastName2;
            this.firstName = firstName2;
            this.lastName = lastName2;
        }

        static String getFullExpirationYear(String expirationYear2) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy");
            Calendar calendar = Calendar.getInstance();
            calendar.set(1, calendar.get(1) - 50);
            dateFormat.set2DigitYearStart(calendar.getTime());
            try {
                return new SimpleDateFormat("yyyy").format(dateFormat.parse(expirationYear2));
            } catch (ParseException e) {
                return expirationYear2;
            }
        }
    }

    public DigitalRiverTokenizer(Context context2, ObjectMapper objectMapper2, String scriptFilename2) {
        this.context = context2;
        this.objectMapper = objectMapper2;
        this.scriptFilename = scriptFilename2;
        this.digitalRiverPublicKey = context2.getResources().getString(C0880R.string.digital_river_public_key);
        this.digitalRiverMerchantId = context2.getResources().getString(C0880R.string.digital_river_merchant_id);
    }

    public void tokenize(DigitalRiverCreditCard creditCard, String firstName, String lastName, DigitalRiverTokenizerListener listener2) throws IOException {
        this.listener = listener2;
        EncodableDigitalRiverCreditCard card = new EncodableDigitalRiverCreditCard(creditCard, firstName, lastName);
        WebView webView = new WebView(this.context);
        webView.getSettings().setJavaScriptEnabled(true);
        if (VERSION.SDK_INT >= 19) {
            webView.evaluateJavascript(buildURL(card), DigitalRiverTokenizer$$Lambda$1.lambdaFactory$(this));
            return;
        }
        String url = buildURLForOldAndroidClient(card);
        webView.addJavascriptInterface(this, TOKENIZER);
        webView.loadUrl(url);
    }

    /* access modifiers changed from: 0000 */
    public String trimCsePayload(String csePayload) {
        String trimmedPayload = csePayload;
        String doubleQuotes = "\"";
        if (!csePayload.startsWith(doubleQuotes) || !csePayload.endsWith(doubleQuotes)) {
            return trimmedPayload;
        }
        return csePayload.substring(1, csePayload.length() - 1);
    }

    private String buildURL(EncodableDigitalRiverCreditCard creditCard) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(readFile(this.scriptFilename));
        stringBuilder.append(getJavascriptEncode(creditCard, false));
        return stringBuilder.toString();
    }

    private String buildURLForOldAndroidClient(EncodableDigitalRiverCreditCard creditCard) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("javascript:");
        stringBuilder.append(readFile(this.scriptFilename));
        stringBuilder.append(getJavascriptEncode(creditCard, true));
        stringBuilder.append("Tokenizer.onDigitalRiverCreditCardTokenized(tokenizedCard);");
        return stringBuilder.toString();
    }

    private String readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.context.getAssets().open(filename)));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String nextLine = reader.readLine();
            if (nextLine == null) {
                return stringBuilder.toString();
            }
            stringBuilder.append(nextLine);
        }
    }

    private String getJavascriptEncode(EncodableDigitalRiverCreditCard creditCard, boolean isOldAndroidClient) throws JsonProcessingException {
        StringBuilder stringBuilder = new StringBuilder();
        String serializedCreditCard = this.objectMapper.writeValueAsString(creditCard);
        stringBuilder.append("var paymentForm = drwp.encrypt.createPaymentForm('" + this.digitalRiverPublicKey + "','" + this.digitalRiverMerchantId + "');");
        stringBuilder.append(isOldAndroidClient ? "var tokenizedCard = " : "");
        stringBuilder.append("paymentForm.encryptPaymentForm('" + serializedCreditCard + "');");
        return stringBuilder.toString();
    }

    @JavascriptInterface
    public void onCreditCardTokenized(String tokenizedCard) {
        this.listener.onDigitalRiverCreditCardTokenized(trimCsePayload(tokenizedCard));
    }
}
