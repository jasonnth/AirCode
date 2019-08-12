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

/* renamed from: net.sf.scuba.data.UnicodeCountry */
public class UnicodeCountry extends Country implements Serializable {

    /* renamed from: AD */
    public static final Country f6056AD = new UnicodeCountry(32, "AD", "AND", "Andorra", "Andorran");

    /* renamed from: AE */
    public static final Country f6057AE = new UnicodeCountry(1924, "AE", "ARE", "United Arab Emirates", "Emirati, Emirian");

    /* renamed from: AF */
    public static final Country f6058AF = new UnicodeCountry(4, "AF", "AFG", "Afghanistan", "Afghan");

    /* renamed from: AG */
    public static final Country f6059AG = new UnicodeCountry(40, "AG", "ATG", "Antigua and Barbuda", "Antiguan, Barbudan");

    /* renamed from: AI */
    public static final Country f6060AI = new UnicodeCountry(1632, "AI", "AIA", "Anguilla", "Anguillan");

    /* renamed from: AL */
    public static final Country f6061AL = new UnicodeCountry(8, "AL", "ALB", "Albania", "Albanian");

    /* renamed from: AM */
    public static final Country f6062AM = new UnicodeCountry(81, "AM", "ARM", "Armenia", "Armenian");

    /* renamed from: AN */
    public static final Country f6063AN = new UnicodeCountry(1328, "AN", "ANT", "Netherlands Antilles", "Antillean");

    /* renamed from: AO */
    public static final Country f6064AO = new UnicodeCountry(36, "AO", "AGO", "Angola", "Angolan");

    /* renamed from: AQ */
    public static final Country f6065AQ = new UnicodeCountry(16, "AQ", "ATA", "Antarctica", "Antarctic");

    /* renamed from: AR */
    public static final Country f6066AR = new UnicodeCountry(50, "AR", "ARG", "Argentina", "Argentine, Argentinean, Argentinian");

    /* renamed from: AS */
    public static final Country f6067AS = new UnicodeCountry(22, "AS", "ASM", "American Samoa", "American Samoan");

    /* renamed from: AT */
    public static final Country f6068AT = new UnicodeCountry(64, "AT", "AUT", "Austria", "Austrian");

    /* renamed from: AU */
    public static final Country f6069AU = new UnicodeCountry(54, AirbnbConstants.COUNTRY_CODE_AUSTRALIA, "AUS", "Australia", "Australian");

    /* renamed from: AW */
    public static final Country f6070AW = new UnicodeCountry(1331, "AW", "ABW", "Aruba", "Aruban");

    /* renamed from: AX */
    public static final Country f6071AX = new UnicodeCountry(584, "AX", "ALA", "Åland Islands", "");

    /* renamed from: AZ */
    public static final Country f6072AZ = new UnicodeCountry(49, "AZ", "AZE", "Azerbaijan", "Azerbaijani, Azeri");

    /* renamed from: BA */
    public static final Country f6073BA = new UnicodeCountry(112, "BA", "BIH", "Bosnia and Herzegovina", "Bosnian, Bosniak, Herzegovinian");

    /* renamed from: BB */
    public static final Country f6074BB = new UnicodeCountry(82, "BB", "BRB", "Barbados", "Barbadian");

    /* renamed from: BD */
    public static final Country f6075BD = new UnicodeCountry(80, "BD", "BGD", "Bangladesh", "Bangladeshi");

    /* renamed from: BE */
    public static final Country f6076BE = new UnicodeCountry(86, "BE", "BEL", "Belgium", "Belgian");

    /* renamed from: BF */
    public static final Country f6077BF = new UnicodeCountry(2132, "BF", "BFA", "Burkina Faso", "Burkinabe");

    /* renamed from: BG */
    public static final Country f6078BG = new UnicodeCountry(256, "BG", "BGR", "Bulgaria", "Bulgarian");

    /* renamed from: BH */
    public static final Country f6079BH = new UnicodeCountry(72, "BH", "BHR", "Bahrain", "Bahraini");

    /* renamed from: BI */
    public static final Country f6080BI = new UnicodeCountry(264, "BI", "BDI", "Burundi", "Burundian");

    /* renamed from: BJ */
    public static final Country f6081BJ = new UnicodeCountry(516, "BJ", "BEN", "Benin", "Beninese");

    /* renamed from: BL */
    public static final Country f6082BL = new UnicodeCountry(1618, "BL", "BLM", "Saint Barthélemy", "");

    /* renamed from: BM */
    public static final Country f6083BM = new UnicodeCountry(96, "BM", "BMU", "Bermuda", "Bermudian, Bermudan");

    /* renamed from: BN */
    public static final Country f6084BN = new UnicodeCountry(150, "BN", "BRN", "Brunei", "Bruneian");

    /* renamed from: BO */
    public static final Country f6085BO = new UnicodeCountry(104, "BO", "BOL", "Bolivia", "Bolivian");

    /* renamed from: BR */
    public static final Country f6086BR = new UnicodeCountry(LDSFile.EF_DG4_TAG, AirbnbConstants.COUNTRY_CODE_BRAZIL, "BRA", "Brazil", "Brazilian");

    /* renamed from: BS */
    public static final Country f6087BS = new UnicodeCountry(68, "BS", "BHS", "Bahamas", "Bahamian");

    /* renamed from: BT */
    public static final Country f6088BT = new UnicodeCountry(100, "BT", "BTN", "Bhutan", "Bhutanese");

