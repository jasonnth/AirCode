package org.spongycastle.i18n;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;
import org.spongycastle.i18n.filter.Filter;
import org.spongycastle.i18n.filter.TrustedInput;
import org.spongycastle.i18n.filter.UntrustedInput;
import org.spongycastle.i18n.filter.UntrustedUrlInput;

public class LocalizedMessage {
    public static final String DEFAULT_ENCODING = "ISO-8859-1";
    protected FilteredArguments arguments;
    protected String encoding = DEFAULT_ENCODING;
    protected FilteredArguments extraArgs = null;
    protected Filter filter = null;

    /* renamed from: id */
    protected final String f6893id;
    protected ClassLoader loader = null;
    protected final String resource;

    protected class FilteredArguments {
        protected static final int FILTER = 1;
        protected static final int FILTER_URL = 2;
        protected static final int NO_FILTER = 0;
        protected int[] argFilterType;
        protected Object[] arguments;
        protected Filter filter;
        protected Object[] filteredArgs;
        protected boolean[] isLocaleSpecific;
        protected Object[] unpackedArgs;

        FilteredArguments(LocalizedMessage this$02) {
            this(new Object[0]);
        }

        FilteredArguments(Object[] args) {
            this.filter = null;
            this.arguments = args;
            this.unpackedArgs = new Object[args.length];
            this.filteredArgs = new Object[args.length];
            this.isLocaleSpecific = new boolean[args.length];
            this.argFilterType = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof TrustedInput) {
                    this.unpackedArgs[i] = args[i].getInput();
                    this.argFilterType[i] = 0;
                } else if (args[i] instanceof UntrustedInput) {
                    this.unpackedArgs[i] = args[i].getInput();
                    if (args[i] instanceof UntrustedUrlInput) {
                        this.argFilterType[i] = 2;
                    } else {
                        this.argFilterType[i] = 1;
                    }
                } else {
                    this.unpackedArgs[i] = args[i];
                    this.argFilterType[i] = 1;
                }
                this.isLocaleSpecific[i] = this.unpackedArgs[i] instanceof LocaleString;
            }
        }

        public boolean isEmpty() {
            return this.unpackedArgs.length == 0;
        }

        public Object[] getArguments() {
            return this.arguments;
        }

        public Object[] getFilteredArgs(Locale locale) {
            Object arg;
            Object[] result = new Object[this.unpackedArgs.length];
            for (int i = 0; i < this.unpackedArgs.length; i++) {
                if (this.filteredArgs[i] != null) {
                    arg = this.filteredArgs[i];
                } else {
                    Object arg2 = this.unpackedArgs[i];
                    if (this.isLocaleSpecific[i]) {
                        arg = filter(this.argFilterType[i], ((LocaleString) arg2).getLocaleString(locale));
                    } else {
                        arg = filter(this.argFilterType[i], arg2);
                        this.filteredArgs[i] = arg;
                    }
                }
                result[i] = arg;
            }
            return result;
        }

        private Object filter(int type, Object obj) {
            if (this.filter == null) {
                return obj;
            }
            Object o = obj == null ? "null" : obj;
            switch (type) {
                case 0:
                    return o;
                case 1:
                    return this.filter.doFilter(o.toString());
                case 2:
                    return this.filter.doFilterUrl(o.toString());
                default:
                    return null;
            }
        }

        public Filter getFilter() {
            return this.filter;
        }

        public void setFilter(Filter filter2) {
            if (filter2 != this.filter) {
                for (int i = 0; i < this.unpackedArgs.length; i++) {
                    this.filteredArgs[i] = null;
                }
            }
            this.filter = filter2;
        }
    }

    public LocalizedMessage(String resource2, String id) throws NullPointerException {
        if (resource2 == null || id == null) {
            throw new NullPointerException();
        }
        this.f6893id = id;
        this.resource = resource2;
        this.arguments = new FilteredArguments(this);
    }

    public LocalizedMessage(String resource2, String id, String encoding2) throws NullPointerException, UnsupportedEncodingException {
        if (resource2 == null || id == null) {
            throw new NullPointerException();
        }
        this.f6893id = id;
        this.resource = resource2;
        this.arguments = new FilteredArguments(this);
        if (!Charset.isSupported(encoding2)) {
            throw new UnsupportedEncodingException("The encoding \"" + encoding2 + "\" is not supported.");
        }
        this.encoding = encoding2;
    }

    public LocalizedMessage(String resource2, String id, Object[] arguments2) throws NullPointerException {
        if (resource2 == null || id == null || arguments2 == null) {
            throw new NullPointerException();
        }
        this.f6893id = id;
        this.resource = resource2;
        this.arguments = new FilteredArguments(arguments2);
    }

    public LocalizedMessage(String resource2, String id, String encoding2, Object[] arguments2) throws NullPointerException, UnsupportedEncodingException {
        if (resource2 == null || id == null || arguments2 == null) {
            throw new NullPointerException();
        }
        this.f6893id = id;
        this.resource = resource2;
        this.arguments = new FilteredArguments(arguments2);
        if (!Charset.isSupported(encoding2)) {
            throw new UnsupportedEncodingException("The encoding \"" + encoding2 + "\" is not supported.");
        }
        this.encoding = encoding2;
    }

    public String getEntry(String key, Locale loc, TimeZone timezone) throws MissingEntryException {
        ClassLoader classLoader;
        ResourceBundle bundle;
        String entry = this.f6893id;
        if (key != null) {
            entry = entry + "." + key;
        }
        try {
            if (this.loader == null) {
                bundle = ResourceBundle.getBundle(this.resource, loc);
            } else {
                bundle = ResourceBundle.getBundle(this.resource, loc, this.loader);
            }
            String result = bundle.getString(entry);
            if (!this.encoding.equals(DEFAULT_ENCODING)) {
                result = new String(result.getBytes(DEFAULT_ENCODING), this.encoding);
            }
            if (!this.arguments.isEmpty()) {
                result = formatWithTimeZone(result, this.arguments.getFilteredArgs(loc), loc, timezone);
            }
            return addExtraArgs(result, loc);
        } catch (MissingResourceException e) {
            String str = "Can't find entry " + entry + " in resource file " + this.resource + ".";
            String str2 = this.resource;
            if (this.loader != null) {
                classLoader = this.loader;
            } else {
                classLoader = getClassLoader();
            }
            throw new MissingEntryException(str, str2, entry, loc, classLoader);
        } catch (UnsupportedEncodingException use) {
            throw new RuntimeException(use);
        }
    }

    /* access modifiers changed from: protected */
    public String formatWithTimeZone(String template, Object[] arguments2, Locale locale, TimeZone timezone) {
        MessageFormat mf = new MessageFormat(" ");
        mf.setLocale(locale);
        mf.applyPattern(template);
        if (!timezone.equals(TimeZone.getDefault())) {
            Format[] formats = mf.getFormats();
            for (int i = 0; i < formats.length; i++) {
                if (formats[i] instanceof DateFormat) {
                    DateFormat temp = (DateFormat) formats[i];
                    temp.setTimeZone(timezone);
                    mf.setFormat(i, temp);
                }
            }
        }
        return mf.format(arguments2);
    }

    /* access modifiers changed from: protected */
    public String addExtraArgs(String msg, Locale locale) {
        if (this.extraArgs == null) {
            return msg;
        }
        StringBuffer sb = new StringBuffer(msg);
        Object[] filteredArgs = this.extraArgs.getFilteredArgs(locale);
        for (Object append : filteredArgs) {
            sb.append(append);
        }
        return sb.toString();
    }

    public void setFilter(Filter filter2) {
        this.arguments.setFilter(filter2);
        if (this.extraArgs != null) {
            this.extraArgs.setFilter(filter2);
        }
        this.filter = filter2;
    }

    public Filter getFilter() {
        return this.filter;
    }

    public void setClassLoader(ClassLoader loader2) {
        this.loader = loader2;
    }

    public ClassLoader getClassLoader() {
        return this.loader;
    }

    public String getId() {
        return this.f6893id;
    }

    public String getResource() {
        return this.resource;
    }

    public Object[] getArguments() {
        return this.arguments.getArguments();
    }

    public void setExtraArgument(Object extraArg) {
        setExtraArguments(new Object[]{extraArg});
    }

    public void setExtraArguments(Object[] extraArgs2) {
        if (extraArgs2 != null) {
            this.extraArgs = new FilteredArguments(extraArgs2);
            this.extraArgs.setFilter(this.filter);
            return;
        }
        this.extraArgs = null;
    }

    public Object[] getExtraArgs() {
        if (this.extraArgs == null) {
            return null;
        }
        return this.extraArgs.getArguments();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Resource: \"").append(this.resource);
        sb.append("\" Id: \"").append(this.f6893id).append("\"");
        sb.append(" Arguments: ").append(this.arguments.getArguments().length).append(" normal");
        if (this.extraArgs != null && this.extraArgs.getArguments().length > 0) {
            sb.append(", ").append(this.extraArgs.getArguments().length).append(" extra");
        }
        sb.append(" Encoding: ").append(this.encoding);
        sb.append(" ClassLoader: ").append(this.loader);
        return sb.toString();
    }
}
