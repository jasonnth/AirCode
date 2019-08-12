package net.p318sf.scuba.data;

import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.utils.AirbnbConstants;
import com.jumio.analytics.MobileEvents;
import com.jumio.p311nv.models.ServerSettingsModel;
import com.miteksystems.misnap.params.MiSnapApiConstants;
import java.io.Serializable;
import org.jmrtd.lds.LDSFile;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.service.StatusCode;

/* renamed from: net.sf.scuba.data.ISOCountry */
public class ISOCountry extends Country implements Serializable {

    /* renamed from: AD */
    public static final Country f5809AD = new ISOCountry(32, "AD", "AND", "Andorra", "Andorran");

    /* renamed from: AE */
    public static final Country f5810AE = new ISOCountry(1924, "AE", "ARE", "United Arab Emirates", "Emirati, Emirian");

    /* renamed from: AF */
    public static final Country f5811AF = new ISOCountry(4, "AF", "AFG", "Afghanistan", "Afghan");

    /* renamed from: AG */
    public static final Country f5812AG = new ISOCountry(40, "AG", "ATG", "Antigua and Barbuda", "Antiguan, Barbudan");

    /* renamed from: AI */
    public static final Country f5813AI = new ISOCountry(1632, "AI", "AIA", "Anguilla", "Anguillan");

    /* renamed from: AL */
    public static final Country f5814AL = new ISOCountry(8, "AL", "ALB", "Albania", "Albanian");

    /* renamed from: AM */
    public static final Country f5815AM = new ISOCountry(81, "AM", "ARM", "Armenia", "Armenian");

    /* renamed from: AN */
    public static final Country f5816AN = new ISOCountry(1328, "AN", "ANT", "Netherlands Antilles", "Antillean");

    /* renamed from: AO */
    public static final Country f5817AO = new ISOCountry(36, "AO", "AGO", "Angola", "Angolan");

    /* renamed from: AQ */
    public static final Country f5818AQ = new ISOCountry(16, "AQ", "ATA", "Antarctica", "Antarctic");

    /* renamed from: AR */
    public static final Country f5819AR = new ISOCountry(50, "AR", "ARG", "Argentina", "Argentine, Argentinean, Argentinian");

    /* renamed from: AS */
    public static final Country f5820AS = new ISOCountry(22, "AS", "ASM", "American Samoa", "American Samoan");

    /* renamed from: AT */
    public static final Country f5821AT = new ISOCountry(64, "AT", "AUT", "Austria", "Austrian");

    /* renamed from: AU */
    public static final Country f5822AU = new ISOCountry(54, AirbnbConstants.COUNTRY_CODE_AUSTRALIA, "AUS", "Australia", "Australian");

    /* renamed from: AW */
    public static final Country f5823AW = new ISOCountry(1331, "AW", "ABW", "Aruba", "Aruban");

    /* renamed from: AX */
    public static final Country f5824AX = new ISOCountry(584, "AX", "ALA", "Aland Islands");

    /* renamed from: AZ */
    public static final Country f5825AZ = new ISOCountry(49, "AZ", "AZE", "Azerbaijan", "Azerbaijani, Azeri");

    /* renamed from: BA */
    public static final Country f5826BA = new ISOCountry(112, "BA", "BIH", "Bosnia and Herzegovina", "Bosnian, Bosniak, Herzegovinian");

    /* renamed from: BB */
    public static final Country f5827BB = new ISOCountry(82, "BB", "BRB", "Barbados", "Barbadian");

    /* renamed from: BD */
    public static final Country f5828BD = new ISOCountry(80, "BD", "BGD", "Bangladesh", "Bangladeshi");

    /* renamed from: BE */
    public static final Country f5829BE = new ISOCountry(86, "BE", "BEL", "Belgium", "Belgian");

    /* renamed from: BF */
    public static final Country f5830BF = new ISOCountry(2132, "BF", "BFA", "Burkina Faso", "Burkinabe");

    /* renamed from: BG */
    public static final Country f5831BG = new ISOCountry(256, "BG", "BGR", "Bulgaria", "Bulgarian");

    /* renamed from: BH */
    public static final Country f5832BH = new ISOCountry(72, "BH", "BHR", "Bahrain", "Bahraini");

    /* renamed from: BI */
    public static final Country f5833BI = new ISOCountry(264, "BI", "BDI", "Burundi", "Burundian");

    /* renamed from: BJ */
    public static final Country f5834BJ = new ISOCountry(516, "BJ", "BEN", "Benin", "Beninese");

    /* renamed from: BL */
    public static final Country f5835BL = new ISOCountry(1618, "BL", "BLM", "Saint Barthlemy");

    /* renamed from: BM */
    public static final Country f5836BM = new ISOCountry(96, "BM", "BMU", "Bermuda", "Bermudian, Bermudan");

    /* renamed from: BN */
    public static final Country f5837BN = new ISOCountry(150, "BN", "BRN", "Brunei Darussalam", "Bruneian");

    /* renamed from: BO */
    public static final Country f5838BO = new ISOCountry(104, "BO", "BOL", "Bolivia", "Bolivian");

    /* renamed from: BR */
    public static final Country f5839BR = new ISOCountry(LDSFile.EF_DG4_TAG, AirbnbConstants.COUNTRY_CODE_BRAZIL, "BRA", "Brazil", "Brazilian");

    /* renamed from: BS */
    public static final Country f5840BS = new ISOCountry(68, "BS", "BHS", "Bahamas", "Bahamian");