    /* renamed from: BV */
    public static final Country f6089BV = new UnicodeCountry(116, "BV", "BVT", "Bouvet Island", "");

    /* renamed from: BW */
    public static final Country f6090BW = new UnicodeCountry(114, "BW", "BWA", "Botswana", "Botswanan");

    /* renamed from: BY */
    public static final Country f6091BY = new UnicodeCountry(TiffUtil.TIFF_TAG_ORIENTATION, "BY", "BLR", "Belarus", "Belarusian");

    /* renamed from: BZ */
    public static final Country f6092BZ = new UnicodeCountry(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, "BZ", "BLZ", "Belize", "Belizean");

    /* renamed from: CA */
    public static final Country f6093CA = new UnicodeCountry(292, "CA", "CAN", "Canada", "Canadian");

    /* renamed from: CC */
    public static final Country f6094CC = new UnicodeCountry(358, "CC", "CCK", "Cocos [Keeling] Islands", "");

    /* renamed from: CD */
    public static final Country f6095CD = new UnicodeCountry(384, "CD", "COD", "Congo - Kinshasa", "Congolese");

    /* renamed from: CF */
    public static final Country f6096CF = new UnicodeCountry(320, "CF", "CAF", "Central African Republic", "Central African");

    /* renamed from: CG */
    public static final Country f6097CG = new UnicodeCountry(HelpCenterArticle.LEGAL_ISSUES, "CG", "COG", "Congo - Brazzaville", "Congolese");

    /* renamed from: CH */
    public static final Country f6098CH = new UnicodeCountry(1878, "CH", "CHE", "Switzerland", "Swiss");

    /* renamed from: CI */
    public static final Country f6099CI = new UnicodeCountry(900, "CI", "CIV", "Côte d’Ivoire", "Ivorian");

    /* renamed from: CK */
    public static final Country f6100CK = new UnicodeCountry(388, "CK", "COK", "Cook Islands", "");

    /* renamed from: CL */
    public static final Country f6101CL = new UnicodeCountry(338, "CL", "CHL", "Chile", "Chilean");

    /* renamed from: CM */
    public static final Country f6102CM = new UnicodeCountry(288, "CM", "CMR", "Cameroon", "Cameroonian");

    /* renamed from: CN */
    public static final Country f6103CN = new UnicodeCountry(342, AirbnbConstants.COUNTRY_CODE_CHINA, "CHN", "China", "Chinese");

    /* renamed from: CO */
    public static final Country f6104CO = new UnicodeCountry(368, "CO", "COL", "Colombia", "Colombian");

    /* renamed from: CR */
    public static final Country f6105CR = new UnicodeCountry(392, "CR", "CRI", "Costa Rica", "Costa Rican");

    /* renamed from: CU */
    public static final Country f6106CU = new UnicodeCountry(402, AirbnbConstants.COUNTRY_CODE_CUBA, "CUB", "Cuba", "Cuban");

    /* renamed from: CV */
    public static final Country f6107CV = new UnicodeCountry(MobileEvents.EVENTTYPE_SDKPARAMETERS, "CV", "CPV", "Cape Verde", "Cape Verdean");

    /* renamed from: CX */
    public static final Country f6108CX = new UnicodeCountry(354, "CX", "CXR", "Christmas Island", "");

    /* renamed from: CY */
    public static final Country f6109CY = new UnicodeCountry(406, "CY", "CYP", "Cyprus", "Cypriot");

    /* renamed from: CZ */
    public static final Country f6110CZ = new UnicodeCountry(515, "CZ", "CZE", "Czech Republic", "Czech");

    /* renamed from: DE */
    public static final Country f6111DE = new UnicodeCountry(630, AirbnbConstants.COUNTRY_CODE_GERMANY, ServerSettingsModel.GERMAN_ISO3, "Germany", "German");

    /* renamed from: DJ */
    public static final Country f6112DJ = new UnicodeCountry(610, "DJ", "DJI", "Djibouti", "Djiboutian");

    /* renamed from: DK */
    public static final Country f6113DK = new UnicodeCountry(520, "DK", "DNK", "Denmark", "Danish");

    /* renamed from: DM */
    public static final Country f6114DM = new UnicodeCountry(530, "DM", "DMA", "Dominica", "Dominican");

    /* renamed from: DO */
    public static final Country f6115DO = new UnicodeCountry(532, "DO", "DOM", "Dominican Republic", "Dominican");

    /* renamed from: DZ */
    public static final Country f6116DZ = new UnicodeCountry(18, "DZ", "DZA", "Algeria", "Algerian");

    /* renamed from: EC */
    public static final Country f6117EC = new UnicodeCountry(536, "EC", "ECU", "Ecuador", "Ecuadorian");

    /* renamed from: EE */
    public static final Country f6118EE = new UnicodeCountry(563, "EE", "EST", "Estonia", "Estonian");

    /* renamed from: EG */
    public static final Country f6119EG = new UnicodeCountry(2072, "EG", "EGY", "Egypt", "Egyptian");

    /* renamed from: EH */
    public static final Country f6120EH = new UnicodeCountry(1842, "EH", "ESH", "Western Sahara", "Sahraw, Sahrawian, Sahraouian");

    /* renamed from: ER */
    public static final Country f6121ER = new UnicodeCountry(562, "ER", "ERI", "Eritrea", "Eritrean");

    /* renamed from: ES */
    public static final Country f6122ES = new UnicodeCountry(1828, "ES", "ESP", "Spain", "Spanish");

