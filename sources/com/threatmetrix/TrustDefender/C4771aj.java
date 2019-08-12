package com.threatmetrix.TrustDefender;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.threatmetrix.TrustDefender.aj */
class C4771aj {

    /* renamed from: h */
    private static final String f4220h = C4834w.m2892a(C4771aj.class);

    /* renamed from: a */
    public long f4221a = 0;

    /* renamed from: b */
    public long f4222b = 0;

    /* renamed from: c */
    public String f4223c = "";

    /* renamed from: d */
    public final ArrayList<String> f4224d = new ArrayList<>();

    /* renamed from: e */
    public final ArrayList<URI> f4225e = new ArrayList<>();

    /* renamed from: f */
    public String f4226f = "";

    /* renamed from: g */
    public int f4227g = 0;

    C4771aj() {
    }

    /* renamed from: a */
    private void m2639a(XmlPullParser xpp) throws XmlPullParserException, IOException {
        int eventType = xpp.next();
        String currentTag = "";
        while (eventType != 1) {
            switch (eventType) {
                case 0:
                    String str = f4220h;
                    break;
                case 2:
                    currentTag = xpp.getName();
                    break;
                case 3:
                    if (!xpp.getName().equals("PS")) {
                        break;
                    } else {
                        return;
                    }
                case 4:
                    if (currentTag != null) {
                        if (!currentTag.equals("P")) {
                            String str2 = f4220h;
                            new StringBuilder("Found tag content unexpectedly: ").append(currentTag);
                            break;
                        } else {
                            this.f4224d.add(xpp.getText());
                            break;
                        }
                    } else {
                        break;
                    }
                default:
                    String str3 = f4220h;
                    new StringBuilder("Found unexpected event type: ").append(eventType);
                    break;
            }
            eventType = xpp.next();
        }
    }

    /* renamed from: b */
    private void m2640b(XmlPullParser xpp) throws XmlPullParserException, IOException {
        int eventType = xpp.next();
        String currentTag = "";
        while (eventType != 1) {
            switch (eventType) {
                case 0:
                    String str = f4220h;
                    break;
                case 2:
                    currentTag = xpp.getName();
                    break;
                case 3:
                    if (!xpp.getName().equals("EX")) {
                        break;
                    } else {
                        return;
                    }
                case 4:
                    if (currentTag != null) {
                        if (!currentTag.equals("E")) {
                            String str2 = f4220h;
                            new StringBuilder("Found tag content unexpectedly: ").append(currentTag);
                            break;
                        } else {
                            try {
                                this.f4225e.add(new URI(xpp.getText()));
                                break;
                            } catch (URISyntaxException e) {
                                String str3 = f4220h;
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                default:
                    C4834w.m2901c(f4220h, "Found unexpected event type: " + eventType);
                    break;
            }
            eventType = xpp.next();
        }
    }

    /* renamed from: a */
    public final boolean mo45970a() {
        return this.f4223c != null && !this.f4223c.isEmpty();
    }

    /* renamed from: a */
    public final void mo45969a(InputStream in) {
        String currentTag = null;
        if (in != null) {
            try {
                XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
                xpp.setInput(new InputStreamReader(in));
                for (int eventType = xpp.getEventType(); eventType != 1; eventType = xpp.next()) {
                    switch (eventType) {
                        case 2:
                            if (!xpp.getName().equals("PS")) {
                                if (!xpp.getName().equals("EX")) {
                                    currentTag = xpp.getName();
                                    break;
                                } else {
                                    m2640b(xpp);
                                    break;
                                }
                            } else {
                                m2639a(xpp);
                                break;
                            }
                        case 3:
                            currentTag = null;
                            break;
                        case 4:
                            if (currentTag != null) {
                                if (!currentTag.equals("w")) {
                                    if (!currentTag.equals("O")) {
                                        if (!currentTag.equals("D")) {
                                            if (!currentTag.equals("cpath")) {
                                                if (!currentTag.equals("Q")) {
                                                    break;
                                                } else {
                                                    this.f4227g = Integer.valueOf(xpp.getText()).intValue();
                                                    break;
                                                }
                                            } else {
                                                this.f4226f = xpp.getText();
                                                break;
                                            }
                                        } else {
                                            this.f4222b = Long.valueOf(xpp.getText()).longValue();
                                            break;
                                        }
                                    } else {
                                        this.f4221a = Long.valueOf(xpp.getText()).longValue();
                                        break;
                                    }
                                } else {
                                    this.f4223c = xpp.getText();
                                    break;
                                }
                            } else {
                                break;
                            }
                    }
                }
            } catch (IOException e) {
                String str = f4220h;
            } catch (XmlPullParserException e2) {
                C4834w.m2895a(f4220h, "XML Parse Error", (Throwable) e2);
            }
        }
    }
}