    /* renamed from: BT */
    public static final Country f5841BT = new ISOCountry(100, "BT", "BTN", "Bhutan", "Bhutanese");

    /* renamed from: BV */
    public static final Country f5842BV = new ISOCountry(116, "BV", "BVT", "Bouvet Island");

    /* renamed from: BW */
    public static final Country f5843BW = new ISOCountry(114, "BW", "BWA", "Botswana", "Botswanan");

    /* renamed from: BY */
    public static final Country f5844BY = new ISOCountry(TiffUtil.TIFF_TAG_ORIENTATION, "BY", "BLR", "Belarus", "Belarusian");

    /* renamed from: BZ */
    public static final Country f5845BZ = new ISOCountry(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, "BZ", "BLZ", "Belize", "Belizean");

    /* renamed from: CA */
    public static final Country f5846CA = new ISOCountry(292, "CA", "CAN", "Canada", "Canadian");

    /* renamed from: CC */
    public static final Country f5847CC = new ISOCountry(358, "CC", "CCK", "Cocos (Keeling) Islands");

    /* renamed from: CD */
    public static final Country f5848CD = new ISOCountry(384, "CD", "COD", "Congo the Democratic Republic of the", "Congolese");

    /* renamed from: CF */
    public static final Country f5849CF = new ISOCountry(320, "CF", "CAF", "Central African Republic", "Central African");

    /* renamed from: CG */
    public static final Country f5850CG = new ISOCountry(HelpCenterArticle.LEGAL_ISSUES, "CG", "COG", "Congo", "Congolese");

    /* renamed from: CH */
    public static final Country f5851CH = new ISOCountry(1878, "CH", "CHE", "Switzerland", "Swiss");

    /* renamed from: CI */
    public static final Country f5852CI = new ISOCountry(900, "CI", "CIV", "Cote d'Ivoire", "Ivorian");

    /* renamed from: CK */
    public static final Country f5853CK = new ISOCountry(388, "CK", "COK", "Cook Islands");

    /* renamed from: CL */
    public static final Country f5854CL = new ISOCountry(338, "CL", "CHL", "Chile", "Chilean");

    /* renamed from: CM */
    public static final Country f5855CM = new ISOCountry(288, "CM", "CMR", "Cameroon", "Cameroonian");

    /* renamed from: CN */
    public static final Country f5856CN = new ISOCountry(342, AirbnbConstants.COUNTRY_CODE_CHINA, "CHN", "China", "Chinese");

    /* renamed from: CO */
    public static final Country f5857CO = new ISOCountry(368, "CO", "COL", "Colombia", "Colombian");

    /* renamed from: CR */
    public static final Country f5858CR = new ISOCountry(392, "CR", "CRI", "Costa Rica", "Costa Rican");

    /* renamed from: CU */
    public static final Country f5859CU = new ISOCountry(402, AirbnbConstants.COUNTRY_CODE_CUBA, "CUB", "Cuba", "Cuban");

    /* renamed from: CV */
    public static final Country f5860CV = new ISOCountry(MobileEvents.EVENTTYPE_SDKPARAMETERS, "CV", "CPV", "Cape Verde", "Cape Verdean");

    /* renamed from: CX */
    public static final Country f5861CX = new ISOCountry(354, "CX", "CXR", "Christmas Island");

    /* renamed from: CY */
    public static final Country f5862CY = new ISOCountry(406, "CY", "CYP", "Cyprus", "Cypriot");

    /* renamed from: CZ */
    public static final Country f5863CZ = new ISOCountry(515, "CZ", "CZE", "Czech Republic", "Czech");

    /* renamed from: DE */
    public static final Country f5864DE = new ISOCountry(630, AirbnbConstants.COUNTRY_CODE_GERMANY, ServerSettingsModel.GERMAN_ISO3, "Germany", "German");

    /* renamed from: DJ */
    public static final Country f5865DJ = new ISOCountry(610, "DJ", "DJI", "Djibouti", "Djiboutian");

    /* renamed from: DK */
    public static final Country f5866DK = new ISOCountry(520, "DK", "DNK", "Denmark", "Danish");

    /* renamed from: DM */
    public static final Country f5867DM = new ISOCountry(530, "DM", "DMA", "Dominica", "Dominican");

    /* renamed from: DO */
    public static final Country f5868DO = new ISOCountry(532, "DO", "DOM", "Dominican Republic", "Dominican");

    /* renamed from: DZ */
    public static final Country f5869DZ = new ISOCountry(18, "DZ", "DZA", "Algeria", "Algerian");

    /* renamed from: EC */
    public static final Country f5870EC = new ISOCountry(536, "EC", "ECU", "Ecuador", "Ecuadorian");

    /* renamed from: EE */
    public static final Country f5871EE = new ISOCountry(563, "EE", "EST", "Estonia", "Estonian");

    /* renamed from: EG */
    public static final Country f5872EG = new ISOCountry(2072, "EG", "EGY", "Egypt", "Egyptian");

    /* renamed from: EH */
    public static final Country f5873EH = new ISOCountry(1842, "EH", "ESH", "Western Sahara", "Sahraw, Sahrawian, Sahraouian");

    /* renamed from: ER */
    public static final Country f5874ER = new ISOCountry(562, "ER", "ERI", "Eritrea", "Eritrean");

    /* renamed from: ES */
    public static final Country f5875ES = new ISOCountry(1828, "ES", "ESP", "Spain", "Spanish");