    /* renamed from: ET */
    public static final Country f6123ET = new UnicodeCountry(561, "ET", "ETH", "Ethiopia", "Ethiopian");

    /* renamed from: FI */
    public static final Country f6124FI = new UnicodeCountry(582, "FI", "FIN", "Finland", "Finnish");

    /* renamed from: FJ */
    public static final Country f6125FJ = new UnicodeCountry(578, "FJ", "FJI", "Fiji", "Fijian");

    /* renamed from: FK */
    public static final Country f6126FK = new UnicodeCountry(568, "FK", "FLK", "Falkland Islands", "");

    /* renamed from: FM */
    public static final Country f6127FM = new UnicodeCountry(1411, "FM", "FSM", "Micronesia", "Micronesian");

    /* renamed from: FO */
    public static final Country f6128FO = new UnicodeCountry(564, "FO", "FRO", "Faroe Islands", "Faroese");

    /* renamed from: FR */
    public static final Country f6129FR = new UnicodeCountry(592, "FR", ServerSettingsModel.FRANCE_ISO3, "France", "French");

    /* renamed from: GA */
    public static final Country f6130GA = new UnicodeCountry(614, "GA", "GAB", "Gabon", "Gabonese");

    /* renamed from: GB */
    public static final Country f6131GB = new UnicodeCountry(2086, "GB", "GBR", "United Kingdom", "British");

    /* renamed from: GD */
    public static final Country f6132GD = new UnicodeCountry(776, "GD", "GRD", "Grenada", "Grenadian");

    /* renamed from: GE */
    public static final Country f6133GE = new UnicodeCountry(616, "GE", "GEO", "Georgia", "Georgian");

    /* renamed from: GF */
    public static final Country f6134GF = new UnicodeCountry(596, "GF", "GUF", "French Guiana", "French Guianese");

    /* renamed from: GG */
    public static final Country f6135GG = new UnicodeCountry(2097, "GG", "GGY", "Guernsey", "");

    /* renamed from: GH */
    public static final Country f6136GH = new UnicodeCountry(648, "GH", "GHA", "Ghana", "Ghanaian");

    /* renamed from: GI */
    public static final Country f6137GI = new UnicodeCountry(658, "GI", "GIB", "Gibraltar", "");

    /* renamed from: GL */
    public static final Country f6138GL = new UnicodeCountry(772, "GL", "GRL", "Greenland", "Greenlandic");

    /* renamed from: GM */
    public static final Country f6139GM = new UnicodeCountry(624, "GM", "GMB", "Gambia", "Gambian");

    /* renamed from: GN */
    public static final Country f6140GN = new UnicodeCountry(804, "GN", "GIN", "Guinea", "Guinean");

    /* renamed from: GP */
    public static final Country f6141GP = new UnicodeCountry(786, "GP", "GLP", "Guadeloupe", "");

    /* renamed from: GQ */
    public static final Country f6142GQ = new UnicodeCountry(550, "GQ", "GNQ", "Equatorial Guinea", "Equatorial Guinean, Equatoguinean");

    /* renamed from: GR */
    public static final Country f6143GR = new UnicodeCountry(768, "GR", "GRC", "Greece", "Greek, Hellenic");

    /* renamed from: GS */
    public static final Country f6144GS = new UnicodeCountry(569, "GS", "SGS", "South Georgia and the South Sandwich Islands", "");

    /* renamed from: GT */
    public static final Country f6145GT = new UnicodeCountry(800, "GT", "GTM", "Guatemala", "Guatemalan");

    /* renamed from: GU */
    public static final Country f6146GU = new UnicodeCountry(790, "GU", "GUM", "Guam", "Guamanian");

    /* renamed from: GW */
    public static final Country f6147GW = new UnicodeCountry(1572, "GW", "GNB", "Guinea-Bissau", "Guinean");

    /* renamed from: GY */
    public static final Country f6148GY = new UnicodeCountry(808, "GY", "GUY", "Guyana", "Guyanese");

    /* renamed from: HK */
    public static final Country f6149HK = new UnicodeCountry(836, "HK", "HKG", "Hong Kong SAR China", "Hong Kong, Hongkongese");

    /* renamed from: HM */
    public static final Country f6150HM = new UnicodeCountry(MiSnapApiConstants.DEFAULT_MAX_BRIGHTNESS, "HM", "HMD", "Heard Island and McDonald Islands", "");

    /* renamed from: HN */
    public static final Country f6151HN = new UnicodeCountry(832, "HN", "HND", "Honduras", "Honduran");

    /* renamed from: HR */
    public static final Country f6152HR = new UnicodeCountry(401, "HR", "HRV", "Croatia", "Croatian");

    /* renamed from: HT */
    public static final Country f6153HT = new UnicodeCountry(818, "HT", "HTI", "Haiti", "Haitian");

    /* renamed from: HU */
    public static final Country f6154HU = new UnicodeCountry(840, "HU", "HUN", "Hungary", "Hungarian");

    /* renamed from: ID */
    public static final Country f6155ID = new UnicodeCountry(864, "ID", "IDN", "Indonesia", "Indonesian");

    /* renamed from: IE */
    public static final Country f6156IE = new UnicodeCountry(882, "IE", "IRL", "Ireland", "Irish");

    /* renamed from: IL */
    public static final Country f6157IL = new UnicodeCountry(886, "IL", "ISR", "Israel", "Israeli");

