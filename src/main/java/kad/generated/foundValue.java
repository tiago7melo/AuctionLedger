// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kademlia.proto

package kad.generated;

/**
 * Protobuf type {@code KademliaProto.foundValue}
 */
public  final class foundValue extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:KademliaProto.foundValue)
    foundValueOrBuilder {
private static final long serialVersionUID = 0L;
  // Use foundValue.newBuilder() to construct.
  private foundValue(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private foundValue() {
    isFound_ = false;
    key_ = com.google.protobuf.ByteString.EMPTY;
    value_ = "";
    nodeID_ = com.google.protobuf.ByteString.EMPTY;
    nodeAdress_ = "";
    port_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private foundValue(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
          case 8: {

            isFound_ = input.readBool();
            break;
          }
          case 18: {

            key_ = input.readBytes();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            value_ = s;
            break;
          }
          case 34: {

            nodeID_ = input.readBytes();
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            nodeAdress_ = s;
            break;
          }
          case 48: {

            port_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
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
    return kad.generated.KademliaProto.internal_static_KademliaProto_foundValue_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return kad.generated.KademliaProto.internal_static_KademliaProto_foundValue_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            kad.generated.foundValue.class, kad.generated.foundValue.Builder.class);
  }

  public static final int ISFOUND_FIELD_NUMBER = 1;
  private boolean isFound_;
  /**
   * <code>bool isFound = 1;</code>
   */
  public boolean getIsFound() {
    return isFound_;
  }

  public static final int KEY_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString key_;
  /**
   * <code>bytes key = 2;</code>
   */
  public com.google.protobuf.ByteString getKey() {
    return key_;
  }

  public static final int VALUE_FIELD_NUMBER = 3;
  private volatile java.lang.Object value_;
  /**
   * <code>string value = 3;</code>
   */
  public java.lang.String getValue() {
    java.lang.Object ref = value_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      value_ = s;
      return s;
    }
  }
  /**
   * <code>string value = 3;</code>
   */
  public com.google.protobuf.ByteString
      getValueBytes() {
    java.lang.Object ref = value_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      value_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int NODEID_FIELD_NUMBER = 4;
  private com.google.protobuf.ByteString nodeID_;
  /**
   * <code>bytes nodeID = 4;</code>
   */
  public com.google.protobuf.ByteString getNodeID() {
    return nodeID_;
  }

  public static final int NODEADRESS_FIELD_NUMBER = 5;
  private volatile java.lang.Object nodeAdress_;
  /**
   * <code>string nodeAdress = 5;</code>
   */
  public java.lang.String getNodeAdress() {
    java.lang.Object ref = nodeAdress_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      nodeAdress_ = s;
      return s;
    }
  }
  /**
   * <code>string nodeAdress = 5;</code>
   */
  public com.google.protobuf.ByteString
      getNodeAdressBytes() {
    java.lang.Object ref = nodeAdress_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      nodeAdress_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PORT_FIELD_NUMBER = 6;
  private int port_;
  /**
   * <code>int32 port = 6;</code>
   */
  public int getPort() {
    return port_;
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
    if (isFound_ != false) {
      output.writeBool(1, isFound_);
    }
    if (!key_.isEmpty()) {
      output.writeBytes(2, key_);
    }
    if (!getValueBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, value_);
    }
    if (!nodeID_.isEmpty()) {
      output.writeBytes(4, nodeID_);
    }
    if (!getNodeAdressBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, nodeAdress_);
    }
    if (port_ != 0) {
      output.writeInt32(6, port_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (isFound_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, isFound_);
    }
    if (!key_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, key_);
    }
    if (!getValueBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, value_);
    }
    if (!nodeID_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(4, nodeID_);
    }
    if (!getNodeAdressBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, nodeAdress_);
    }
    if (port_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, port_);
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
    if (!(obj instanceof kad.generated.foundValue)) {
      return super.equals(obj);
    }
    kad.generated.foundValue other = (kad.generated.foundValue) obj;

    boolean result = true;
    result = result && (getIsFound()
        == other.getIsFound());
    result = result && getKey()
        .equals(other.getKey());
    result = result && getValue()
        .equals(other.getValue());
    result = result && getNodeID()
        .equals(other.getNodeID());
    result = result && getNodeAdress()
        .equals(other.getNodeAdress());
    result = result && (getPort()
        == other.getPort());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ISFOUND_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getIsFound());
    hash = (37 * hash) + KEY_FIELD_NUMBER;
    hash = (53 * hash) + getKey().hashCode();
    hash = (37 * hash) + VALUE_FIELD_NUMBER;
    hash = (53 * hash) + getValue().hashCode();
    hash = (37 * hash) + NODEID_FIELD_NUMBER;
    hash = (53 * hash) + getNodeID().hashCode();
    hash = (37 * hash) + NODEADRESS_FIELD_NUMBER;
    hash = (53 * hash) + getNodeAdress().hashCode();
    hash = (37 * hash) + PORT_FIELD_NUMBER;
    hash = (53 * hash) + getPort();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static kad.generated.foundValue parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kad.generated.foundValue parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kad.generated.foundValue parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kad.generated.foundValue parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kad.generated.foundValue parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kad.generated.foundValue parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kad.generated.foundValue parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static kad.generated.foundValue parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static kad.generated.foundValue parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static kad.generated.foundValue parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static kad.generated.foundValue parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static kad.generated.foundValue parseFrom(
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
  public static Builder newBuilder(kad.generated.foundValue prototype) {
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
   * Protobuf type {@code KademliaProto.foundValue}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:KademliaProto.foundValue)
      kad.generated.foundValueOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return kad.generated.KademliaProto.internal_static_KademliaProto_foundValue_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return kad.generated.KademliaProto.internal_static_KademliaProto_foundValue_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              kad.generated.foundValue.class, kad.generated.foundValue.Builder.class);
    }

    // Construct using kad.generated.foundValue.newBuilder()
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
      isFound_ = false;

      key_ = com.google.protobuf.ByteString.EMPTY;

      value_ = "";

      nodeID_ = com.google.protobuf.ByteString.EMPTY;

      nodeAdress_ = "";

      port_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return kad.generated.KademliaProto.internal_static_KademliaProto_foundValue_descriptor;
    }

    @java.lang.Override
    public kad.generated.foundValue getDefaultInstanceForType() {
      return kad.generated.foundValue.getDefaultInstance();
    }

    @java.lang.Override
    public kad.generated.foundValue build() {
      kad.generated.foundValue result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public kad.generated.foundValue buildPartial() {
      kad.generated.foundValue result = new kad.generated.foundValue(this);
      result.isFound_ = isFound_;
      result.key_ = key_;
      result.value_ = value_;
      result.nodeID_ = nodeID_;
      result.nodeAdress_ = nodeAdress_;
      result.port_ = port_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof kad.generated.foundValue) {
        return mergeFrom((kad.generated.foundValue)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(kad.generated.foundValue other) {
      if (other == kad.generated.foundValue.getDefaultInstance()) return this;
      if (other.getIsFound() != false) {
        setIsFound(other.getIsFound());
      }
      if (other.getKey() != com.google.protobuf.ByteString.EMPTY) {
        setKey(other.getKey());
      }
      if (!other.getValue().isEmpty()) {
        value_ = other.value_;
        onChanged();
      }
      if (other.getNodeID() != com.google.protobuf.ByteString.EMPTY) {
        setNodeID(other.getNodeID());
      }
      if (!other.getNodeAdress().isEmpty()) {
        nodeAdress_ = other.nodeAdress_;
        onChanged();
      }
      if (other.getPort() != 0) {
        setPort(other.getPort());
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
      kad.generated.foundValue parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (kad.generated.foundValue) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private boolean isFound_ ;
    /**
     * <code>bool isFound = 1;</code>
     */
    public boolean getIsFound() {
      return isFound_;
    }
    /**
     * <code>bool isFound = 1;</code>
     */
    public Builder setIsFound(boolean value) {
      
      isFound_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool isFound = 1;</code>
     */
    public Builder clearIsFound() {
      
      isFound_ = false;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString key_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes key = 2;</code>
     */
    public com.google.protobuf.ByteString getKey() {
      return key_;
    }
    /**
     * <code>bytes key = 2;</code>
     */
    public Builder setKey(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      key_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes key = 2;</code>
     */
    public Builder clearKey() {
      
      key_ = getDefaultInstance().getKey();
      onChanged();
      return this;
    }

    private java.lang.Object value_ = "";
    /**
     * <code>string value = 3;</code>
     */
    public java.lang.String getValue() {
      java.lang.Object ref = value_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        value_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string value = 3;</code>
     */
    public com.google.protobuf.ByteString
        getValueBytes() {
      java.lang.Object ref = value_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        value_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string value = 3;</code>
     */
    public Builder setValue(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      value_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string value = 3;</code>
     */
    public Builder clearValue() {
      
      value_ = getDefaultInstance().getValue();
      onChanged();
      return this;
    }
    /**
     * <code>string value = 3;</code>
     */
    public Builder setValueBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      value_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString nodeID_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes nodeID = 4;</code>
     */
    public com.google.protobuf.ByteString getNodeID() {
      return nodeID_;
    }
    /**
     * <code>bytes nodeID = 4;</code>
     */
    public Builder setNodeID(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      nodeID_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes nodeID = 4;</code>
     */
    public Builder clearNodeID() {
      
      nodeID_ = getDefaultInstance().getNodeID();
      onChanged();
      return this;
    }

    private java.lang.Object nodeAdress_ = "";
    /**
     * <code>string nodeAdress = 5;</code>
     */
    public java.lang.String getNodeAdress() {
      java.lang.Object ref = nodeAdress_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        nodeAdress_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string nodeAdress = 5;</code>
     */
    public com.google.protobuf.ByteString
        getNodeAdressBytes() {
      java.lang.Object ref = nodeAdress_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        nodeAdress_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string nodeAdress = 5;</code>
     */
    public Builder setNodeAdress(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      nodeAdress_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string nodeAdress = 5;</code>
     */
    public Builder clearNodeAdress() {
      
      nodeAdress_ = getDefaultInstance().getNodeAdress();
      onChanged();
      return this;
    }
    /**
     * <code>string nodeAdress = 5;</code>
     */
    public Builder setNodeAdressBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      nodeAdress_ = value;
      onChanged();
      return this;
    }

    private int port_ ;
    /**
     * <code>int32 port = 6;</code>
     */
    public int getPort() {
      return port_;
    }
    /**
     * <code>int32 port = 6;</code>
     */
    public Builder setPort(int value) {
      
      port_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 port = 6;</code>
     */
    public Builder clearPort() {
      
      port_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:KademliaProto.foundValue)
  }

  // @@protoc_insertion_point(class_scope:KademliaProto.foundValue)
  private static final kad.generated.foundValue DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new kad.generated.foundValue();
  }

  public static kad.generated.foundValue getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<foundValue>
      PARSER = new com.google.protobuf.AbstractParser<foundValue>() {
    @java.lang.Override
    public foundValue parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new foundValue(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<foundValue> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<foundValue> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public kad.generated.foundValue getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