    /* renamed from: ET */
    public static final Country f5876ET = new ISOCountry(561, "ET", "ETH", "Ethiopia", "Ethiopian");

    /* renamed from: FI */
    public static final Country f5877FI = new ISOCountry(582, "FI", "FIN", "Finland", "Finnish");

    /* renamed from: FJ */
    public static final Country f5878FJ = new ISOCountry(578, "FJ", "FJI", "Fiji", "Fijian");

    /* renamed from: FK */
    public static final Country f5879FK = new ISOCountry(568, "FK", "FLK", "Falkland Islands (Malvinas)");

    /* renamed from: FM */
    public static final Country f5880FM = new ISOCountry(1411, "FM", "FSM", "Micronesia Federated States of", "Micronesian");

    /* renamed from: FO */
    public static final Country f5881FO = new ISOCountry(564, "FO", "FRO", "Faroe Islands", "Faroese");

    /* renamed from: FR */
    public static final Country f5882FR = new ISOCountry(592, "FR", ServerSettingsModel.FRANCE_ISO3, "France", "French");

    /* renamed from: GA */
    public static final Country f5883GA = new ISOCountry(614, "GA", "GAB", "Gabon", "Gabonese");

    /* renamed from: GB */
    public static final Country f5884GB = new ISOCountry(2086, "GB", "GBR", "United Kingdom", "British");

    /* renamed from: GD */
    public static final Country f5885GD = new ISOCountry(776, "GD", "GRD", "Grenada", "Grenadian");

    /* renamed from: GE */
    public static final Country f5886GE = new ISOCountry(616, "GE", "GEO", "Georgia", "Georgian");

    /* renamed from: GF */
    public static final Country f5887GF = new ISOCountry(596, "GF", "GUF", "French Guiana", "French Guianese");

    /* renamed from: GG */
    public static final Country f5888GG = new ISOCountry(2097, "GG", "GGY", "Guernsey");

    /* renamed from: GH */
    public static final Country f5889GH = new ISOCountry(648, "GH", "GHA", "Ghana", "Ghanaian");

    /* renamed from: GI */
    public static final Country f5890GI = new ISOCountry(658, "GI", "GIB", "Gibraltar");

    /* renamed from: GL */
    public static final Country f5891GL = new ISOCountry(772, "GL", "GRL", "Greenland", "Greenlandic");

    /* renamed from: GM */
    public static final Country f5892GM = new ISOCountry(624, "GM", "GMB", "Gambia", "Gambian");

    /* renamed from: GN */
    public static final Country f5893GN = new ISOCountry(804, "GN", "GIN", "Guinea", "Guinean");

    /* renamed from: GP */
    public static final Country f5894GP = new ISOCountry(786, "GP", "GLP", "Guadeloupe");

    /* renamed from: GQ */
    public static final Country f5895GQ = new ISOCountry(550, "GQ", "GNQ", "Equatorial Guinea", "Equatorial Guinean, Equatoguinean");

    /* renamed from: GR */
    public static final Country f5896GR = new ISOCountry(768, "GR", "GRC", "Greece", "Greek, Hellenic");

    /* renamed from: GS */
    public static final Country f5897GS = new ISOCountry(569, "GS", "SGS", "South Georgia and the South Sandwich Islands");

    /* renamed from: GT */
    public static final Country f5898GT = new ISOCountry(800, "GT", "GTM", "Guatemala", "Guatemalan");

    /* renamed from: GU */
    public static final Country f5899GU = new ISOCountry(790, "GU", "GUM", "Guam", "Guamanian");

    /* renamed from: GW */
    public static final Country f5900GW = new ISOCountry(1572, "GW", "GNB", "Guinea-Bissau", "Guinean");

    /* renamed from: GY */
    public static final Country f5901GY = new ISOCountry(808, "GY", "GUY", "Guyana", "Guyanese");

    /* renamed from: HK */
    public static final Country f5902HK = new ISOCountry(836, "HK", "HKG", "Hong Kong", "Hong Kong, Hongkongese");

    /* renamed from: HM */
    public static final Country f5903HM = new ISOCountry(MiSnapApiConstants.DEFAULT_MAX_BRIGHTNESS, "HM", "HMD", "Heard Island and McDonald Islands");

    /* renamed from: HN */
    public static final Country f5904HN = new ISOCountry(832, "HN", "HND", "Honduras", "Honduran");

    /* renamed from: HR */
    public static final Country f5905HR = new ISOCountry(401, "HR", "HRV", "Croatia", "Croatian");

    /* renamed from: HT */
    public static final Country f5906HT = new ISOCountry(818, "HT", "HTI", "Haiti", "Haitian");

    /* renamed from: HU */
    public static final Country f5907HU = new ISOCountry(840, "HU", "HUN", "Hungary", "Hungarian");

    /* renamed from: ID */
    public static final Country f5908ID = new ISOCountry(864, "ID", "IDN", "Indonesia", "Indonesian");

    /* renamed from: IE */
    public static final Country f5909IE = new ISOCountry(882, "IE", "IRL", "Ireland", "Irish");

    /* renamed from: IL */
    public static final Country f5910IL = new ISOCountry(886, "IL", "ISR", "Israel", "Israeli");

    /* renamed from: IM */
    public static final Country f5911IM = new ISOCountry(2099, "IM", "IMN", "Isle of Man", "Manx");

