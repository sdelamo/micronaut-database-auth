/*
 * Copyright 2025-2025 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.softamo.micronaut.dbauth.i18n;

import io.micronaut.views.fields.messages.Message;

import java.util.Locale;

/**
 * Language representation.
 */
public enum Language {
    ARABIC(new Locale("ar"), Message.of("‫العربية‬", "language.arabic")),
    CHINESE_SIMPLIFIED(Locale.SIMPLIFIED_CHINESE, Message.of("中文(简体)", "language.chinese.simplified")),
    CHINESE_TRADITIONAL(Locale.TRADITIONAL_CHINESE, Message.of("中文 (繁體)", "language.chinese.Traditional")),
    CZECH(new Locale("cs"), Message.of("Čeština", "language.czech")),
    DUTCH(new Locale("nl"), Message.of("Nederlands", "language.dutch")),
    ENGLISH(Locale.ENGLISH, Message.of("English", "language.english")),
    FINNISH(new Locale("fi"), Message.of("suomi", "language.finnish")),
    FRENCH(Locale.FRENCH, Message.of("Français", "language.french")),
    GERMAN(Locale.GERMAN, Message.of("Deutsch", "language.german")),
    ITALIAN(Locale.ITALIAN, Message.of("Italiano", "language.italian")),
    JAPANESE(Locale.JAPANESE, Message.of("日本語", "language.japanese")),
    KOREAN(Locale.KOREAN, Message.of("한국어", "language.korean")),
    POLISH(new Locale("pl"), Message.of("polski", "language.polish")),
    PORTUGUESE_BRAZIL(new Locale("pt", "BR"), Message.of("Português (Brasil)", "language.portuguese.brazil")),
    RUSSIAN(new Locale("ru"), Message.of("русский", "language.russian")),
    SPANISH(new Locale("es"), Message.of("Español", "language.spanish")),
    SWEDISH(new Locale("sv"), Message.of("Svenska", "language.swedish")),
    TURKISH(new Locale("tr"), Message.of("Türkçe", "language.turkish")),
    AFRIKAANS(new Locale("af"), Message.of("Afrikaans", "language.afrikaans")),
    ALBANIAN(new Locale("sq"), Message.of("Shqip", "language.albanian")),
    AMHARIC(new Locale("am"), Message.of("አማርኛ", "language.amharic")),
    ARMENIAN(new Locale("hy"), Message.of("Հայերեն", "language.armenian")),
    ASSAMESE(new Locale("as"), Message.of("অসমীয়া", "language.assamese")),
    AZERBAIJANI_LATIN(new Locale("az"), Message.of("azərbaycan", "language.azerbaijani.latin")),
    BANGLA(new Locale("bn"), Message.of("বাংলা", "language.bangla")),
    BASQUE(new Locale("eu"), Message.of("Euskara", "language.basque")),
    BELARUSIAN(new Locale("be"), Message.of("Беларуская", "language.belarusian")),
    BOSNIAN_LATIN(new Locale("bs"), Message.of("bosanski", "language.bosnian.latin")),
    BULGARIAN(new Locale("bg"), Message.of("Български", "language.bulgarian")),
    CATALAN(new Locale("ca"), Message.of("català", "language.catalan")),
    CHEROKEE(new Locale("chr"), Message.of("ᏣᎳᎩ", "language.cherokee")),
    CROATIAN(new Locale("hr"), Message.of("hrvatski", "language.croatian")),
    DANISH(new Locale("da"), Message.of("dansk", "language.danish")),
    ESTONIAN(new Locale("et"), Message.of("Eesti", "language.estonian")),
    FILIPINO(new Locale("fil"), Message.of("Filipino", "language.filipino")),
    GALICIAN(new Locale("gl"), Message.of("Galego", "language.galician")),
    GEORGIAN(new Locale("ka"), Message.of("ქართული", "language.georgian")),
    GREEK(new Locale("el"), Message.of("Ελληνικά", "language.greek")),
    GUJARATI(new Locale("gu"), Message.of("ગુજરાતી", "language.gujarati")),
    HEBREW(new Locale("he"), Message.of("‫עברית‬", "language.hebrew")),
    HINDI(new Locale("hi"), Message.of("हिन्दी", "language.hindi")),
    HUNGARIAN(new Locale("hu"), Message.of("magyar", "language.hungarian")),
    ICELANDIC(new Locale("is"), Message.of("Íslenska", "language.icelandic")),
    INDONESIAN(new Locale("id"), Message.of("Indonesia", "language.indonesian")),
    IRISH(new Locale("ga"), Message.of("Gaeilge", "language.irish")),
    KANNADA(new Locale("kn"), Message.of("ಕನ್ನಡ", "language.kannada")),
    KAZAKH(new Locale("kk"), Message.of("қазақ тілі", "language.kazakh")),
    KHMER(new Locale("km"), Message.of("ខ្មែរ", "language.khmer")),
    KONKANI(new Locale("kok"), Message.of("कोंकणी", "language.konkani")),
    LAO(new Locale("lo"), Message.of("ລາວ", "language.lao")),
    LATVIAN(new Locale("lv"), Message.of("Latviešu", "language.latvian")),
    LITHUANIAN(new Locale("lt"), Message.of("Lietuvių", "language.lithuanian")),
    LUXEMBOURGISH(new Locale("lb"), Message.of("Lëtzebuergesch", "language.luxembourgish")),
    MACEDONIAN(new Locale("mk"), Message.of("Македонски", "language.macedonian")),
    MALAY(new Locale("ms"), Message.of("Melayu", "language.malay")),
    MALAYALAM(new Locale("ml"), Message.of("മലയാളം", "language.malayala")),
    MALTESE(new Locale("mt"), Message.of("Malti", "language.maltese")),
    MAORI(new Locale("mi"), Message.of("Māori", "language.maori")),
    MARATHI(new Locale("mr"), Message.of("मराठी", "language.marathi")),
    NEPALI(new Locale("ne"), Message.of("नेपाली", "language.nepali")),
    NORWEGIAN_BOKMAL(new Locale("nb"), Message.of("Norwegian Bokmål", "language.norwegian.bokmal")),
    NORWEGIAN_NYNORSK(new Locale("nn"), Message.of("Norwegian Nynorsk", "language.norwegian.nynorsk")),
    ODIA(new Locale("or"), Message.of("ଓଡ଼ିଆ", "language.odia")),
    PERSIAN(new Locale("fa"), Message.of("‫فارسی‬", "language.persian")),
    PORTUGUESE_PORTUGAL(new Locale("pt", "PT"), Message.of("Português (Portugal)", "language.portuguese.portugal")),
    PUNJABI_GURMUKHI(new Locale("pa"), Message.of("ਪੰਜਾਬੀ", "language.punjabi.gurmukhi")),
    QUECHUA(new Locale("qu"), Message.of("Runasimi", "language.quechua")),
    ROMANIAN(new Locale("ro"), Message.of("Română", "language.romanian")),
    SCOTTISH_GAELIC(new Locale("gd"), Message.of("Gàidhlig", "language.scottish.gaelic")),
    SERBIAN_CYRILLIC(new Locale("sr"), Message.of("српски", "language.serbian.cyrillic")),
    SERBIAN_LATIN(new Locale("sr"), Message.of("srpski", "language.serbian.latin")),
    SLOVAK(new Locale("sk"), Message.of("slovenčina", "language.slovak")),
    SLOVENIAN(new Locale("sl"), Message.of("slovenščina", "language.slovenian")),
    TAMIL(new Locale("ta"), Message.of("தமிழ்", "language.tamil")),
    TATAR_CYRILLIC(new Locale("tt"), Message.of("татар", "language.tatar.cyrillic")),
    TELUGU(new Locale("te"), Message.of("తెలుగు", "language.telugu")),
    THAI(new Locale("th"), Message.of("ไทย", "language.thai")),
    UKRAINIAN(new Locale("uk"), Message.of("українська", "language.ukrainian")),
    URDU(new Locale("ur"), Message.of("‫اردو‬", "language.urdu")),
    UYGHUR_ARABIC(new Locale("ug"), Message.of("‫ئۇيغۇرچە (ئەرەب يېزىقىدىكى)‬", "language.uyghur.arabic")),
    UZBEK_LATIN(new Locale("uz"), Message.of("oʻzbekcha", "language.uzbek.latin")),
    VALENCIAN(new Locale("ca"), Message.of("Valencià", "language.valencian")),
    VIETNAMESE(new Locale("vi"), Message.of("Tiếng Việt", "language.vietnamese")),
    WELSH(new Locale("cy"), Message.of("Cymraeg", "language.welsh"));

    private final Locale locale;
    private final Message message;

    Language(Locale locale, Message message) {
        this.locale = locale;
        this.message = message;
    }

    public Locale getLocale() {
        return locale;
    }

    public Message getMessage() {
        return message;
    }
}
