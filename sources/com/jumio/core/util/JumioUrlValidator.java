package com.jumio.core.util;

import com.facebook.common.util.UriUtil;
import com.jumio.commons.validation.InetAddressValidator;
import com.jumio.commons.validation.UrlValidator;
import java.util.regex.Pattern;

public class JumioUrlValidator extends UrlValidator {
    private static final Pattern PATH_PATTERN = Pattern.compile("^(([-/\\w:@&?=+,.!~*'$_;]|(%[0-9a-fA-F]{2}))*)?$");
    private static final Pattern QUERY_PATTERN = Pattern.compile("^(([-\\w:@&?=+,.!~*'$_;]|(%[0-9a-fA-F]{2}))*)?$");
    private static final String[] VALID_SCHEMES = {UriUtil.HTTPS_SCHEME, UriUtil.HTTP_SCHEME};
    private static final long serialVersionUID = 421988208904137814L;
    private boolean allowIPs;
    private boolean allowPorts;
    private boolean allowQuery;
    private boolean allowUnicode;

    public JumioUrlValidator() {
        this(VALID_SCHEMES, true, true, true, true);
    }

    public JumioUrlValidator(String[] schemas, boolean allowUnicode2, boolean allowIPs2, boolean allowPorts2, boolean allowQuery2) {
        super(schemas, 4);
        this.allowPorts = true;
        this.allowIPs = true;
        this.allowUnicode = true;
        this.allowQuery = true;
        this.allowUnicode = allowUnicode2;
        this.allowIPs = allowIPs2;
        this.allowPorts = allowPorts2;
        this.allowQuery = allowQuery2;
    }

    /* access modifiers changed from: protected */
    public boolean isValidAuthority(String authority) {
        if (!this.allowIPs && InetAddressValidator.getInstance().isValid(authority)) {
            return false;
        }
        if ((!this.allowUnicode && !isOnlyASCII(authority)) || !super.isValidAuthority(authority)) {
            return false;
        }
        if (this.allowPorts || authority.split(":").length < 2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isValidQuery(String query) {
        return query == null || (this.allowQuery && super.isValidQuery(query) && QUERY_PATTERN.matcher(query).matches());
    }

    /* access modifiers changed from: protected */
    public boolean isValidPath(String path) {
        return path == null || (super.isValidPath(path) && PATH_PATTERN.matcher(path).matches());
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