    /* renamed from: IN */
    public static final Country f5912IN = new ISOCountry(854, AirbnbConstants.COUNTRY_CODE_INDIA, "IND", "India", "Indian");

    /* renamed from: IO */
    public static final Country f5913IO = new ISOCountry(134, "IO", "IOT", "British Indian Ocean Territory");

    /* renamed from: IQ */
    public static final Country f5914IQ = new ISOCountry(872, "IQ", "IRQ", "Iraq", "Iraqi");

    /* renamed from: IR */
    public static final Country f5915IR = new ISOCountry(868, "IR", "IRN", "Iran Islamic Republic of", "Iranian, Persian");

    /* renamed from: IS */
    public static final Country f5916IS = new ISOCountry(MiSnapApiConstants.DEFAULT_CORNER_CONFIDENCE_DRIVER_LICENSE, "IS", "ISL", "Iceland", "Icelandic");

    /* renamed from: IT */
    public static final Country f5917IT = new ISOCountry(896, "IT", "ITA", "Italy", "Italian");

    /* renamed from: JE */
    public static final Country f5918JE = new ISOCountry(2098, "JE", "JEY", "Jersey");

    /* renamed from: JM */
    public static final Country f5919JM = new ISOCountry(904, "JM", "JAM", "Jamaica", "Jamaican");

    /* renamed from: JO */
    public static final Country f5920JO = new ISOCountry(1024, "JO", "JOR", "Jordan", "Jordanian");

    /* renamed from: JP */
    public static final Country f5921JP = new ISOCountry(914, AirbnbConstants.COUNTRY_CODE_JAPAN, "JPN", "Japan", "Japanese");

    /* renamed from: KE */
    public static final Country f5922KE = new ISOCountry(StatusCode.RESULT_TYPE_NOTIFACTION_OPENED, "KE", "KEN", "Kenya", "Kenyan");

    /* renamed from: KG */
    public static final Country f5923KG = new ISOCountry(1047, "KG", "KGZ", "Kyrgyzstan", "Kyrgyz");

    /* renamed from: KH */
    public static final Country f5924KH = new ISOCountry(278, "KH", "KHM", "Cambodia", "Cambodian");

    /* renamed from: KI */
    public static final Country f5925KI = new ISOCountry(662, "KI", "KIR", "Kiribati", "I-Kiribati");

    /* renamed from: KM */
    public static final Country f5926KM = new ISOCountry(372, "KM", "COM", "Comoros", "Comorian");

    /* renamed from: KN */
    public static final Country f5927KN = new ISOCountry(1625, "KN", "KNA", "Saint Kitts and Nevis");

    /* renamed from: KP */
    public static final Country f5928KP = new ISOCountry(StatusCode.RESULT_TYPE_RESUME_PUSH, "KP", "PRK", "Korea Democratic People's Republic of", "North Korean");

    /* renamed from: KR */
    public static final Country f5929KR = new ISOCountry(1040, AirbnbConstants.COUNTRY_CODE_KOREA, "KOR", "Korea Republic of", "South Korean");

    /* renamed from: KW */
    public static final Country f5930KW = new ISOCountry(1044, "KW", "KWT", "Kuwait", "Kuwaiti");

    /* renamed from: KY */
    public static final Country f5931KY = new ISOCountry(310, "KY", "CYM", "Cayman Islands", "Caymanian");

    /* renamed from: KZ */
    public static final Country f5932KZ = new ISOCountry(MiSnapApiConstants.DEFAULT_MICR_CONFIDENCE_FOR_PASSPORT, "KZ", "KAZ", "Kazakhstan", "Kazakh");

    /* renamed from: LA */
    public static final Country f5933LA = new ISOCountry(1048, "LA", "LAO", "Lao People's Democratic Republic", "Lao");

    /* renamed from: LB */
    public static final Country f5934LB = new ISOCountry(1058, "LB", "LBN", "Lebanon", "Lebanese");

    /* renamed from: LC */
    public static final Country f5935LC = new ISOCountry(1634, "LC", "LCA", "Saint Lucia", "Saint Lucian");

    /* renamed from: LI */
    public static final Country f5936LI = new ISOCountry(1080, "LI", "LIE", "Liechtenstein");

    /* renamed from: LK */
    public static final Country f5937LK = new ISOCountry(324, "LK", "LKA", "Sri Lanka", "Sri Lankan");

    /* renamed from: LR */
    public static final Country f5938LR = new ISOCountry(1072, "LR", "LBR", "Liberia", "Liberian");

    /* renamed from: LS */
    public static final Country f5939LS = new ISOCountry(1062, "LS", "LSO", "Lesotho", "Basotho");

    /* renamed from: LT */
    public static final Country f5940LT = new ISOCountry(1088, "LT", "LTU", "Lithuania", "Lithuanian");

    /* renamed from: LU */
    public static final Country f5941LU = new ISOCountry(1090, "LU", "LUX", "Luxembourg", "Luxembourg, Luxembourgish");

    /* renamed from: LV */
    public static final Country f5942LV = new ISOCountry(1064, "LV", "LVA", "Latvia", "Latvian");

    /* renamed from: LY */
    public static final Country f5943LY = new ISOCountry(1076, "LY", "LBY", "Libyan Arab Jamahiriya", "Libyan");

    /* renamed from: MA */
    public static final Country f5944MA = new ISOCountry(1284, "MA", "MAR", "Morocco", "Moroccan");

