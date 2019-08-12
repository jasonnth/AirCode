package com.airbnb.android.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class PasswordStrength {
    private static final Set<String> COMMON_WORDS = new HashSet(Arrays.asList(new String[]{"!qaz1qaz", "!qaz2wsx", "!qazxsw2", "!qazzaq1", "#edc4rfv", "11111111", "12344321", "12345678", "123456789", "1234567890", "1234abcd", "123qweasd", "12qw!@qw", "1941.salembbb.41", "1a2b3c4d", "1q2w3e4r", "1qaz!qaz", "1qaz@wsx", "1qazxsw2", "1qazxsw@", "1qazzaq!", "2wsx@wsx", "333333333", "3edc#edc", "@wsx2wsx", "a1b2c3d4", "aaliyah1", "abc123abc", "abcabc123", "abcd1234", "abigail1", "access14", "addison1", "adobe123", "airforce1", "alabama1", "alexande", "alexander1", "alexandra", "alexandra1", "allison1", "america1", "anderson1", "angel101", "angel123", "angelina1", "annabelle1", "anthony1", "anthony11", "antonio1", "arianna1", "arsenal1", "arsenal12", "arsenal123", "ashley12", "asshole1", "atlanta1", "august08", "august10", "august12", "august20", "august22", "austin02", "austin316", "australia1", "awesome1", "babyboy1", "babygirl1", "babygurl1", "bailey12", "barcelona1", "baseball", "baseball1", "basketball", "batista1", "beautiful1", "beckham7", "bella123", "benjamin1", "bentley1", "bethany1", "bigdaddy", "bigdaddy1", "blessed1", "blink-182", "blink182", "blondie1", "boricua1", "bradley1", "brandon1", "brandon2", "brandon7", "braxton1", "brayden1", "breanna1", "brianna1", "brittany1", "brittney1", "broncos1", "brooklyn1", "brownie1", "bubbles1", "buddy123", "buttercup1", "butterfly1", "butterfly7", "butthead", "buttons1", "cameron1", "candy123", "carolina", "carolina1", "cassandra1", "catherine1", "celtic1888", "chargers1", "charles1", "charlie1", "charlotte1", "charmed1", "chelsea1", "chelsea123", "chester1", "cheyenne1", "chicago1", "chicken1", "chocolate1", "chopper1", "chris123", "christian1", "christina1", "christine1", "christmas1", "classof08", "clayton1", "cocacola", "college1", "colombia1", "colorado1", "computer", "computer1", "corvette", "courtney1", "cowboys1", "cricket1", "crystal1", "cutiepie1", "daisy123", "dallas22", "dan1elle", "daniela1", "danielle", "danielle1", "david123", "death666", "december1", "december21", "derrick1", "destiny1", "deuseamor", "devil666", "diamond1", "diamonds1", "dolphin1", "dolphins", "dolphins1", "dominic1", "douglas1", "einstein", "elizabeth1", "elizabeth2", "england1", "falcons1", "falcons7", "fernando", "firebird", "florida1", "football", "football1", "forever1", "forever21", "formula1", "frankie1", "freddie1", "freedom1", "friday13", "friends1", "friends2", "fuckoff1", "fuckyou1", "fuckyou2", "gabriel1", "gangsta1", "garrett1", "gateway1", "genesis1", "georgia1", "gerrard8", "giggles1", "goddess1", "godfather", "godislove1", "gordon24", "grandma1", "greenday1", "hamilton", "hardcore", "harrison", "harry123", "hawaii50", "heather1", "hello123", "hershey1", "holiday1", "hollywood1", "honey123", "houston1", "hunter01", "iloveme1", "iloveme2", "iloveyou", "iloveyou1", "iloveyou2", "internet", "internet1", "inuyasha1", "ireland1", "isabella1", "isabelle1", "iverson3", "iydgtvmujl6f", "jackson1", "jackson5", "jamaica1", "james123", "jamesbond", "january1", "january29", "jasmine1", "jazmine1", "jeffrey1", "jehovah1", "jennifer", "jennifer1", "jennifer2", "jeremiah1", "jessica1", "jessica7", "jesus123", "jesus143", "jesus1st", "jesus4me", "jesus777", "jesuscristo", "jesusis#1", "jesusis1", "john3:16", "johncena1", "jonathan1", "jordan01", "jordan12", "jordan23", "joshua01", "justice1", "justin01", "justin11", "justin21", "justin23", "katelyn1", "katherine1", "kathryn1", "katrina1", "kendall1", "kennedy1", "kenneth1", "kimberly", "kimberly1", "kristen1", "kristin1", "l6fkiy9on", "ladybug1", "lakers24", "lampard8", "lasvegas", "laura123", "lebron23", "letmein1", "liberty1", "lindsay1", "lindsey1", "liverp00l", "liverpool1", "liverpool123", "longhorns1", "love4ever", "loveyou2", "lucky123", "m1chelle", "mackenzie1", "madison01", "madison1", "madonna1", "makayla1", "marathon", "marie123", "marines1", "marissa1", "marlboro", "marshall1", "matthew1", "matthew2", "matthew3", "maverick", "maxwell1", "melanie1", "melissa1", "mercedes", "mercedes1", "metallica1", "michael01", "michael07", "michael1", "michael2", "michael7", "micheal1", "michele1", "michelle", "michelle1", "michelle2", "Midnight", "midnight", "midnight1", "miranda1", "mistress", "molly123", "monique1", "monkey01", "monkey12", "monkey13", "monkeys1", "monster1", "montana1", "mountain", "music123", "mustang1", "myspace1", "napoleon", "natalie1", "natasha1", "nathan06", "newyork1", "nicholas", "nicholas1", "nichole1", "nicole12", "nirvana1", "november1", "november11", "november15", "november16", "nursing1", "october1", "october13", "october22", "omarion1", "orlando1", "p4ssword", "p@$$w0rd", "p@55w0rd", "p@ssw0rd", "pa$$w0rd", "pa55w0rd", "pa55word", "panther1", "panthers1", "pass1234", "passion1", "passw0rd", "passw0rd1", "password", "password01", "password1", "password1!", "password11", "password12", "password123", "password13", "password2", "password21", "password3", "password4", "password5", "password7", "password9", "patches1", "patricia1", "patrick1", "pavilion", "peaches1", "peanut01", "peanut11", "pebbles1", "penguin1", "phantom1", "phoenix1", "photoshop", "pickles1", "playboy1", "pokemon1", "poohbear1", "popcorn1", "poseidon", "pr1nc3ss", "pr1ncess", "precious1", "predator", "preston1", "princess", "princess01", "princess07", "princess08", "princess1", "princess12", "princess123", "princess13", "princess15", "princess18", "princess19", "princess2", "princess21", "princess23", "princess24", "princess4", "princess5", "princess7", "prototype1", "pumpkin1", "qwerty123", "qwertyui", "raiders1", "rainbow1", "rangers1", "raymond1", "rebecca1", "rebelde1", "redskins", "redskins1", "redwings", "ricardo1", "richard1", "robert01", "rockstar1", "rocky123", "rockyou1", "ronaldo7", "rush2112", "russell1", "rusty123", "sabrina1", "sail2boat3", "samantha", "samantha1", "santana1", "savannah1", "scooter1", "scorpio1", "scorpion", "scotland1", "scrappy1", "sebastian1", "senior06", "senior07", "september1", "serenity1", "shannon1", "shopping1", "skittles1", "slipknot", "slipknot1", "smokey01", "snickers1", "snowball", "snowball1", "soccer11", "soccer12", "soccer13", "soccer14", "soccer17", "softball1", "spartan117", "special1", "spectrum", "spencer1", "spiderman1", "spongebob1", "srinivas", "start123", "startrek", "starwars", "starwars1", "steelers", "steelers1", "stephanie1", "stephen1", "stranger", "summer01", "summer05", "summer06", "summer07", "summer08", "summer99", "sunshine", "sunshine1", "superman", "superman1", "superstar1", "sweetie1", "sweetpea1", "swimming", "taylor13", "tbfkiy9on", "teddybear1", "testing1", "thesims2", "thirteen13", "thumper1", "thunder1", "tiffany1", "tiger123", "tigger01", "tigger12", "tigger123", "timothy1", "tinkerbell1", "titanic1", "trinity1", "trinity3", "tristan1", "trouble1", "trustno1", "twilight1", "unicorn1", "valerie1", "vampire1", "vanessa1", "vanilla1", "veronica1", "victoria", "victoria1", "vincent1", "welcome1", "welcome123", "welcome2", "whatever", "whatever1", "whitney1", "william1", "winston1", "winter06", "wolfgang", "xxxxxxxx", "yankees1", "yankees2", "z,iyd86i", "zachary1", "zaq!1qaz", "zaq!2wsx", "zaq!xsw2", "zaq1!qaz", "zaq12wsx", "zaq1@wsx", "zaq1zaq!", "zeppelin"}));
    private static final String CONTAINS_LETTER_REGEX = ".*\\p{L}.*";
    private static final String CONTAINS_MULTIPLE_NUMBERS_REGEX = "(.*\\d.*){3,}";
    private static final String CONTAINS_MULTIPLE_SYMBOLS_REGEX = "(.*[!@#$%^&*?_~].*){2,}";
    private static final String CONTAINS_NUMBER_REGEX = ".*\\d.*";
    private static final String CONTAINS_SYMBOL_REGEX = ".*[!@#$%^&*?_~].*";
    private static final String CONTAINS_UPPERCASE_LOWERCASE_REGEX = "(.*\\p{Lu}.*[\\p{L}&&[^\\p{Lu}]].*)|(.*[\\p{L}&&[^\\p{Lu}]].*\\p{Lu}.*)";
    public static final int MIN_GOOD_SCORE = 35;
    private static final int MIN_PASSWORD_SIZE = 6;
    public static final int MIN_STRONG_SCORE = 70;
    private static final String ONLY_CHARACTERS_REGEX = "\\p{L}+";
    private static final String ONLY_NUMBERS_REGEX = "\\d+";
    private Level level;
    private final String password;
    private int score;
    private final String username;

    public enum Level {
        Invalid,
        Weak,
        Good,
        Strong
    }

    private enum StrengthRule {
        PasswordSize,
        Numbers,
        Symbols,
        UppercaseLowercase,
        NumbersChars,
        NumbersSymbols,
        SymbolsChars,
        OnlyChars,
        OnlyNumbers,
        Username,
        Sequences,
        Repetitions
    }

    private PasswordStrength(String username2, String password2) {
        this.username = username2;
        this.password = password2;
        init();
    }

    public static PasswordStrength test(String username2, String password2) {
        return new PasswordStrength(username2, password2);
    }

    public static PasswordStrength test(String password2) {
        return test("", password2);
    }

    public boolean isInvalid() {
        return this.level == Level.Invalid;
    }

    public boolean isWeak() {
        return this.level == Level.Weak;
    }

    public boolean isGood() {
        return this.level == Level.Good || this.level == Level.Strong;
    }

    public boolean isStrong() {
        return this.level == Level.Strong;
    }

    public boolean isValid(Level level2) {
        if (level2 == Level.Strong) {
            return isStrong();
        }
        if (level2 == Level.Good) {
            return isGood();
        }
        return !isInvalid();
    }

    public int getScore() {
        return this.score;
    }

    private void init() {
        this.score = 0;
        if (this.password == null || usesCommonWord()) {
            this.level = Level.Invalid;
            return;
        }
        for (StrengthRule sr : StrengthRule.values()) {
            this.score += scoreFor(sr);
        }
        if (this.score < 0) {
            this.score = 0;
        }
        if (this.score > 100) {
            this.score = 100;
        }
        if (this.score < 35) {
            this.level = Level.Weak;
        }
        if (this.score >= 35) {
            this.level = Level.Good;
        }
        if (this.score >= 70) {
            this.level = Level.Strong;
        }
    }

    private boolean usesCommonWord() {
        return COMMON_WORDS.contains(this.password.toLowerCase());
    }

    private int scoreFor(StrengthRule sr) {
        switch (sr) {
            case PasswordSize:
                if (this.password.length() < 6) {
                    return -100;
                }
                return this.password.length() * 4;
            case Numbers:
                if (Pattern.matches(CONTAINS_MULTIPLE_NUMBERS_REGEX, this.password)) {
                    return 5;
                }
                return 0;
            case Symbols:
                if (Pattern.matches(CONTAINS_MULTIPLE_SYMBOLS_REGEX, this.password)) {
                    return 5;
                }
                return 0;
            case UppercaseLowercase:
                if (Pattern.matches(CONTAINS_UPPERCASE_LOWERCASE_REGEX, this.password)) {
                    return 10;
                }
                return 0;
            case NumbersChars:
                if (!Pattern.matches(CONTAINS_NUMBER_REGEX, this.password) || !Pattern.matches(CONTAINS_LETTER_REGEX, this.password)) {
                    return 0;
                }
                return 15;
            case NumbersSymbols:
                if (!Pattern.matches(CONTAINS_NUMBER_REGEX, this.password) || !Pattern.matches(CONTAINS_SYMBOL_REGEX, this.password)) {
                    return 0;
                }
                return 15;
            case SymbolsChars:
                if (!Pattern.matches(CONTAINS_SYMBOL_REGEX, this.password) || !Pattern.matches(CONTAINS_LETTER_REGEX, this.password)) {
                    return 0;
                }
                return 15;
            case OnlyChars:
                if (Pattern.matches(ONLY_CHARACTERS_REGEX, this.password)) {
                    return -15;
                }
                return 0;
            case OnlyNumbers:
                if (Pattern.matches("\\d+", this.password)) {
                    return -15;
                }
                return 0;
            case Username:
                if (this.password.equals(this.username)) {
                    return -100;
                }
                if (this.username.equals("") || !this.password.toLowerCase().contains(this.username.toLowerCase())) {
                    return 0;
                }
                return -15;
            case Sequences:
                return 0 + (sequences(this.password) * -15) + (sequences(new StringBuilder(this.password).reverse().toString()) * -15);
            case Repetitions:
                return 0 + (-(repetitions(this.password, 2) * 4)) + (-(repetitions(this.password, 3) * 3)) + (-(repetitions(this.password, 4) * 2));
            default:
                return 0;
        }
    }

    private int sequences(String text) {
        int matches = 0;
        int sequenceSize = 0;
        int len = text.length();
        for (int i = 1; i < len; i++) {
            int currentCode = Character.codePointAt(text, i);
            int previousCode = Character.codePointAt(text, i - 1);
            if (currentCode == previousCode + 1 || previousCode == currentCode) {
                sequenceSize++;
            } else {
                sequenceSize = 0;
            }
            if (sequenceSize == 2) {
                matches++;
            }
        }
        return matches;
    }

    private int repetitions(String text, int size) {
        int count = 0;
        Set<String> matches = new HashSet<>();
        int len = text.length();
        int i = 0;
        while (i < len - size) {
            String substring = text.substring(i, i + size);
            int occurrences = 0;
            String tmpText = text;
            if (!matches.contains(substring) && substring.length() >= size) {
                matches.add(substring);
                while (true) {
                    i = tmpText.indexOf(substring);
                    if (i == -1) {
                        break;
                    }
                    occurrences++;
                    tmpText = tmpText.substring(i + 1);
                }
                if (occurrences > 1) {
                    count++;
                }
            }
            i++;
        }
        return count;
    }
}
