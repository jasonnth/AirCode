package com.squareup.sqldelight;

import java.util.Set;

public class SqlDelightStatement {
    public final String[] args;
    public final String statement;
    public final Set<String> tables;

    public SqlDelightStatement(String statement2, String[] args2, Set<String> tables2) {
        this.statement = statement2;
        this.args = args2;
        this.tables = tables2;
    }
}