    /* renamed from: MC */
    public static final Country f5945MC = new ISOCountry(1170, "MC", "MCO", "Monaco", "Monegasque, Monacan");

    /* renamed from: MD */
    public static final Country f5946MD = new ISOCountry(1176, "MD", "MDA", "Moldova", "Moldovan");

    /* renamed from: ME */
    public static final Country f5947ME = new ISOCountry(1177, "ME", "MNE", "Montenegro", "Montenegrin");

    /* renamed from: MF */
    public static final Country f5948MF = new ISOCountry(1635, "MF", "MAF", "Saint Martin (French part)");

    /* renamed from: MG */
    public static final Country f5949MG = new ISOCountry(1104, "MG", "MDG", "Madagascar", "Malagasy");

    /* renamed from: MH */
    public static final Country f5950MH = new ISOCountry(1412, "MH", "MHL", "Marshall Islands", "Marshallese");

    /* renamed from: MK */
    public static final Country f5951MK = new ISOCountry(2055, "MK", "MKD", "Macedonia the former Yugoslav Republic of", "Macedonian");

    /* renamed from: ML */
    public static final Country f5952ML = new ISOCountry(1126, "ML", "MLI", "Mali", "Malian");

    /* renamed from: MM */
    public static final Country f5953MM = new ISOCountry(Configs.BUILD_ID_BASE, "MM", "MMR", "Myanmar", "Burmese");

    /* renamed from: MN */
    public static final Country f5954MN = new ISOCountry(1174, "MN", "MNG", "Mongolia", "Mongolian");

    /* renamed from: MO */
    public static final Country f5955MO = new ISOCountry(1094, "MO", "MAC", "Macao", "Macanese, Chinese");

    /* renamed from: MP */
    public static final Country f5956MP = new ISOCountry(1408, "MP", "MNP", "Northern Mariana Islands", "Northern Marianan");

    /* renamed from: MQ */
    public static final Country f5957MQ = new ISOCountry(1140, "MQ", "MTQ", "Martinique", "Martiniquais, Martinican");

    /* renamed from: MR */
    public static final Country f5958MR = new ISOCountry(1144, "MR", "MRT", "Mauritania", "Mauritanian");

    /* renamed from: MS */
    public static final Country f5959MS = new ISOCountry(1280, "MS", "MSR", "Montserrat", "Montserratian");

    /* renamed from: MT */
    public static final Country f5960MT = new ISOCountry(1136, "MT", "MLT", "Malta", "Maltese");

    /* renamed from: MU */
    public static final Country f5961MU = new ISOCountry(1152, "MU", "MUS", "Mauritius", "Mauritian");

    /* renamed from: MV */
    public static final Country f5962MV = new ISOCountry(1122, "MV", "MDV", "Maldives", "Maldivian");

    /* renamed from: MW */
    public static final Country f5963MW = new ISOCountry(1108, "MW", "MWI", "Malawi", "Malawian");

    /* renamed from: MX */
    public static final Country f5964MX = new ISOCountry(1156, "MX", "MEX", "Mexico", "Mexican");

    /* renamed from: MY */
    public static final Country f5965MY = new ISOCountry(1112, "MY", "MYS", "Malaysia", "Malaysian");

    /* renamed from: MZ */
    public static final Country f5966MZ = new ISOCountry(1288, "MZ", "MOZ", "Mozambique", "Mozambican");

    /* renamed from: NA */
    public static final Country f5967NA = new ISOCountry(1302, "NA", "NAM", "Namibia", "Namibian");

    /* renamed from: NC */
    public static final Country f5968NC = new ISOCountry(1344, "NC", "NCL", "New Caledonia", "New Caledonian");

    /* renamed from: NE */
    public static final Country f5969NE = new ISOCountry(1378, "NE", "NER", "Niger", "Nigerien");

    /* renamed from: NF */
    public static final Country f5970NF = new ISOCountry(1396, "NF", "NFK", "Norfolk Island");

    /* renamed from: NG */
    public static final Country f5971NG = new ISOCountry(1382, "NG", "NGA", "Nigeria", "Nigerian");

    /* renamed from: NI */
    public static final Country f5972NI = new ISOCountry(1368, "NI", "NIC", "Nicaragua", "Nicaraguan");

    /* renamed from: NL */
    public static final Country f5973NL = new ISOCountry(1320, AirbnbConstants.COUNTRY_CODE_NETHERLANDS, "NLD", "Netherlands", "Dutch");

    /* renamed from: NO */
    public static final Country f5974NO = new ISOCountry(1400, "NO", "NOR", "Norway", "Norwegian");

    /* renamed from: NP */
    public static final Country f5975NP = new ISOCountry(1316, "NP", "NPL", "Nepal", "Nepali");

    /* renamed from: NR */
    public static final Country f5976NR = new ISOCountry(1312, "NR", "NRU", "Nauru", "Nauruan");

    /* renamed from: NU */
    public static final Country f5977NU = new ISOCountry(1392, "NU", "NIU", "Niue", "Niuean");

    /* renamed from: NZ */
    public static final Country f5978NZ = new ISOCountry(1364, "NZ", "NZL", "New Zealand");

    /* renamed from: OM */
    public static final Country f5979OM = new ISOCountry(1298, "OM", "OMN", "Oman", "Omani");

    /* renamed from: PA */
    public static final Country f5980PA = new ISOCountry(1425, "PA", "PAN", "Panama", "Panamanian");