    /* renamed from: IM */
    public static final Country f6158IM = new UnicodeCountry(2099, "IM", "IMN", "Isle of Man", "Manx");

    /* renamed from: IN */
    public static final Country f6159IN = new UnicodeCountry(854, AirbnbConstants.COUNTRY_CODE_INDIA, "IND", "India", "Indian");

    /* renamed from: IO */
    public static final Country f6160IO = new UnicodeCountry(134, "IO", "IOT", "British Indian Ocean Territory", "");

    /* renamed from: IQ */
    public static final Country f6161IQ = new UnicodeCountry(872, "IQ", "IRQ", "Iraq", "Iraqi");

    /* renamed from: IR */
    public static final Country f6162IR = new UnicodeCountry(868, "IR", "IRN", "Iran", "Iranian, Persian");

    /* renamed from: IS */
    public static final Country f6163IS = new UnicodeCountry(MiSnapApiConstants.DEFAULT_CORNER_CONFIDENCE_DRIVER_LICENSE, "IS", "ISL", "Iceland", "Icelandic");

    /* renamed from: IT */
    public static final Country f6164IT = new UnicodeCountry(896, "IT", "ITA", "Italy", "Italian");

    /* renamed from: JE */
    public static final Country f6165JE = new UnicodeCountry(2098, "JE", "JEY", "Jersey", "");

    /* renamed from: JM */
    public static final Country f6166JM = new UnicodeCountry(904, "JM", "JAM", "Jamaica", "Jamaican");

    /* renamed from: JO */
    public static final Country f6167JO = new UnicodeCountry(1024, "JO", "JOR", "Jordan", "Jordanian");

    /* renamed from: JP */
    public static final Country f6168JP = new UnicodeCountry(914, AirbnbConstants.COUNTRY_CODE_JAPAN, "JPN", "Japan", "Japanese");

    /* renamed from: KE */
    public static final Country f6169KE = new UnicodeCountry(StatusCode.RESULT_TYPE_NOTIFACTION_OPENED, "KE", "KEN", "Kenya", "Kenyan");

    /* renamed from: KG */
    public static final Country f6170KG = new UnicodeCountry(1047, "KG", "KGZ", "Kyrgyzstan", "Kyrgyz");

    /* renamed from: KH */
    public static final Country f6171KH = new UnicodeCountry(278, "KH", "KHM", "Cambodia", "Cambodian");

    /* renamed from: KI */
    public static final Country f6172KI = new UnicodeCountry(662, "KI", "KIR", "Kiribati", "I-Kiribati");

    /* renamed from: KM */
    public static final Country f6173KM = new UnicodeCountry(372, "KM", "COM", "Comoros", "Comorian");

    /* renamed from: KN */
    public static final Country f6174KN = new UnicodeCountry(1625, "KN", "KNA", "Saint Kitts and Nevis", "");

    /* renamed from: KP */
    public static final Country f6175KP = new UnicodeCountry(StatusCode.RESULT_TYPE_RESUME_PUSH, "KP", "PRK", "North Korea", "North Korean");

    /* renamed from: KR */
    public static final Country f6176KR = new UnicodeCountry(1040, AirbnbConstants.COUNTRY_CODE_KOREA, "KOR", "South Korea", "South Korean");

    /* renamed from: KW */
    public static final Country f6177KW = new UnicodeCountry(1044, "KW", "KWT", "Kuwait", "Kuwaiti");

    /* renamed from: KY */
    public static final Country f6178KY = new UnicodeCountry(310, "KY", "CYM", "Cayman Islands", "Caymanian");

    /* renamed from: KZ */
    public static final Country f6179KZ = new UnicodeCountry(MiSnapApiConstants.DEFAULT_MICR_CONFIDENCE_FOR_PASSPORT, "KZ", "KAZ", "Kazakhstan", "Kazakh");

    /* renamed from: LA */
    public static final Country f6180LA = new UnicodeCountry(1048, "LA", "Lao", "Laos", "Lao");

    /* renamed from: LB */
    public static final Country f6181LB = new UnicodeCountry(1058, "LB", "LBN", "Lebanon", "Lebanese");

    /* renamed from: LC */
    public static final Country f6182LC = new UnicodeCountry(1634, "LC", "LCA", "Saint Lucia", "Saint Lucian");

    /* renamed from: LI */
    public static final Country f6183LI = new UnicodeCountry(1080, "LI", "LIE", "Liechtenstein", "");

    /* renamed from: LK */
    public static final Country f6184LK = new UnicodeCountry(324, "LK", "LKA", "Sri Lanka", "Sri Lankan");

    /* renamed from: LR */
    public static final Country f6185LR = new UnicodeCountry(1072, "LR", "LBR", "Liberia", "Liberian");

    /* renamed from: LS */
    public static final Country f6186LS = new UnicodeCountry(1062, "LS", "LSO", "Lesotho", "Basotho");

    /* renamed from: LT */
    public static final Country f6187LT = new UnicodeCountry(1088, "LT", "LTU", "Lithuania", "Lithuanian");

    /* renamed from: LU */
    public static final Country f6188LU = new UnicodeCountry(1090, "LU", "LUX", "Luxembourg", "Luxembourg, Luxembourgish");

    /* renamed from: LV */
    public static final Country f6189LV = new UnicodeCountry(1064, "LV", "LVA", "Latvia", "Latvian");

    /* renamed from: LY */
    public static final Country f6190LY = new UnicodeCountry(1076, "LY", "LBY", "Libya", "Libyan");

