package com.squareup.sqldelight;

import android.database.sqlite.SQLiteStatement;

public abstract class SqlDelightCompiledStatement {
    public final SQLiteStatement program;
    public final String table;

    public static abstract class Delete extends SqlDelightCompiledStatement {
        protected Delete(String table, SQLiteStatement program) {
            super(table, program);
        }
    }

    public static abstract class Insert extends SqlDelightCompiledStatement {
        protected Insert(String table, SQLiteStatement program) {
            super(table, program);
        }
    }

    public static abstract class Update extends SqlDelightCompiledStatement {
        protected Update(String table, SQLiteStatement program) {
            super(table, program);
        }
    }

    protected SqlDelightCompiledStatement(String table2, SQLiteStatement program2) {
        this.table = table2;
        this.program = program2;
    }
}