    /* renamed from: PE */
    public static final Country f5981PE = new ISOCountry(1540, "PE", "PER", "Peru", "Peruvian");

    /* renamed from: PF */
    public static final Country f5982PF = new ISOCountry(600, "PF", "PYF", "French Polynesia", "French Polynesian");

    /* renamed from: PG */
    public static final Country f5983PG = new ISOCountry(1432, "PG", "PNG", "Papua New Guinea", "Papua New Guinean, Papuan");

    /* renamed from: PH */
    public static final Country f5984PH = new ISOCountry(1544, "PH", "PHL", "Philippines", "Philippine, Filipino");

    /* renamed from: PK */
    public static final Country f5985PK = new ISOCountry(1414, "PK", "PAK", "Pakistan", "Pakistani");

    /* renamed from: PL */
    public static final Country f5986PL = new ISOCountry(1558, "PL", "POL", "Poland", "Polish");

    /* renamed from: PM */
    public static final Country f5987PM = new ISOCountry(1638, "PM", "SPM", "Saint Pierre and Miquelon", "Saint Pierrais, Miquelonnais");

    /* renamed from: PN */
    public static final Country f5988PN = new ISOCountry(1554, "PN", "PCN", "Pitcairn");

    /* renamed from: PR */
    public static final Country f5989PR = new ISOCountry(1584, "PR", "PRI", "Puerto Rico", "Puerto Rican");

    /* renamed from: PS */
    public static final Country f5990PS = new ISOCountry(629, "PS", "PSE", "Palestinian Territory Occupied", "Palestinian");

    /* renamed from: PT */
    public static final Country f5991PT = new ISOCountry(1568, "PT", "PRT", "Portugal", "Portuguese");

    /* renamed from: PW */
    public static final Country f5992PW = new ISOCountry(1413, "PW", "PLW", "Palau", "Palauan");

    /* renamed from: PY */
    public static final Country f5993PY = new ISOCountry(HelpCenterArticle.COHOSTING_DIFFERENCE_BETWEEN_COHOST_AND_PRIMARY_HOST, "PY", "PRY", "Paraguay", "Paraguayan");

    /* renamed from: QA */
    public static final Country f5994QA = new ISOCountry(1588, "QA", "QAT", "Qatar", "Qatari");

    /* renamed from: RE */
    public static final Country f5995RE = new ISOCountry(1592, "RE", "REU", "Reunion", "Reunionese, Reunionnais");

    /* renamed from: RO */
    public static final Country f5996RO = new ISOCountry(1602, "RO", "ROU", "Romania", "Romanian");

    /* renamed from: RS */
    public static final Country f5997RS = new ISOCountry(1672, "RS", "SRB", "Serbia", "Serbian");

    /* renamed from: RU */
    public static final Country f5998RU = new ISOCountry(1603, "RU", "RUS", "Russian Federation", "Russian");

    /* renamed from: RW */
    public static final Country f5999RW = new ISOCountry(1606, "RW", "RWA", "Rwanda", "Rwandan");

    /* renamed from: SA */
    public static final Country f6000SA = new ISOCountry(1666, "SA", "SAU", "Saudi Arabia", "Saudi, Saudi Arabian");

    /* renamed from: SB */
    public static final Country f6001SB = new ISOCountry(CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, "SB", "SLB", "Solomon Islands", "Solomon Island");

    /* renamed from: SC */
    public static final Country f6002SC = new ISOCountry(1680, BouncyCastleProvider.PROVIDER_NAME, "SYC", "Seychelles", "Seychellois");

    /* renamed from: SD */
    public static final Country f6003SD = new ISOCountry(1846, "SD", "SDN", "Sudan", "Sudanese");

    /* renamed from: SE */
    public static final Country f6004SE = new ISOCountry(1874, "SE", "SWE", "Sweden", "Swedish");

    /* renamed from: SG */
    public static final Country f6005SG = new ISOCountry(1794, "SG", "SGP", "Singapore");

    /* renamed from: SH */
    public static final Country f6006SH = new ISOCountry(1620, "SH", "SHN", "Saint Helena", "Saint Helenian");

    /* renamed from: SI */
    public static final Country f6007SI = new ISOCountry(1797, "SI", "SVN", "Slovenia", "Slovenian, Slovene");

    /* renamed from: SJ */
    public static final Country f6008SJ = new ISOCountry(1860, "SJ", "SJM", "Svalbard and Jan Mayen");

    /* renamed from: SK */
    public static final Country f6009SK = new ISOCountry(1795, "SK", "SVK", "Slovakia", "Slovak");

    /* renamed from: SL */
    public static final Country f6010SL = new ISOCountry(1684, "SL", "SLE", "Sierra Leone", "Sierra Leonean");

    /* renamed from: SM */
    public static final Country f6011SM = new ISOCountry(1652, "SM", "SMR", "San Marino", "Sammarinese");

    /* renamed from: SN */
    public static final Country f6012SN = new ISOCountry(1670, "SN", "SEN", "Senegal", "Senegalese");

    /* renamed from: SO */
    public static final Country f6013SO = new ISOCountry(1798, "SO", "SOM", "Somalia", "Somali, Somalian");

    /* renamed from: SR */
    public static final Country f6014SR = new ISOCountry(1856, "SR", "SUR", "Suriname", "Surinamese");

    /* renamed from: ST */
    public static final Country f6015ST = new ISOCountry(1656, "ST", "STP", "Sao Tome and Principe", "Sao Tomean");