    /* renamed from: MA */
    public static final Country f6191MA = new UnicodeCountry(1284, "MA", "MAR", "Morocco", "Moroccan");

    /* renamed from: MC */
    public static final Country f6192MC = new UnicodeCountry(1170, "MC", "MCO", "Monaco", "Monegasque, Monacan");

    /* renamed from: MD */
    public static final Country f6193MD = new UnicodeCountry(1176, "MD", "MDA", "Moldova", "Moldovan");

    /* renamed from: ME */
    public static final Country f6194ME = new UnicodeCountry(1177, "ME", "MNE", "Montenegro", "Montenegrin");

    /* renamed from: MF */
    public static final Country f6195MF = new UnicodeCountry(1635, "MF", "MAF", "Saint Martin", "");

    /* renamed from: MG */
    public static final Country f6196MG = new UnicodeCountry(1104, "MG", "MDG", "Madagascar", "Malagasy");

    /* renamed from: MH */
    public static final Country f6197MH = new UnicodeCountry(1412, "MH", "MHL", "Marshall Islands", "Marshallese");

    /* renamed from: MK */
    public static final Country f6198MK = new UnicodeCountry(2055, "MK", "MKD", "Macedonia", "Macedonian");

    /* renamed from: ML */
    public static final Country f6199ML = new UnicodeCountry(1126, "ML", "MLI", "Mali", "Malian");

    /* renamed from: MM */
    public static final Country f6200MM = new UnicodeCountry(Configs.BUILD_ID_BASE, "MM", "MMR", "Myanmar [Burma]", "Burmese");

    /* renamed from: MN */
    public static final Country f6201MN = new UnicodeCountry(1174, "MN", "MNG", "Mongolia", "Mongolian");

    /* renamed from: MO */
    public static final Country f6202MO = new UnicodeCountry(1094, "MO", "MAC", "Macau SAR China", "Macanese, Chinese");

    /* renamed from: MP */
    public static final Country f6203MP = new UnicodeCountry(1408, "MP", "MNP", "Northern Mariana Islands", "Northern Marianan");

    /* renamed from: MQ */
    public static final Country f6204MQ = new UnicodeCountry(1140, "MQ", "MTQ", "Martinique", "Martiniquais, Martinican");

    /* renamed from: MR */
    public static final Country f6205MR = new UnicodeCountry(1144, "MR", "MRT", "Mauritania", "Mauritanian");

    /* renamed from: MS */
    public static final Country f6206MS = new UnicodeCountry(1280, "MS", "MSR", "Montserrat", "Montserratian");

    /* renamed from: MT */
    public static final Country f6207MT = new UnicodeCountry(1136, "MT", "MLT", "Malta", "Maltese");

    /* renamed from: MU */
    public static final Country f6208MU = new UnicodeCountry(1152, "MU", "MUS", "Mauritius", "Mauritian");

    /* renamed from: MV */
    public static final Country f6209MV = new UnicodeCountry(1122, "MV", "MDV", "Maldives", "Maldivian");

    /* renamed from: MW */
    public static final Country f6210MW = new UnicodeCountry(1108, "MW", "MWI", "Malawi", "Malawian");

    /* renamed from: MX */
    public static final Country f6211MX = new UnicodeCountry(1156, "MX", "MEX", "Mexico", "Mexican");

    /* renamed from: MY */
    public static final Country f6212MY = new UnicodeCountry(1112, "MY", "MYS", "Malaysia", "Malaysian");

    /* renamed from: MZ */
    public static final Country f6213MZ = new UnicodeCountry(1288, "MZ", "MOZ", "Mozambique", "Mozambican");

    /* renamed from: NA */
    public static final Country f6214NA = new UnicodeCountry(1302, "NA", "NAM", "Namibia", "Namibian");

    /* renamed from: NC */
    public static final Country f6215NC = new UnicodeCountry(1344, "NC", "NCL", "New Caledonia", "New Caledonian");

    /* renamed from: NE */
    public static final Country f6216NE = new UnicodeCountry(1378, "NE", "NER", "Niger", "Nigerien");

    /* renamed from: NF */
    public static final Country f6217NF = new UnicodeCountry(1396, "NF", "NFK", "Norfolk Island", "");

    /* renamed from: NG */
    public static final Country f6218NG = new UnicodeCountry(1382, "NG", "NGA", "Nigeria", "Nigerian");

    /* renamed from: NI */
    public static final Country f6219NI = new UnicodeCountry(1368, "NI", "NIC", "Nicaragua", "Nicaraguan");

    /* renamed from: NL */
    public static final Country f6220NL = new UnicodeCountry(1320, AirbnbConstants.COUNTRY_CODE_NETHERLANDS, "NLD", "Netherlands", "Dutch");

    /* renamed from: NO */
    public static final Country f6221NO = new UnicodeCountry(1400, "NO", "NOR", "Norway", "Norwegian");

    /* renamed from: NP */
    public static final Country f6222NP = new UnicodeCountry(1316, "NP", "NPL", "Nepal", "Nepali");

    /* renamed from: NR */
    public static final Country f6223NR = new UnicodeCountry(1312, "NR", "NRU", "Nauru", "Nauruan");

    /* renamed from: NU */
    public static final Country f6224NU = new UnicodeCountry(1392, "NU", "NIU", "Niue", "Niuean");

    /* renamed from: NZ */
    public static final Country f6225NZ = new UnicodeCountry(1364, "NZ", "NZL", "New Zealand", "");

