package org.apache.commons.math3.exception.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ExceptionContext implements Serializable {
    private Map<String, Object> context = new HashMap();
    private List<Object[]> msgArguments = new ArrayList();
    private List<Localizable> msgPatterns = new ArrayList();
    private Throwable throwable;

    public ExceptionContext(Throwable throwable2) {
        this.throwable = throwable2;
    }

    public void addMessage(Localizable pattern, Object... arguments) {
        this.msgPatterns.add(pattern);
        this.msgArguments.add(ArgUtils.flatten(arguments));
    }

    public String getMessage() {
        return getMessage(Locale.US);
    }

    public String getLocalizedMessage() {
        return getMessage(Locale.getDefault());
    }

    public String getMessage(Locale locale) {
        return buildMessage(locale, ": ");
    }

    private String buildMessage(Locale locale, String separator) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int len = this.msgPatterns.size();
        for (int i = 0; i < len; i++) {
            Object[] args = (Object[]) this.msgArguments.get(i);
            sb.append(new MessageFormat(((Localizable) this.msgPatterns.get(i)).getLocalizedString(locale), locale).format(args));
            count++;
            if (count < len) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(this.throwable);
        serializeMessages(out);
        serializeContext(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.throwable = (Throwable) in.readObject();
        deSerializeMessages(in);
        deSerializeContext(in);
    }

    private void serializeMessages(ObjectOutputStream out) throws IOException {
        int len = this.msgPatterns.size();
        out.writeInt(len);
        for (int i = 0; i < len; i++) {
            out.writeObject((Localizable) this.msgPatterns.get(i));
            Object[] args = (Object[]) this.msgArguments.get(i);
            int aLen = args.length;
            out.writeInt(aLen);
            for (int j = 0; j < aLen; j++) {
                if (args[j] instanceof Serializable) {
                    out.writeObject(args[j]);
                } else {
                    out.writeObject(nonSerializableReplacement(args[j]));
                }
            }
        }
    }

    private void deSerializeMessages(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int len = in.readInt();
        this.msgPatterns = new ArrayList(len);
        this.msgArguments = new ArrayList(len);
        for (int i = 0; i < len; i++) {
            this.msgPatterns.add((Localizable) in.readObject());
            int aLen = in.readInt();
            Object[] args = new Object[aLen];
            for (int j = 0; j < aLen; j++) {
                args[j] = in.readObject();
            }
            this.msgArguments.add(args);
        }
    }

    private void serializeContext(ObjectOutputStream out) throws IOException {
        out.writeInt(this.context.keySet().size());
        for (String key : this.context.keySet()) {
            out.writeObject(key);
            Object value = this.context.get(key);
            if (value instanceof Serializable) {
                out.writeObject(value);
            } else {
                out.writeObject(nonSerializableReplacement(value));
            }
        }
    }

    private void deSerializeContext(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int len = in.readInt();
        this.context = new HashMap();
        for (int i = 0; i < len; i++) {
            this.context.put((String) in.readObject(), in.readObject());
        }
    }

    private String nonSerializableReplacement(Object obj) {
        return "[Object could not be serialized: " + obj.getClass().getName() + "]";
    }
}
