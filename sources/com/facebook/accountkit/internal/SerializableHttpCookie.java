package com.facebook.accountkit.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.HttpCookie;

final class SerializableHttpCookie implements Serializable {
    private static final long serialVersionUID = 2064381394822046912L;
    private transient HttpCookie cookie;
    private Field fieldHttpOnly;

    public String encode(HttpCookie cookie2) {
        this.cookie = cookie2;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(os).writeObject(this);
            return byteArrayToHexString(os.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }

    public HttpCookie decode(String encodedCookie) {
        HttpCookie cookie2 = null;
        try {
            return ((SerializableHttpCookie) new ObjectInputStream(new ByteArrayInputStream(hexStringToByteArray(encodedCookie))).readObject()).cookie;
        } catch (IOException | ClassNotFoundException e) {
            return cookie2;
        }
    }

    private boolean getHttpOnly() {
        try {
            initFieldHttpOnly();
            return ((Boolean) this.fieldHttpOnly.get(this.cookie)).booleanValue();
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
            return false;
        }
    }

    private void setHttpOnly(boolean httpOnly) {
        try {
            initFieldHttpOnly();
            this.fieldHttpOnly.set(this.cookie, Boolean.valueOf(httpOnly));
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
        }
    }

    private void initFieldHttpOnly() throws NoSuchFieldException {
        this.fieldHttpOnly = this.cookie.getClass().getDeclaredField("httpOnly");
        this.fieldHttpOnly.setAccessible(true);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(this.cookie.getName());
        out.writeObject(this.cookie.getValue());
        out.writeObject(this.cookie.getComment());
        out.writeObject(this.cookie.getCommentURL());
        out.writeObject(this.cookie.getDomain());
        out.writeLong(this.cookie.getMaxAge());
        out.writeObject(this.cookie.getPath());
        out.writeObject(this.cookie.getPortlist());
        out.writeInt(this.cookie.getVersion());
        out.writeBoolean(this.cookie.getSecure());
        out.writeBoolean(this.cookie.getDiscard());
        out.writeBoolean(getHttpOnly());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.cookie = new HttpCookie((String) in.readObject(), (String) in.readObject());
        this.cookie.setComment((String) in.readObject());
        this.cookie.setCommentURL((String) in.readObject());
        this.cookie.setDomain((String) in.readObject());
        this.cookie.setMaxAge(in.readLong());
        this.cookie.setPath((String) in.readObject());
        this.cookie.setPortlist((String) in.readObject());
        this.cookie.setVersion(in.readInt());
        this.cookie.setSecure(in.readBoolean());
        this.cookie.setDiscard(in.readBoolean());
        setHttpOnly(in.readBoolean());
    }

    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 255;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    private byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[(len / 2)];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}