    /* renamed from: OM */
    public static final Country f6226OM = new UnicodeCountry(1298, "OM", "OMN", "Oman", "Omani");

    /* renamed from: PA */
    public static final Country f6227PA = new UnicodeCountry(1425, "PA", "PAN", "Panama", "Panamanian");

    /* renamed from: PE */
    public static final Country f6228PE = new UnicodeCountry(1540, "PE", "PER", "Peru", "Peruvian");

    /* renamed from: PF */
    public static final Country f6229PF = new UnicodeCountry(600, "PF", "PYF", "French Polynesia", "French Polynesian");

    /* renamed from: PG */
    public static final Country f6230PG = new UnicodeCountry(1432, "PG", "PNG", "Papua New Guinea", "Papua New Guinean, Papuan");

    /* renamed from: PH */
    public static final Country f6231PH = new UnicodeCountry(1544, "PH", "PHL", "Philippines", "Philippine, Filipino");

    /* renamed from: PK */
    public static final Country f6232PK = new UnicodeCountry(1414, "PK", "PAK", "Pakistan", "Pakistani");

    /* renamed from: PL */
    public static final Country f6233PL = new UnicodeCountry(1558, "PL", "POL", "Poland", "Polish");

    /* renamed from: PM */
    public static final Country f6234PM = new UnicodeCountry(1638, "PM", "SPM", "Saint Pierre and Miquelon", "Saint Pierrais, Miquelonnais");

    /* renamed from: PN */
    public static final Country f6235PN = new UnicodeCountry(1554, "PN", "PCN", "Pitcairn Islands", "");

    /* renamed from: PR */
    public static final Country f6236PR = new UnicodeCountry(1584, "PR", "PRI", "Puerto Rico", "Puerto Rican");

    /* renamed from: PS */
    public static final Country f6237PS = new UnicodeCountry(629, "PS", "PSE", "Palestinian Territories", "Palestinian");

    /* renamed from: PT */
    public static final Country f6238PT = new UnicodeCountry(1568, "PT", "PRT", "Portugal", "Portuguese");

    /* renamed from: PW */
    public static final Country f6239PW = new UnicodeCountry(1413, "PW", "PLW", "Palau", "Palauan");

    /* renamed from: PY */
    public static final Country f6240PY = new UnicodeCountry(HelpCenterArticle.COHOSTING_DIFFERENCE_BETWEEN_COHOST_AND_PRIMARY_HOST, "PY", "PRY", "Paraguay", "Paraguayan");

    /* renamed from: QA */
    public static final Country f6241QA = new UnicodeCountry(1588, "QA", "QAT", "Qatar", "Qatari");

    /* renamed from: RE */
    public static final Country f6242RE = new UnicodeCountry(1592, "RE", "REU", "Réunion", "Reunionese, Reunionnais");

    /* renamed from: RO */
    public static final Country f6243RO = new UnicodeCountry(1602, "RO", "ROU", "Romania", "Romanian");

    /* renamed from: RS */
    public static final Country f6244RS = new UnicodeCountry(1672, "RS", "SRB", "Serbia", "Serbian");

    /* renamed from: RU */
    public static final Country f6245RU = new UnicodeCountry(1603, "RU", "RUS", "Russia", "Russian");

    /* renamed from: RW */
    public static final Country f6246RW = new UnicodeCountry(1606, "RW", "RWA", "Rwanda", "Rwandan");

    /* renamed from: SA */
    public static final Country f6247SA = new UnicodeCountry(1666, "SA", "SAU", "Saudi Arabia", "Saudi, Saudi Arabian");

    /* renamed from: SB */
    public static final Country f6248SB = new UnicodeCountry(CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, "SB", "SLB", "Solomon Islands", "Solomon Island");

    /* renamed from: SC */
    public static final Country f6249SC = new UnicodeCountry(1680, BouncyCastleProvider.PROVIDER_NAME, "SYC", "Seychelles", "Seychellois");

    /* renamed from: SD */
    public static final Country f6250SD = new UnicodeCountry(1846, "SD", "SDN", "Sudan", "Sudanese");

    /* renamed from: SE */
    public static final Country f6251SE = new UnicodeCountry(1874, "SE", "SWE", "Sweden", "Swedish");

    /* renamed from: SG */
    public static final Country f6252SG = new UnicodeCountry(1794, "SG", "SGP", "Singapore", "");

    /* renamed from: SH */
    public static final Country f6253SH = new UnicodeCountry(1620, "SH", "SHN", "Saint Helena", "Saint Helenian");

    /* renamed from: SI */
    public static final Country f6254SI = new UnicodeCountry(1797, "SI", "SVN", "Slovenia", "Slovenian, Slovene");

    /* renamed from: SJ */
    public static final Country f6255SJ = new UnicodeCountry(1860, "SJ", "SJM", "Svalbard and Jan Mayen", "");

    /* renamed from: SK */
    public static final Country f6256SK = new UnicodeCountry(1795, "SK", "SVK", "Slovakia", "Slovak");

    /* renamed from: SL */
    public static final Country f6257SL = new UnicodeCountry(1684, "SL", "SLE", "Sierra Leone", "Sierra Leonean");

    /* renamed from: SM */
    public static final Country f6258SM = new UnicodeCountry(1652, "SM", "SMR", "San Marino", "Sammarinese");

    /* renamed from: SN */
    public static final Country f6259SN = new UnicodeCountry(1670, "SN", "SEN", "Senegal", "Senegalese");

