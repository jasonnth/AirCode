package com.airbnb.android.superhero;

class MessageDiffResult {
    final Status diffResult;
    final SuperHeroMessage localMessage;
    final SuperHeroMessage serverMessage;

    enum Status {
        ADD,
        REMOVE,
        CONFLICT
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageDiffResult result = (MessageDiffResult) o;
        if (this.localMessage != null) {
            if (!this.localMessage.equals(result.localMessage)) {
                return false;
            }
        } else if (result.localMessage != null) {
            return false;
        }
        if (this.serverMessage != null) {
            if (!this.serverMessage.equals(result.serverMessage)) {
                return false;
            }
        } else if (result.serverMessage != null) {
            return false;
        }
        if (this.diffResult != result.diffResult) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int i = 0;
        if (this.localMessage != null) {
            result = this.localMessage.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.serverMessage != null) {
            i = this.serverMessage.hashCode();
        }
        return ((i2 + i) * 31) + this.diffResult.hashCode();
    }

    public String toString() {
        return "MessageDiffResult{localMessage=" + this.localMessage + ", serverMessage=" + this.serverMessage + ", diffResult=" + this.diffResult + '}';
    }

    MessageDiffResult(SuperHeroMessage localMessage2, SuperHeroMessage serverMessage2, Status diffResult2) {
        this.localMessage = localMessage2;
        this.serverMessage = serverMessage2;
        this.diffResult = diffResult2;
    }
}
