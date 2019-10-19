// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: names.proto

package com.prod.app.protobuff;

public final class Names {
  private Names() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface NamesPbOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.prod.app.protobuff.NamesPb)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string firstName = 1;</code>
     */
    java.lang.String getFirstName();
    /**
     * <code>string firstName = 1;</code>
     */
    com.google.protobuf.ByteString
        getFirstNameBytes();

    /**
     * <code>string lastName = 2;</code>
     */
    java.lang.String getLastName();
    /**
     * <code>string lastName = 2;</code>
     */
    com.google.protobuf.ByteString
        getLastNameBytes();

    /**
     * <code>string canonicalName = 3;</code>
     */
    java.lang.String getCanonicalName();
    /**
     * <code>string canonicalName = 3;</code>
     */
    com.google.protobuf.ByteString
        getCanonicalNameBytes();
  }
  /**
   * Protobuf type {@code com.prod.app.protobuff.NamesPb}
   */
  public  static final class NamesPb extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.prod.app.protobuff.NamesPb)
      NamesPbOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use NamesPb.newBuilder() to construct.
    private NamesPb(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private NamesPb() {
      firstName_ = "";
      lastName_ = "";
      canonicalName_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new NamesPb();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private NamesPb(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              firstName_ = s;
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              lastName_ = s;
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              canonicalName_ = s;
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.prod.app.protobuff.Names.internal_static_com_prod_app_protobuff_NamesPb_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.prod.app.protobuff.Names.internal_static_com_prod_app_protobuff_NamesPb_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.prod.app.protobuff.Names.NamesPb.class, com.prod.app.protobuff.Names.NamesPb.Builder.class);
    }

    public static final int FIRSTNAME_FIELD_NUMBER = 1;
    private volatile java.lang.Object firstName_;
    /**
     * <code>string firstName = 1;</code>
     */
    public java.lang.String getFirstName() {
      java.lang.Object ref = firstName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        firstName_ = s;
        return s;
      }
    }
    /**
     * <code>string firstName = 1;</code>
     */
    public com.google.protobuf.ByteString
        getFirstNameBytes() {
      java.lang.Object ref = firstName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        firstName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int LASTNAME_FIELD_NUMBER = 2;
    private volatile java.lang.Object lastName_;
    /**
     * <code>string lastName = 2;</code>
     */
    public java.lang.String getLastName() {
      java.lang.Object ref = lastName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        lastName_ = s;
        return s;
      }
    }
    /**
     * <code>string lastName = 2;</code>
     */
    public com.google.protobuf.ByteString
        getLastNameBytes() {
      java.lang.Object ref = lastName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        lastName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int CANONICALNAME_FIELD_NUMBER = 3;
    private volatile java.lang.Object canonicalName_;
    /**
     * <code>string canonicalName = 3;</code>
     */
    public java.lang.String getCanonicalName() {
      java.lang.Object ref = canonicalName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        canonicalName_ = s;
        return s;
      }
    }
    /**
     * <code>string canonicalName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getCanonicalNameBytes() {
      java.lang.Object ref = canonicalName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        canonicalName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getFirstNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, firstName_);
      }
      if (!getLastNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, lastName_);
      }
      if (!getCanonicalNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, canonicalName_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getFirstNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, firstName_);
      }
      if (!getLastNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, lastName_);
      }
      if (!getCanonicalNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, canonicalName_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.prod.app.protobuff.Names.NamesPb)) {
        return super.equals(obj);
      }
      com.prod.app.protobuff.Names.NamesPb other = (com.prod.app.protobuff.Names.NamesPb) obj;

      if (!getFirstName()
          .equals(other.getFirstName())) return false;
      if (!getLastName()
          .equals(other.getLastName())) return false;
      if (!getCanonicalName()
          .equals(other.getCanonicalName())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + FIRSTNAME_FIELD_NUMBER;
      hash = (53 * hash) + getFirstName().hashCode();
      hash = (37 * hash) + LASTNAME_FIELD_NUMBER;
      hash = (53 * hash) + getLastName().hashCode();
      hash = (37 * hash) + CANONICALNAME_FIELD_NUMBER;
      hash = (53 * hash) + getCanonicalName().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.prod.app.protobuff.Names.NamesPb parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.prod.app.protobuff.Names.NamesPb parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.prod.app.protobuff.Names.NamesPb parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.prod.app.protobuff.Names.NamesPb parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.prod.app.protobuff.Names.NamesPb parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.prod.app.protobuff.Names.NamesPb parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.prod.app.protobuff.Names.NamesPb parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.prod.app.protobuff.Names.NamesPb parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.prod.app.protobuff.Names.NamesPb parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.prod.app.protobuff.Names.NamesPb parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.prod.app.protobuff.Names.NamesPb parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.prod.app.protobuff.Names.NamesPb parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.prod.app.protobuff.Names.NamesPb prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.prod.app.protobuff.NamesPb}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.prod.app.protobuff.NamesPb)
        com.prod.app.protobuff.Names.NamesPbOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.prod.app.protobuff.Names.internal_static_com_prod_app_protobuff_NamesPb_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.prod.app.protobuff.Names.internal_static_com_prod_app_protobuff_NamesPb_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.prod.app.protobuff.Names.NamesPb.class, com.prod.app.protobuff.Names.NamesPb.Builder.class);
      }

      // Construct using com.prod.app.protobuff.Names.NamesPb.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        firstName_ = "";

        lastName_ = "";

        canonicalName_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.prod.app.protobuff.Names.internal_static_com_prod_app_protobuff_NamesPb_descriptor;
      }

      @java.lang.Override
      public com.prod.app.protobuff.Names.NamesPb getDefaultInstanceForType() {
        return com.prod.app.protobuff.Names.NamesPb.getDefaultInstance();
      }

      @java.lang.Override
      public com.prod.app.protobuff.Names.NamesPb build() {
        com.prod.app.protobuff.Names.NamesPb result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.prod.app.protobuff.Names.NamesPb buildPartial() {
        com.prod.app.protobuff.Names.NamesPb result = new com.prod.app.protobuff.Names.NamesPb(this);
        result.firstName_ = firstName_;
        result.lastName_ = lastName_;
        result.canonicalName_ = canonicalName_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.prod.app.protobuff.Names.NamesPb) {
          return mergeFrom((com.prod.app.protobuff.Names.NamesPb)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.prod.app.protobuff.Names.NamesPb other) {
        if (other == com.prod.app.protobuff.Names.NamesPb.getDefaultInstance()) return this;
        if (!other.getFirstName().isEmpty()) {
          firstName_ = other.firstName_;
          onChanged();
        }
        if (!other.getLastName().isEmpty()) {
          lastName_ = other.lastName_;
          onChanged();
        }
        if (!other.getCanonicalName().isEmpty()) {
          canonicalName_ = other.canonicalName_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.prod.app.protobuff.Names.NamesPb parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.prod.app.protobuff.Names.NamesPb) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object firstName_ = "";
      /**
       * <code>string firstName = 1;</code>
       */
      public java.lang.String getFirstName() {
        java.lang.Object ref = firstName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          firstName_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string firstName = 1;</code>
       */
      public com.google.protobuf.ByteString
          getFirstNameBytes() {
        java.lang.Object ref = firstName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          firstName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string firstName = 1;</code>
       */
      public Builder setFirstName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        firstName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string firstName = 1;</code>
       */
      public Builder clearFirstName() {
        
        firstName_ = getDefaultInstance().getFirstName();
        onChanged();
        return this;
      }
      /**
       * <code>string firstName = 1;</code>
       */
      public Builder setFirstNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        firstName_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object lastName_ = "";
      /**
       * <code>string lastName = 2;</code>
       */
      public java.lang.String getLastName() {
        java.lang.Object ref = lastName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          lastName_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string lastName = 2;</code>
       */
      public com.google.protobuf.ByteString
          getLastNameBytes() {
        java.lang.Object ref = lastName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          lastName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string lastName = 2;</code>
       */
      public Builder setLastName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        lastName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string lastName = 2;</code>
       */
      public Builder clearLastName() {
        
        lastName_ = getDefaultInstance().getLastName();
        onChanged();
        return this;
      }
      /**
       * <code>string lastName = 2;</code>
       */
      public Builder setLastNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        lastName_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object canonicalName_ = "";
      /**
       * <code>string canonicalName = 3;</code>
       */
      public java.lang.String getCanonicalName() {
        java.lang.Object ref = canonicalName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          canonicalName_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string canonicalName = 3;</code>
       */
      public com.google.protobuf.ByteString
          getCanonicalNameBytes() {
        java.lang.Object ref = canonicalName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          canonicalName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string canonicalName = 3;</code>
       */
      public Builder setCanonicalName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        canonicalName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string canonicalName = 3;</code>
       */
      public Builder clearCanonicalName() {
        
        canonicalName_ = getDefaultInstance().getCanonicalName();
        onChanged();
        return this;
      }
      /**
       * <code>string canonicalName = 3;</code>
       */
      public Builder setCanonicalNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        canonicalName_ = value;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.prod.app.protobuff.NamesPb)
    }

    // @@protoc_insertion_point(class_scope:com.prod.app.protobuff.NamesPb)
    private static final com.prod.app.protobuff.Names.NamesPb DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.prod.app.protobuff.Names.NamesPb();
    }

    public static com.prod.app.protobuff.Names.NamesPb getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<NamesPb>
        PARSER = new com.google.protobuf.AbstractParser<NamesPb>() {
      @java.lang.Override
      public NamesPb parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new NamesPb(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<NamesPb> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<NamesPb> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.prod.app.protobuff.Names.NamesPb getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_prod_app_protobuff_NamesPb_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_prod_app_protobuff_NamesPb_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013names.proto\022\026com.prod.app.protobuff\"E\n" +
      "\007NamesPb\022\021\n\tfirstName\030\001 \001(\t\022\020\n\010lastName\030" +
      "\002 \001(\t\022\025\n\rcanonicalName\030\003 \001(\tb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_prod_app_protobuff_NamesPb_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_prod_app_protobuff_NamesPb_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_prod_app_protobuff_NamesPb_descriptor,
        new java.lang.String[] { "FirstName", "LastName", "CanonicalName", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
