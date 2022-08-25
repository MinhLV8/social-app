package ch.spz.ep.backend.service.auth.core;

import static ch.spz.ep.backend.Defaults.ROOT_ID;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.isNull;

import ch.spz.ep.backend.storage.AbstractCompartedEntity;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.annotation.Nonnull;

/**
 * @author holgerstolzenberg
 * @since 0.1.1-5
 */
public final class AuthContext {
  static final int AUTH_CONTEXT_FIELDS = 5;

  private final long userId;
  private final String username;
  private final long clientId;
  private final String salt;
  private final boolean root;

  @SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
  private AuthContext(final Builder builder) {
    this.userId = builder.userId;
    this.username = builder.username;
    this.clientId = builder.clientId;
    this.salt = builder.salt;
    this.root = builder.root;
  }

  @Nonnull
  public static Builder newBuilder() {
    return new Builder();
  }

  public long getUserId() {
    return userId;
  }

  @Nonnull
  public String getUsername() {
    return username;
  }

  public long getClientId() {
    return clientId;
  }

  public boolean isRoot() {
    return root;
  }

  @Nonnull
  String getSalt() {
    return salt;
  }

  public void ensureClientId(@Nonnull final AbstractCompartedEntity entity) {
    if (isNull(entity.getClientId())) {
      entity.setClientId(clientId);
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o)
      return true;
    if ((o == null) || (getClass() != o.getClass()))
      return false;
    final AuthContext that = (AuthContext) o;
    return new EqualsBuilder().append(userId, that.getUserId())
        .append(username, that.getUsername())
        .append(clientId, that.getClientId())
        .append(salt, that.getSalt())
        .append(root, that.isRoot())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getUserId(), getUsername(), getClientId(), salt);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("userId", userId)
        .add("username", username)
        .add("clientId", clientId)
        .toString();
  }

  public static final class Builder {
    private long userId;
    private String username;
    private long clientId;
    private String salt;
    private boolean root;

    private Builder() {
    }

    @Nonnull
    public final Builder userId(final long value) {
      checkArgument(value >= ROOT_ID);
      this.userId = value;
      return this;
    }

    @Nonnull
    public final Builder username(@Nonnull final String value) {
      checkArgument(!isNullOrEmpty(value));
      this.username = value;
      return this;
    }

    @Nonnull
    public final Builder clientId(final long value) {
      checkArgument(value >= ROOT_ID);
      this.clientId = value;
      return this;
    }

    @Nonnull
    public final Builder salt(@Nonnull final String value) {
      checkArgument(!isNullOrEmpty(value));
      this.salt = value;
      return this;
    }

    @Nonnull
    public final Builder root(final boolean value) {
      this.root = value;
      return this;
    }

    @Nonnull
    public final AuthContext build() {
      return new AuthContext(this);
    }
  }
}
