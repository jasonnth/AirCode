package com.jumio.commons.validation;

import android.support.p000v4.app.NotificationCompat;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.notifications.NotificationPreferencesGroups;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.lib.adapters.VerificationsAdapter;
import com.braintreepayments.api.models.PayPalRequest;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.places.model.PlaceFields;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.share.internal.ShareConstants;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;
import p005cn.jpush.android.JPushConstants.PushService;

public class DomainValidator implements Serializable {
    private static final String[] COUNTRY_CODE_TLDS = {"ac", "ad", "ae", "af", "ag", "ai", "al", "am", "an", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bm", "bn", "bo", "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "er", "es", "et", "eu", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", JPushReportInterface.f962MY, "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "st", "su", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tp", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "uk", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "xn--3e0b707e", "xn--45brj9c", "xn--80ao21a", "xn--90a3ac", "xn--clchc0ea0b2g2a9gcd", "xn--d1alf", "xn--fiqs8s", "xn--fiqz9s", "xn--fpcrj9c3d", "xn--fzc2c9e2c", "xn--gecrj9c", "xn--h2brj9c", "xn--j1amh", "xn--j6w193g", "xn--kprw13d", "xn--kpry57d", "xn--l1acc", "xn--lgbbat1ad8j", "xn--mgb9awbf", "xn--mgba3a4f16a", "xn--mgbaam7a8h", "xn--mgbayh7gpa", "xn--mgbbh1a71e", "xn--mgbc0a9azcg", "xn--mgberp4a5d4ar", "xn--mgbx4cd0ab", "xn--node", "xn--o3cw4h", "xn--ogbpf8fl", "xn--p1ai", "xn--pgbs0dh", "xn--s9brj9c", "xn--wgbh1c", "xn--wgbl6a", "xn--xkc2al3hye2a", "xn--xkc2dl3a5ee0h", "xn--yfro4i67o", "xn--ygbi2ammx", "ye", "yt", "za", "zm", "zw"};
    private static final String DOMAIN_LABEL_REGEX = "\\p{Alnum}(?>[\\p{Alnum}-]{0,61}\\p{Alnum})?";
    private static final String DOMAIN_NAME_REGEX = "^(?:\\p{Alnum}(?>[\\p{Alnum}-]{0,61}\\p{Alnum})?\\.)+(\\p{Alpha}(?>[\\p{Alnum}-]{0,61}\\p{Alnum})?)\\.?$";
    private static final DomainValidator DOMAIN_VALIDATOR = new DomainValidator(false);
    private static final DomainValidator DOMAIN_VALIDATOR_WITH_LOCAL = new DomainValidator(true);
    private static final String[] GENERIC_TLDS;
    private static final String[] INFRASTRUCTURE_TLDS = {"arpa"};
    private static final String[] LOCAL_TLDS = {"localdomain", "localhost"};
    private static final String TOP_LABEL_REGEX = "\\p{Alpha}(?>[\\p{Alnum}-]{0,61}\\p{Alnum})?";
    private static final long serialVersionUID = -4407125112880174009L;
    private final boolean allowLocal;
    private final RegexValidator domainRegex = new RegexValidator(DOMAIN_NAME_REGEX);
    private final RegexValidator hostnameRegex = new RegexValidator(DOMAIN_LABEL_REGEX);

    private static class IDNHolder {
        /* access modifiers changed from: private */
        public static final Method JAVA_NET_IDN_TO_ASCII = getMethod();

        private IDNHolder() {
        }

        private static Method getMethod() {
            try {
                return Class.forName("java.net.IDN", false, DomainValidator.class.getClassLoader()).getDeclaredMethod("toASCII", new Class[]{String.class});
            } catch (Exception e) {
                return null;
            }
        }
    }

    static {
        String[] strArr = new String[HelpCenterArticle.CURRENCY_EXCHANGE_RATE];
        strArr[0] = "abogado";
        strArr[1] = "academy";
        strArr[2] = "accountants";
        strArr[3] = AppStateModule.APP_STATE_ACTIVE;
        strArr[4] = "actor";
        strArr[5] = "adult";
        strArr[6] = "aero";
        strArr[7] = "agency";
        strArr[8] = "airforce";
        strArr[9] = "allfinanz";
        strArr[10] = "alsace";
        strArr[11] = "amsterdam";
        strArr[12] = "android";
        strArr[13] = "aquarelle";
        strArr[14] = "archi";
        strArr[15] = "army";
        strArr[16] = "arpa";
        strArr[17] = "asia";
        strArr[18] = "associates";
        strArr[19] = "attorney";
        strArr[20] = "auction";
        strArr[21] = "audio";
        strArr[22] = "autos";
        strArr[23] = "axa";
        strArr[24] = "band";
        strArr[25] = "bar";
        strArr[26] = "bargains";
        strArr[27] = "bayern";
        strArr[28] = "beer";
        strArr[29] = "berlin";
        strArr[30] = "best";
        strArr[31] = "bid";
        strArr[32] = "bike";
        strArr[33] = "bio";
        strArr[34] = "biz";
        strArr[35] = "black";
        strArr[36] = "blackfriday";
        strArr[37] = "bloomberg";
        strArr[38] = "blue";
        strArr[39] = "bmw";
        strArr[40] = "bnpparibas";
        strArr[41] = "boo";
        strArr[42] = "boutique";
        strArr[43] = "brussels";
        strArr[44] = "budapest";
        strArr[45] = "build";
        strArr[46] = "builders";
        strArr[47] = "business";
        strArr[48] = "buzz";
        strArr[49] = "bzh";
        strArr[50] = "cab";
        strArr[51] = "cal";
        strArr[52] = "camera";
        strArr[53] = "camp";
        strArr[54] = "cancerresearch";
        strArr[55] = "capetown";
        strArr[56] = "capital";
        strArr[57] = "caravan";
        strArr[58] = "cards";
        strArr[59] = "care";
        strArr[60] = "career";
        strArr[61] = "careers";
        strArr[62] = "cartier";
        strArr[63] = "casa";
        strArr[64] = "cash";
        strArr[65] = "cat";
        strArr[66] = "catering";
        strArr[67] = "center";
        strArr[68] = "ceo";
        strArr[69] = "cern";
        strArr[70] = "channel";
        strArr[71] = "cheap";
        strArr[72] = "christmas";
        strArr[73] = "chrome";
        strArr[74] = "church";
        strArr[75] = "citic";
        strArr[76] = "city";
        strArr[77] = "claims";
        strArr[78] = "cleaning";
        strArr[79] = "click";
        strArr[80] = "clinic";
        strArr[81] = "clothing";
        strArr[82] = "club";
        strArr[83] = "coach";
        strArr[84] = "codes";
        strArr[85] = "coffee";
        strArr[86] = "college";
        strArr[87] = "cologne";
        strArr[88] = "com";
        strArr[89] = "community";
        strArr[90] = "company";
        strArr[91] = "computer";
        strArr[92] = "condos";
        strArr[93] = "construction";
        strArr[94] = "consulting";
        strArr[95] = "contractors";
        strArr[96] = "cooking";
        strArr[97] = "cool";
        strArr[98] = "coop";
        strArr[99] = "country";
        strArr[100] = "credit";
        strArr[101] = "creditcard";
        strArr[102] = "cricket";
        strArr[103] = "crs";
        strArr[104] = "cruises";
        strArr[105] = "cuisinella";
        strArr[106] = "cymru";
        strArr[107] = "dad";
        strArr[108] = "dance";
        strArr[109] = "dating";
        strArr[110] = CohostInvitation.DAY;
        strArr[111] = "deals";
        strArr[112] = "degree";
        strArr[113] = "delivery";
        strArr[114] = "democrat";
        strArr[115] = "dental";
        strArr[116] = "dentist";
        strArr[117] = "desi";
        strArr[118] = "dev";
        strArr[119] = "diamonds";
        strArr[120] = "diet";
        strArr[121] = "digital";
        strArr[122] = RegistrationAnalytics.DIRECT;
        strArr[123] = "directory";
        strArr[124] = "discount";
        strArr[125] = "dnp";
        strArr[126] = "docs";
        strArr[127] = "domains";
        strArr[128] = "doosan";
        strArr[129] = "durban";
        strArr[130] = "dvag";
        strArr[131] = "eat";
        strArr[132] = "edu";
        strArr[133] = "education";
        strArr[134] = "email";
        strArr[135] = "emerck";
        strArr[136] = "energy";
        strArr[137] = "engineer";
        strArr[138] = "engineering";
        strArr[139] = "enterprises";
        strArr[140] = "equipment";
        strArr[141] = "esq";
        strArr[142] = "estate";
        strArr[143] = "eurovision";
        strArr[144] = "eus";
        strArr[145] = "events";
        strArr[146] = "everbank";
        strArr[147] = "exchange";
        strArr[148] = "expert";
        strArr[149] = "exposed";
        strArr[150] = "fail";
        strArr[151] = "farm";
        strArr[152] = "fashion";
        strArr[153] = "feedback";
        strArr[154] = "finance";
        strArr[155] = "financial";
        strArr[156] = "firmdale";
        strArr[157] = "fish";
        strArr[158] = "fishing";
        strArr[159] = "fitness";
        strArr[160] = "flights";
        strArr[161] = "florist";
        strArr[162] = "flowers";
        strArr[163] = "flsmidth";
        strArr[164] = "fly";
        strArr[165] = "foo";
        strArr[166] = "forsale";
        strArr[167] = "foundation";
        strArr[168] = "frl";
        strArr[169] = "frogans";
        strArr[170] = "fund";
        strArr[171] = "furniture";
        strArr[172] = "futbol";
        strArr[173] = "gal";
        strArr[174] = MessagingJitneyLogger.PHOTO_ICON_LOGGING_TAG;
        strArr[175] = "garden";
        strArr[176] = "gbiz";
        strArr[177] = "gent";
        strArr[178] = "ggee";
        strArr[179] = "gift";
        strArr[180] = "gifts";
        strArr[181] = "gives";
        strArr[182] = "glass";
        strArr[183] = "gle";
        strArr[184] = "global";
        strArr[185] = "globo";
        strArr[186] = "gmail";
        strArr[187] = "gmo";
        strArr[188] = "gmx";
        strArr[189] = "google";
        strArr[190] = "gop";
        strArr[191] = "gov";
        strArr[192] = "graphics";
        strArr[193] = "gratis";
        strArr[194] = "green";
        strArr[195] = "gripe";
        strArr[196] = "guide";
        strArr[197] = "guitars";
        strArr[198] = "guru";
        strArr[199] = "hamburg";
        strArr[200] = "haus";
        strArr[201] = "healthcare";
        strArr[202] = AccountVerificationAnalytics.HELP_BUTTON;
        strArr[203] = "here";
        strArr[204] = "hiphop";
        strArr[205] = "hiv";
        strArr[206] = "holdings";
        strArr[207] = "holiday";
        strArr[208] = "homes";
        strArr[209] = "horse";
        strArr[210] = TripRole.ROLE_KEY_HOST;
        strArr[211] = "hosting";
        strArr[212] = "house";
        strArr[213] = "how";
        strArr[214] = "ibm";
        strArr[215] = "immo";
        strArr[216] = "immobilien";
        strArr[217] = "industries";
        strArr[218] = "info";
        strArr[219] = "ing";
        strArr[220] = "ink";
        strArr[221] = "institute";
        strArr[222] = "insure";
        strArr[223] = "int";
        strArr[224] = "international";
        strArr[225] = "investments";
        strArr[226] = "irish";
        strArr[227] = "iwc";
        strArr[228] = "jetzt";
        strArr[229] = "jobs";
        strArr[230] = "joburg";
        strArr[231] = "juegos";
        strArr[232] = "kaufen";
        strArr[233] = "kim";
        strArr[234] = "kitchen";
        strArr[235] = "kiwi";
        strArr[236] = "koeln";
        strArr[237] = "krd";
        strArr[238] = "kred";
        strArr[239] = "lacaixa";
        strArr[240] = "land";
        strArr[241] = "latrobe";
        strArr[242] = "lawyer";
        strArr[243] = "lds";
        strArr[244] = "lease";
        strArr[245] = "legal";
        strArr[246] = "lgbt";
        strArr[247] = "lidl";
        strArr[248] = "life";
        strArr[249] = "lighting";
        strArr[250] = "limited";
        strArr[251] = "limo";
        strArr[252] = "link";
        strArr[253] = "loans";
        strArr[254] = "london";
        strArr[255] = "lotto";
        strArr[256] = "ltda";
        strArr[257] = "luxe";
        strArr[258] = "luxury";
        strArr[259] = "madrid";
        strArr[260] = "maison";
        strArr[261] = "management";
        strArr[262] = "mango";
        strArr[263] = "market";
        strArr[264] = NotificationPreferencesGroups.MARKETING;
        strArr[265] = ShareConstants.WEB_DIALOG_PARAM_MEDIA;
        strArr[266] = "meet";
        strArr[267] = "melbourne";
        strArr[268] = "meme";
        strArr[269] = "memorial";
        strArr[270] = "menu";
        strArr[271] = "miami";
        strArr[272] = "mil";
        strArr[273] = "mini";
        strArr[274] = "mobi";
        strArr[275] = "moda";
        strArr[276] = "moe";
        strArr[277] = "monash";
        strArr[278] = "money";
        strArr[279] = "mormon";
        strArr[280] = "mortgage";
        strArr[281] = "moscow";
        strArr[282] = "motorcycles";
        strArr[283] = "mov";
        strArr[284] = "museum";
        strArr[285] = "nagoya";
        strArr[286] = "name";
        strArr[287] = "navy";
        strArr[288] = "net";
        strArr[289] = "network";
        strArr[290] = "neustar";
        strArr[291] = "new";
        strArr[292] = "nexus";
        strArr[293] = "ngo";
        strArr[294] = "nhk";
        strArr[295] = "ninja";
        strArr[296] = "nra";
        strArr[297] = "nrw";
        strArr[298] = "nyc";
        strArr[299] = "okinawa";
        strArr[300] = "ong";
        strArr[301] = "onl";
        strArr[302] = "ooo";
        strArr[303] = "org";
        strArr[304] = "organic";
        strArr[305] = "osaka";
        strArr[306] = "otsuka";
        strArr[307] = "ovh";
        strArr[308] = "paris";
        strArr[309] = "partners";
        strArr[310] = "parts";
        strArr[311] = "party";
        strArr[312] = "pharmacy";
        strArr[313] = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO;
        strArr[314] = "photography";
        strArr[315] = PlaceFields.PHOTOS_PROFILE;
        strArr[316] = "physio";
        strArr[317] = "pics";
        strArr[318] = "pictures";
        strArr[319] = "pink";
        strArr[320] = "pizza";
        strArr[321] = "place";
        strArr[322] = "plumbing";
        strArr[323] = "pohl";
        strArr[324] = "poker";
        strArr[325] = "porn";
        strArr[326] = "post";
        strArr[327] = "praxi";
        strArr[328] = "press";
        strArr[329] = "pro";
        strArr[330] = "prod";
        strArr[331] = "productions";
        strArr[332] = "prof";
        strArr[333] = "properties";
        strArr[334] = "property";
        strArr[335] = "pub";
        strArr[336] = "qpon";
        strArr[337] = "quebec";
        strArr[338] = "realtor";
        strArr[339] = "recipes";
        strArr[340] = "red";
        strArr[341] = "rehab";
        strArr[342] = "reise";
        strArr[343] = "reisen";
        strArr[344] = "reit";
        strArr[345] = "ren";
        strArr[346] = "rentals";
        strArr[347] = "repair";
        strArr[348] = PushService.PARAM_REPORT;
        strArr[349] = "republican";
        strArr[350] = "rest";
        strArr[351] = "restaurant";
        strArr[352] = VerificationsAdapter.VERIFICATION_REVIEWS;
        strArr[353] = "rich";
        strArr[354] = "rio";
        strArr[355] = "rip";
        strArr[356] = "rocks";
        strArr[357] = "rodeo";
        strArr[358] = "rsvp";
        strArr[359] = "ruhr";
        strArr[360] = "ryukyu";
        strArr[361] = "saarland";
        strArr[362] = PayPalRequest.INTENT_SALE;
        strArr[363] = "samsung";
        strArr[364] = "sarl";
        strArr[365] = "sca";
        strArr[366] = "scb";
        strArr[367] = "schmidt";
        strArr[368] = "schule";
        strArr[369] = "schwarz";
        strArr[370] = "science";
        strArr[371] = "scot";
        strArr[372] = "services";
        strArr[373] = "sew";
        strArr[374] = "sexy";
        strArr[375] = "shiksha";
        strArr[376] = "shoes";
        strArr[377] = "shriram";
        strArr[378] = "singles";
        strArr[379] = "sky";
        strArr[380] = NotificationCompat.CATEGORY_SOCIAL;
        strArr[381] = "software";
        strArr[382] = "sohu";
        strArr[383] = "solar";
        strArr[384] = "solutions";
        strArr[385] = "soy";
        strArr[386] = ListingRequestConstants.JSON_SPACE_KEY;
        strArr[387] = "spiegel";
        strArr[388] = "supplies";
        strArr[389] = "supply";
        strArr[390] = "support";
        strArr[391] = "surf";
        strArr[392] = "surgery";
        strArr[393] = "suzuki";
        strArr[394] = "sydney";
        strArr[395] = "systems";
        strArr[396] = "taipei";
        strArr[397] = "tatar";
        strArr[398] = "tattoo";
        strArr[399] = "tax";
        strArr[400] = "technology";
        strArr[401] = "tel";
        strArr[402] = "tienda";
        strArr[403] = "tips";
        strArr[404] = "tires";
        strArr[405] = "tirol";
        strArr[406] = "today";
        strArr[407] = "tokyo";
        strArr[408] = "tools";
        strArr[409] = ViewProps.TOP;
        strArr[410] = "town";
        strArr[411] = "toys";
        strArr[412] = "trade";
        strArr[413] = "training";
        strArr[414] = "travel";
        strArr[415] = "trust";
        strArr[416] = "tui";
        strArr[417] = "university";
        strArr[418] = "uno";
        strArr[419] = "uol";
        strArr[420] = "vacations";
        strArr[421] = "vegas";
        strArr[422] = "ventures";
        strArr[423] = "versicherung";
        strArr[424] = "vet";
        strArr[425] = "viajes";
        strArr[426] = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO;
        strArr[427] = "villas";
        strArr[428] = "vision";
        strArr[429] = "vlaanderen";
        strArr[430] = "vodka";
        strArr[431] = "vote";
        strArr[432] = "voting";
        strArr[433] = "voto";
        strArr[434] = "voyage";
        strArr[435] = "wales";
        strArr[436] = "wang";
        strArr[437] = "watch";
        strArr[438] = "webcam";
        strArr[439] = PlaceFields.WEBSITE;
        strArr[440] = "wed";
        strArr[441] = "wedding";
        strArr[442] = "whoswho";
        strArr[443] = "wien";
        strArr[444] = "wiki";
        strArr[445] = "williamhill";
        strArr[446] = "wme";
        strArr[447] = "work";
        strArr[448] = "works";
        strArr[449] = "world";
        strArr[450] = "wtc";
        strArr[451] = "wtf";
        strArr[452] = "xn--1qqw23a";
        strArr[453] = "xn--3bst00m";
        strArr[454] = "xn--3ds443g";
        strArr[455] = "xn--45q11c";
        strArr[456] = "xn--4gbrim";
        strArr[457] = "xn--55qw42g";
        strArr[458] = "xn--55qx5d";
        strArr[459] = "xn--6frz82g";
        strArr[460] = "xn--6qq986b3xl";
        strArr[461] = "xn--80adxhks";
        strArr[462] = "xn--80asehdb";
        strArr[463] = "xn--80aswg";
        strArr[464] = "xn--c1avg";
        strArr[465] = "xn--cg4bki";
        strArr[466] = "xn--czr694b";
        strArr[467] = "xn--czrs0t";
        strArr[468] = "xn--czru2d";
        strArr[469] = "xn--d1acj3b";
        strArr[470] = "xn--fiq228c5hs";
        strArr[471] = "xn--fiq64b";
        strArr[472] = "xn--flw351e";
        strArr[473] = "xn--hxt814e";
        strArr[474] = "xn--i1b6b1a6a2e";
        strArr[475] = "xn--io0a7i";
        strArr[476] = "xn--kput3i";
        strArr[477] = "xn--mgbab2bd";
        strArr[478] = "xn--ngbc5azd";
        strArr[479] = "xn--nqv7f";
        strArr[480] = "xn--nqv7fs00ema";
        strArr[481] = "xn--p1acf";
        strArr[482] = "xn--q9jyb4c";
        strArr[483] = "xn--qcka1pmc";
        strArr[484] = "xn--rhqv96g";
        strArr[485] = "xn--ses554g";
        strArr[486] = "xn--unup4y";
        strArr[487] = "xn--vermgensberater-ctb";
        strArr[488] = "xn--vermgensberatung-pwb";
        strArr[489] = "xn--vhquv";
        strArr[490] = "xn--xhq521b";
        strArr[491] = "xn--zfr164b";
        strArr[492] = "xxx";
        strArr[493] = "xyz";
        strArr[494] = "yachts";
        strArr[495] = "yandex";
        strArr[496] = "yoga";
        strArr[497] = "yokohama";
        strArr[498] = "youtube";
        strArr[499] = "zip";
        strArr[500] = "zone";
        strArr[501] = "zuerich";
        GENERIC_TLDS = strArr;
    }

    public static DomainValidator getInstance() {
        return DOMAIN_VALIDATOR;
    }

    public static DomainValidator getInstance(boolean allowLocal2) {
        if (allowLocal2) {
            return DOMAIN_VALIDATOR_WITH_LOCAL;
        }
        return DOMAIN_VALIDATOR;
    }

    private DomainValidator(boolean allowLocal2) {
        this.allowLocal = allowLocal2;
    }

    public boolean isValid(String domain) {
        if (domain == null) {
            return false;
        }
        String domain2 = unicodeToASCII(domain);
        if (domain2.length() > 253) {
            return false;
        }
        String[] groups = this.domainRegex.match(domain2);
        if (groups != null && groups.length > 0) {
            return isValidTld(groups[0]);
        }
        if (!this.allowLocal || !this.hostnameRegex.isValid(domain2)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final boolean isValidDomainSyntax(String domain) {
        if (domain == null) {
            return false;
        }
        String domain2 = unicodeToASCII(domain);
        if (domain2.length() > 253) {
            return false;
        }
        String[] groups = this.domainRegex.match(domain2);
        if ((groups == null || groups.length <= 0) && !this.hostnameRegex.isValid(domain2)) {
            return false;
        }
        return true;
    }

    public boolean isValidTld(String tld) {
        String tld2 = unicodeToASCII(tld);
        if ((!this.allowLocal || !isValidLocalTld(tld2)) && !isValidInfrastructureTld(tld2) && !isValidGenericTld(tld2) && !isValidCountryCodeTld(tld2)) {
            return false;
        }
        return true;
    }

    public boolean isValidInfrastructureTld(String iTld) {
        return Arrays.binarySearch(INFRASTRUCTURE_TLDS, chompLeadingDot(unicodeToASCII(iTld).toLowerCase(Locale.ENGLISH))) >= 0;
    }

    public boolean isValidGenericTld(String gTld) {
        return Arrays.binarySearch(GENERIC_TLDS, chompLeadingDot(unicodeToASCII(gTld).toLowerCase(Locale.ENGLISH))) >= 0;
    }

    public boolean isValidCountryCodeTld(String ccTld) {
        return Arrays.binarySearch(COUNTRY_CODE_TLDS, chompLeadingDot(unicodeToASCII(ccTld).toLowerCase(Locale.ENGLISH))) >= 0;
    }

    public boolean isValidLocalTld(String lTld) {
        return Arrays.binarySearch(LOCAL_TLDS, chompLeadingDot(unicodeToASCII(lTld).toLowerCase(Locale.ENGLISH))) >= 0;
    }

    private String chompLeadingDot(String str) {
        if (str.startsWith(".")) {
            return str.substring(1);
        }
        return str;
    }

    static String unicodeToASCII(String input) {
        try {
            return toASCII(input);
        } catch (IllegalArgumentException e) {
            return input;
        }
    }

    private static final String toASCII(String line) throws IllegalArgumentException {
        if (isOnlyASCII(line)) {
            return line;
        }
        Method m = IDNHolder.JAVA_NET_IDN_TO_ASCII;
        if (m == null) {
            return line;
        }
        try {
            return (String) m.invoke(null, new String[]{line.toLowerCase(Locale.ENGLISH)});
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            Throwable t = e2.getCause();
            if (t instanceof IllegalArgumentException) {
                throw ((IllegalArgumentException) t);
            }
            throw new RuntimeException(e2);
        }
    }

    private static boolean isOnlyASCII(String input) {
        if (input == null) {
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) > 127) {
                return false;
            }
        }
        return true;
    }
}