    /* renamed from: SO */
    public static final Country f6260SO = new UnicodeCountry(1798, "SO", "SOM", "Somalia", "Somali, Somalian");

    /* renamed from: SR */
    public static final Country f6261SR = new UnicodeCountry(1856, "SR", "SUR", "Suriname", "Surinamese");

    /* renamed from: ST */
    public static final Country f6262ST = new UnicodeCountry(1656, "ST", "STP", "São Tomé & Príncipe", "Sao Tomean");

    /* renamed from: SV */
    public static final Country f6263SV = new UnicodeCountry(546, "SV", "SLV", "El Salvador", "Salvadoran");

    /* renamed from: SY */
    public static final Country f6264SY = new UnicodeCountry(1888, "SY", "SYR", "Syria", "Syrian");

    /* renamed from: SZ */
    public static final Country f6265SZ = new UnicodeCountry(1864, "SZ", "SWZ", "Swaziland", "Swazi");

    /* renamed from: TC */
    public static final Country f6266TC = new UnicodeCountry(1942, "TC", "TCA", "Turks and Caicos Islands", "");

    /* renamed from: TD */
    public static final Country f6267TD = new UnicodeCountry(328, "TD", "TCD", "Chad", "Chadian");

    /* renamed from: TF */
    public static final Country f6268TF = new UnicodeCountry(608, "TF", "ATF", "French Southern Territories", "");

    /* renamed from: TG */
    public static final Country f6269TG = new UnicodeCountry(1896, "TG", "TGO", "Togo", "Togolese");

    /* renamed from: TH */
    public static final Country f6270TH = new UnicodeCountry(1892, "TH", "THA", "Thailand", "Thai");

    /* renamed from: TJ */
    public static final Country f6271TJ = new UnicodeCountry(1890, "TJ", "TJK", "Tajikistan", "Tajikistani");

    /* renamed from: TK */
    public static final Country f6272TK = new UnicodeCountry(1906, "TK", "TKL", "Tokelau", "");

    /* renamed from: TL */
    public static final Country f6273TL = new UnicodeCountry(1574, "TL", "TLS", "Timor-Leste", "Timorese");

    /* renamed from: TM */
    public static final Country f6274TM = new UnicodeCountry(1941, "TM", "TKM", "Turkmenistan", "Turkmen");

    /* renamed from: TN */
    public static final Country f6275TN = new UnicodeCountry(1928, "TN", "TUN", "Tunisia", "Tunisian");

    /* renamed from: TO */
    public static final Country f6276TO = new UnicodeCountry(1910, "TO", "TON", "Tonga", "Tongan");

    /* renamed from: TR */
    public static final Country f6277TR = new UnicodeCountry(1938, "TR", "TUR", "Turkey", "Turkish");

    /* renamed from: TT */
    public static final Country f6278TT = new UnicodeCountry(1920, "TT", "TTO", "Trinidad and Tobago", "Trinidadian, Tobagonian");

    /* renamed from: TV */
    public static final Country f6279TV = new UnicodeCountry(1944, "TV", "TUV", "Tuvalu", "Tuvaluan");

    /* renamed from: TW */
    public static final Country f6280TW = new UnicodeCountry(344, "TW", "TWN", "Taiwan", "Taiwanese");

    /* renamed from: TZ */
    public static final Country f6281TZ = new UnicodeCountry(2100, "TZ", "TZA", "Tanzania", "Tanzanian");

    /* renamed from: UA */
    public static final Country f6282UA = new UnicodeCountry(2052, "UA", "UKR", "Ukraine", "Ukrainian");

    /* renamed from: UG */
    public static final Country f6283UG = new UnicodeCountry(2048, "UG", "UGA", "Uganda", "Ugandan");

    /* renamed from: UM */
    public static final Country f6284UM = new UnicodeCountry(1409, "UM", "UMI", "U.S. Outlying Islands", "");

    /* renamed from: US */
    public static final Country f6285US = new UnicodeCountry(2112, "US", "USA", "United States", "American");

    /* renamed from: UY */
    public static final Country f6286UY = new UnicodeCountry(2136, "UY", "URY", "Uruguay", "Uruguayan");

    /* renamed from: UZ */
    public static final Country f6287UZ = new UnicodeCountry(2144, "UZ", "UZB", "Uzbekistan", "Uzbekistani, Uzbek");