    /* renamed from: SV */
    public static final Country f6016SV = new ISOCountry(546, "SV", "SLV", "El Salvador", "Salvadoran");

    /* renamed from: SY */
    public static final Country f6017SY = new ISOCountry(1888, "SY", "SYR", "Syrian Arab Republic", "Syrian");

    /* renamed from: SZ */
    public static final Country f6018SZ = new ISOCountry(1864, "SZ", "SWZ", "Swaziland", "Swazi");

    /* renamed from: TC */
    public static final Country f6019TC = new ISOCountry(1942, "TC", "TCA", "Turks and Caicos Islands");

    /* renamed from: TD */
    public static final Country f6020TD = new ISOCountry(328, "TD", "TCD", "Chad", "Chadian");

    /* renamed from: TF */
    public static final Country f6021TF = new ISOCountry(608, "TF", "ATF", "French Southern Territories");

    /* renamed from: TG */
    public static final Country f6022TG = new ISOCountry(1896, "TG", "TGO", "Togo", "Togolese");

    /* renamed from: TH */
    public static final Country f6023TH = new ISOCountry(1892, "TH", "THA", "Thailand", "Thai");

    /* renamed from: TJ */
    public static final Country f6024TJ = new ISOCountry(1890, "TJ", "TJK", "Tajikistan", "Tajikistani");

    /* renamed from: TK */
    public static final Country f6025TK = new ISOCountry(1906, "TK", "TKL", "Tokelau");

    /* renamed from: TL */
    public static final Country f6026TL = new ISOCountry(1574, "TL", "TLS", "Timor-Leste", "Timorese");

    /* renamed from: TM */
    public static final Country f6027TM = new ISOCountry(1941, "TM", "TKM", "Turkmenistan", "Turkmen");

    /* renamed from: TN */
    public static final Country f6028TN = new ISOCountry(1928, "TN", "TUN", "Tunisia", "Tunisian");

    /* renamed from: TO */
    public static final Country f6029TO = new ISOCountry(1910, "TO", "TON", "Tonga", "Tongan");

    /* renamed from: TR */
    public static final Country f6030TR = new ISOCountry(1938, "TR", "TUR", "Turkey", "Turkish");

    /* renamed from: TT */
    public static final Country f6031TT = new ISOCountry(1920, "TT", "TTO", "Trinidad and Tobago", "Trinidadian, Tobagonian");

    /* renamed from: TV */
    public static final Country f6032TV = new ISOCountry(1944, "TV", "TUV", "Tuvalu", "Tuvaluan");

    /* renamed from: TW */
    public static final Country f6033TW = new ISOCountry(344, "TW", "TWN", "Taiwan Province of China", "Taiwanese");

    /* renamed from: TZ */
    public static final Country f6034TZ = new ISOCountry(2100, "TZ", "TZA", "Tanzania United Republic of", "Tanzanian");

    /* renamed from: UA */
    public static final Country f6035UA = new ISOCountry(2052, "UA", "UKR", "Ukraine", "Ukrainian");

    /* renamed from: UG */
    public static final Country f6036UG = new ISOCountry(2048, "UG", "UGA", "Uganda", "Ugandan");

    /* renamed from: UM */
    public static final Country f6037UM = new ISOCountry(1409, "UM", "UMI", "United States Minor Outlying Islands");

    /* renamed from: US */
    public static final Country f6038US = new ISOCountry(2112, "US", "USA", "United States", "American");

    /* renamed from: UY */
    public static final Country f6039UY = new ISOCountry(2136, "UY", "URY", "Uruguay", "Uruguayan");

    /* renamed from: UZ */
    public static final Country f6040UZ = new ISOCountry(2144, "UZ", "UZB", "Uzbekistan", "Uzbekistani, Uzbek");

