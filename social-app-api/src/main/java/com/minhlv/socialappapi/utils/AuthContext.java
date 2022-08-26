package com.minhlv.socialappapi.utils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.lang.NonNull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public final class AuthContext {
    static final int AUTH_CONTEXT_FIELDS = 5;

    private final long userId;
    private final String username;
    private final long clientId;
    private final String salt;
    private final boolean root;

    private AuthContext(final Builder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.clientId = builder.clientId;
        this.salt = builder.salt;
        this.root = builder.root;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    public long getUserId() {
        return userId;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public long getClientId() {
        return clientId;
    }

    public boolean isRoot() {
        return root;
    }

    @NonNull
    String getSalt() {
        return salt;
    }

    /*
     * public void ensureClientId(@NonNull final AbstractCompartedEntity entity)
     * { if (isNull(entity.getClientId())) { entity.setClientId(clientId); } }
     */

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        final AuthContext that = (AuthContext) o;
        return new EqualsBuilder().append(userId, that.getUserId()).append(username, that.getUsername())
                .append(clientId, that.getClientId()).append(salt, that.getSalt()).append(root, that.isRoot())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUserId(), getUsername(), getClientId(), salt);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("userId", userId).add("username", username)
                .add("clientId", clientId).toString();
    }

    public static final class Builder {
        private long userId;
        private String username;
        private long clientId;
        private String salt;
        private boolean root;

        private Builder() {
        }

        @NonNull
        public final Builder userId(final long value) {
            // checkArgument(value >= ROOT_ID);
            this.userId = value;
            return this;
        }

        @NonNull
        public final Builder username(@NonNull final String value) {
            checkArgument(!isNullOrEmpty(value));
            this.username = value;
            return this;
        }

        @NonNull
        public final Builder clientId(final long value) {
            // checkArgument(value >= ROOT_ID);
            this.clientId = value;
            return this;
        }

        @NonNull
        public final Builder salt(@NonNull final String value) {
            checkArgument(!isNullOrEmpty(value));
            this.salt = value;
            return this;
        }

        @NonNull
        public final Builder root(final boolean value) {
            this.root = value;
            return this;
        }

        @NonNull
        public final AuthContext build() {
            return new AuthContext(this);
        }
    }
}
