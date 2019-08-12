package org.apache.sanselan;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class FormatCompliance {
    private final ArrayList comments = new ArrayList();
    private final String description;
    private final boolean failOnError;

    public FormatCompliance(String description2, boolean fail_on_error) {
        this.description = description2;
        this.failOnError = fail_on_error;
    }

    public static final FormatCompliance getDefault() {
        return new FormatCompliance("ignore", false);
    }

    public String toString() {
        StringWriter sw = new StringWriter();
        dump(new PrintWriter(sw));
        return sw.getBuffer().toString();
    }

    public void dump(PrintWriter pw) {
        pw.println("Format Compliance: " + this.description);
        if (this.comments.size() == 0) {
            pw.println("\tNo comments.");
        } else {
            for (int i = 0; i < this.comments.size(); i++) {
                pw.println("\t" + (i + 1) + ": " + this.comments.get(i));
            }
        }
        pw.println("");
        pw.flush();
    }
}