    /* renamed from: VA */
    public static final Country f6041VA = new ISOCountry(822, "VA", "VAT", "Holy See (Vatican City State)");
    private static final Country[] VALUES = {f5823AW, f5811AF, f5817AO, f5813AI, f5824AX, f5814AL, f5809AD, f5816AN, f5810AE, f5819AR, f5815AM, f5820AS, f5818AQ, f6021TF, f5812AG, f5822AU, f5821AT, f5825AZ, f5833BI, f5829BE, f5834BJ, f5830BF, f5828BD, f5831BG, f5832BH, f5840BS, f5826BA, f5835BL, f5844BY, f5845BZ, f5836BM, f5838BO, f5839BR, f5827BB, f5837BN, f5841BT, f5842BV, f5843BW, f5849CF, f5846CA, f5847CC, f5851CH, f5854CL, f5856CN, f5852CI, f5855CM, f5848CD, f5850CG, f5853CK, f5857CO, f5926KM, f5860CV, f5858CR, f5859CU, f5861CX, f5931KY, f5862CY, f5863CZ, f5864DE, f5865DJ, f5867DM, f5866DK, f5868DO, f5869DZ, f5870EC, f5872EG, f5874ER, f5873EH, f5875ES, f5871EE, f5876ET, f5877FI, f5878FJ, f5879FK, f5882FR, f5881FO, f5880FM, f5883GA, f5884GB, f5886GE, f5888GG, f5889GH, f5890GI, f5893GN, f5894GP, f5892GM, f5900GW, f5895GQ, f5896GR, f5885GD, f5891GL, f5898GT, f5887GF, f5899GU, f5901GY, f5902HK, f5903HM, f5904HN, f5905HR, f5906HT, f5907HU, f5908ID, f5911IM, f5912IN, f5913IO, f5909IE, f5915IR, f5914IQ, f5916IS, f5910IL, f5917IT, f5919JM, f5918JE, f5920JO, f5921JP, f5932KZ, f5922KE, f5923KG, f5924KH, f5925KI, f5927KN, f5929KR, f5930KW, f5933LA, f5934LB, f5938LR, f5943LY, f5935LC, f5936LI, f5937LK, f5939LS, f5940LT, f5941LU, f5942LV, f5955MO, f5948MF, f5944MA, f5945MC, f5946MD, f5949MG, f5962MV, f5964MX, f5950MH, f5951MK, f5952ML, f5960MT, f5953MM, f5947ME, f5954MN, f5956MP, f5966MZ, f5958MR, f5959MS, f5957MQ, f5961MU, f5963MW, f5965MY, f6051YT, f5967NA, f5968NC, f5969NE, f5970NF, f5971NG, f5972NI, f5977NU, f5973NL, f5974NO, f5975NP, f5976NR, f5978NZ, f5979OM, f5985PK, f5980PA, f5988PN, f5981PE, f5984PH, f5992PW, f5983PG, f5986PL, f5989PR, f5928KP, f5991PT, f5993PY, f5990PS, f5982PF, f5994QA, f5995RE, f5996RO, f5998RU, f5999RW, f6000SA, f6003SD, f6012SN, f6005SG, f5897GS, f6006SH, f6008SJ, f6001SB, f6010SL, f6016SV, f6011SM, f6013SO, f5987PM, f5997RS, f6015ST, f6014SR, f6009SK, f6007SI, f6004SE, f6018SZ, f6002SC, f6017SY, f6019TC, f6020TD, f6022TG, f6023TH, f6024TJ, f6025TK, f6027TM, f6026TL, f6029TO, f6031TT, f6028TN, f6030TR, f6032TV, f6033TW, f6034TZ, f6036UG, f6035UA, f6037UM, f6039UY, f6038US, f6040UZ, f6041VA, f6042VC, f6043VE, f6044VG, f6045VI, f6046VN, f6047VU, f6048WF, f6049WS, f6050YE, f6052ZA, f6053ZM, f6054ZW};

    /* renamed from: VC */
    public static final Country f6042VC = new ISOCountry(1648, "VC", "VCT", "Saint Vincent and the Grenadines", "Saint Vincentian");

    /* renamed from: VE */
    public static final Country f6043VE = new ISOCountry(2146, "VE", "VEN", "Venezuela", "Venezuelan");

    /* renamed from: VG */
    public static final Country f6044VG = new ISOCountry(CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, "VG", "VGB", "Virgin Islands British", "Virgin Island");

    /* renamed from: VI */
    public static final Country f6045VI = new ISOCountry(2128, "VI", "VIR", "Virgin Islands U.S.", "Virgin Island");

    /* renamed from: VN */
    public static final Country f6046VN = new ISOCountry(1796, "VN", "VNM", "Viet Nam", "Vietnamese");

    /* renamed from: VU */
    public static final Country f6047VU = new ISOCountry(1352, "VU", "VUT", "Vanuatu", "Ni-Vanuatu, Vanuatuan");

    /* renamed from: WF */
    public static final Country f6048WF = new ISOCountry(2166, "WF", "WLF", "Wallis and Futuna", "Wallisian, Futunan");

    /* renamed from: WS */
    public static final Country f6049WS = new ISOCountry(2178, "WS", "WSM", "Samoa", "Samoan");

    /* renamed from: YE */
    public static final Country f6050YE = new ISOCountry(2183, "YE", "YEM", "Yemen", "Yemeni");

    /* renamed from: YT */
    public static final Country f6051YT = new ISOCountry(373, "YT", "MYT", "Mayotte", "Mahoran");

    /* renamed from: ZA */
    public static final Country f6052ZA = new ISOCountry(1808, "ZA", "ZAF", "South Africa", "South African");

    /* renamed from: ZM */
    public static final Country f6053ZM = new ISOCountry(2196, "ZM", "ZMB", "Zambia", "Zambian");

    /* renamed from: ZW */
    public static final Country f6054ZW = new ISOCountry(1814, "ZW", "ZWE", "Zimbabwe", "Zimbabwean");
    private static final long serialVersionUID = 7220597933847617859L;
    private String alpha2Code;
    private String alpha3Code;
    private int code;
    private String name;
    private String nationality;

    private ISOCountry(int i, String str, String str2, String str3) {
        this(i, str, str2, str3, str3);
    }

    private ISOCountry(int i, String str, String str2, String str3, String str4) {
        this.code = i;
        this.alpha2Code = str;
        this.alpha3Code = str2;
        this.name = str3;
        this.nationality = str4;
    }

    public static Country[] values() {
        return VALUES;
    }

    public int compareTo(Country country) {
        return toAlpha2Code().compareTo(country.toAlpha2Code());
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(getClass()) && ((ISOCountry) obj).code == this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getNationality() {
        return this.nationality;
    }

    public int hashCode() {
        return this.code;
    }

    public String toAlpha2Code() {
        return this.alpha2Code;
    }

    public String toAlpha3Code() {
        return this.alpha3Code;
    }

    public String toString() {
        return this.alpha2Code;
    }

    public int valueOf() {
        return this.code;
    }
}
