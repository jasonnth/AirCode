package org.spongycastle.i18n.filter;

public class SQLFilter implements Filter {
    public String doFilter(String input) {
        StringBuffer buf = new StringBuffer(input);
        int i = 0;
        while (i < buf.length()) {
            switch (buf.charAt(i)) {
                case 10:
                    buf.replace(i, i + 1, "\\n");
                    i++;
                    break;
                case 13:
                    buf.replace(i, i + 1, "\\r");
                    i++;
                    break;
                case '\"':
                    buf.replace(i, i + 1, "\\\"");
                    i++;
                    break;
                case '\'':
                    buf.replace(i, i + 1, "\\'");
                    i++;
                    break;
                case '-':
                    buf.replace(i, i + 1, "\\-");
                    i++;
                    break;
                case '/':
                    buf.replace(i, i + 1, "\\/");
                    i++;
                    break;
                case ';':
                    buf.replace(i, i + 1, "\\;");
                    i++;
                    break;
                case '=':
                    buf.replace(i, i + 1, "\\=");
                    i++;
                    break;
                case '\\':
                    buf.replace(i, i + 1, "\\\\");
                    i++;
                    break;
            }
            i++;
        }
        return buf.toString();
    }

    public String doFilterUrl(String input) {
        return doFilter(input);
    }
}