    /* renamed from: VA */
    public static final Country f6288VA = new UnicodeCountry(822, "VA", "VAT", "Vatican City", "");
    private static final Country[] VALUES = {f6056AD, f6057AE, f6058AF, f6059AG, f6060AI, f6061AL, f6062AM, f6063AN, f6064AO, f6065AQ, f6066AR, f6067AS, f6068AT, f6069AU, f6070AW, f6071AX, f6072AZ, f6073BA, f6074BB, f6075BD, f6076BE, f6077BF, f6078BG, f6079BH, f6080BI, f6081BJ, f6082BL, f6083BM, f6084BN, f6085BO, f6086BR, f6087BS, f6088BT, f6089BV, f6090BW, f6091BY, f6092BZ, f6093CA, f6094CC, f6095CD, f6096CF, f6097CG, f6098CH, f6099CI, f6100CK, f6101CL, f6102CM, f6103CN, f6104CO, f6105CR, f6106CU, f6107CV, f6108CX, f6109CY, f6110CZ, f6111DE, f6112DJ, f6113DK, f6114DM, f6115DO, f6116DZ, f6117EC, f6118EE, f6119EG, f6120EH, f6121ER, f6122ES, f6123ET, f6124FI, f6125FJ, f6126FK, f6127FM, f6128FO, f6129FR, f6130GA, f6131GB, f6132GD, f6133GE, f6134GF, f6135GG, f6136GH, f6137GI, f6138GL, f6139GM, f6140GN, f6141GP, f6142GQ, f6143GR, f6144GS, f6145GT, f6146GU, f6147GW, f6148GY, f6149HK, f6150HM, f6151HN, f6152HR, f6153HT, f6154HU, f6155ID, f6156IE, f6157IL, f6158IM, f6159IN, f6160IO, f6161IQ, f6162IR, f6163IS, f6164IT, f6165JE, f6166JM, f6167JO, f6168JP, f6169KE, f6170KG, f6171KH, f6172KI, f6173KM, f6174KN, f6175KP, f6176KR, f6177KW, f6178KY, f6179KZ, f6180LA, f6181LB, f6182LC, f6183LI, f6184LK, f6185LR, f6186LS, f6187LT, f6188LU, f6189LV, f6190LY, f6191MA, f6192MC, f6193MD, f6194ME, f6195MF, f6196MG, f6197MH, f6198MK, f6199ML, f6200MM, f6201MN, f6202MO, f6203MP, f6204MQ, f6205MR, f6206MS, f6207MT, f6208MU, f6209MV, f6210MW, f6211MX, f6212MY, f6213MZ, f6214NA, f6215NC, f6216NE, f6217NF, f6218NG, f6219NI, f6220NL, f6221NO, f6222NP, f6223NR, f6224NU, f6225NZ, f6226OM, f6227PA, f6228PE, f6229PF, f6230PG, f6231PH, f6232PK, f6233PL, f6234PM, f6235PN, f6236PR, f6237PS, f6238PT, f6239PW, f6240PY, f6241QA, f6242RE, f6243RO, f6244RS, f6245RU, f6246RW, f6247SA, f6248SB, f6249SC, f6250SD, f6251SE, f6252SG, f6253SH, f6254SI, f6255SJ, f6256SK, f6257SL, f6258SM, f6259SN, f6260SO, f6261SR, f6262ST, f6263SV, f6264SY, f6265SZ, f6266TC, f6267TD, f6268TF, f6269TG, f6270TH, f6271TJ, f6272TK, f6273TL, f6274TM, f6275TN, f6276TO, f6277TR, f6278TT, f6279TV, f6280TW, f6281TZ, f6282UA, f6283UG, f6284UM, f6285US, f6286UY, f6287UZ, f6288VA, f6289VC, f6290VE, f6291VG, f6292VI, f6293VN, f6294VU, f6295WF, f6296WS, f6297YE, f6298YT, f6299ZA, f6300ZM, f6301ZW};

    /* renamed from: VC */
    public static final Country f6289VC = new UnicodeCountry(1648, "VC", "VCT", "Saint Vincent and the Grenadines", "Saint Vincentian");

    /* renamed from: VE */
    public static final Country f6290VE = new UnicodeCountry(2146, "VE", "VEN", "Venezuela", "Venezuelan");

    /* renamed from: VG */
    public static final Country f6291VG = new UnicodeCountry(CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, "VG", "VGB", "British Virgin Islands", "Virgin Island");

    /* renamed from: VI */
    public static final Country f6292VI = new UnicodeCountry(2128, "VI", "VIR", "U.S. Virgin Islands", "Virgin Island");

    /* renamed from: VN */
    public static final Country f6293VN = new UnicodeCountry(1796, "VN", "VNM", "Vietnam", "Vietnamese");

    /* renamed from: VU */
    public static final Country f6294VU = new UnicodeCountry(1352, "VU", "VUT", "Vanuatu", "Ni-Vanuatu, Vanuatuan");

    /* renamed from: WF */
    public static final Country f6295WF = new UnicodeCountry(2166, "WF", "WLF", "Wallis and Futuna", "Wallisian, Futunan");

    /* renamed from: WS */
    public static final Country f6296WS = new UnicodeCountry(2178, "WS", "WSM", "Samoa", "Samoan");

    /* renamed from: YE */
    public static final Country f6297YE = new UnicodeCountry(2183, "YE", "YEM", "Yemen", "Yemeni");

    /* renamed from: YT */
    public static final Country f6298YT = new UnicodeCountry(373, "YT", "MYT", "Mayotte", "Mahoran");

    /* renamed from: ZA */
    public static final Country f6299ZA = new UnicodeCountry(1808, "ZA", "ZAF", "South Africa", "South African");

    /* renamed from: ZM */
    public static final Country f6300ZM = new UnicodeCountry(2196, "ZM", "ZMB", "Zambia", "Zambian");

    /* renamed from: ZW */
    public static final Country f6301ZW = new UnicodeCountry(1814, "ZW", "ZWE", "Zimbabwe", "Zimbabwean");
    private static final long serialVersionUID = 7220597933847617853L;
    private String alpha2Code;
    private String alpha3Code;
    private int code;
    private String name;
    private String nationality;

    private UnicodeCountry(int i, String str, String str2, String str3) {
        this(i, str, str2, str3, str3);
    }

    private UnicodeCountry(int i, String str, String str2, String str3, String str4) {
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
        return obj != null && obj.getClass().equals(getClass()) && ((UnicodeCountry) obj).code == this.code;
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
