// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kademlia.proto

package kad.generated;

/**
 * Protobuf type {@code KademliaProto.Message}
 */
public  final class Message extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:KademliaProto.Message)
    MessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Message.newBuilder() to construct.
  private Message(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Message() {
    senderID_ = 0;
    senderAddress_ = "";
    senderPort_ = 0;
    messageType_ = 0;
    messageID_ = 0;
    timestamp_ = 0L;
    msg_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Message(
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

            senderID_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            senderAddress_ = s;
            break;
          }
          case 24: {

            senderPort_ = input.readInt32();
            break;
          }
          case 32: {

            messageType_ = input.readInt32();
            break;
          }
          case 40: {

            messageID_ = input.readInt32();
            break;
          }
          case 48: {

            timestamp_ = input.readInt64();
            break;
          }
          case 58: {
            java.lang.String s = input.readStringRequireUtf8();

            msg_ = s;
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
    return kad.generated.KademliaProto.internal_static_KademliaProto_Message_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return kad.generated.KademliaProto.internal_static_KademliaProto_Message_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            kad.generated.Message.class, kad.generated.Message.Builder.class);
  }

  public static final int SENDERID_FIELD_NUMBER = 1;
  private int senderID_;
  /**
   * <code>int32 senderID = 1;</code>
   */
  public int getSenderID() {
    return senderID_;
  }

  public static final int SENDERADDRESS_FIELD_NUMBER = 2;
  private volatile java.lang.Object senderAddress_;
  /**
   * <code>string senderAddress = 2;</code>
   */
  public java.lang.String getSenderAddress() {
    java.lang.Object ref = senderAddress_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      senderAddress_ = s;
      return s;
    }
  }
  /**
   * <code>string senderAddress = 2;</code>
   */
  public com.google.protobuf.ByteString
      getSenderAddressBytes() {
    java.lang.Object ref = senderAddress_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      senderAddress_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SENDERPORT_FIELD_NUMBER = 3;
  private int senderPort_;
  /**
   * <code>int32 senderPort = 3;</code>
   */
  public int getSenderPort() {
    return senderPort_;
  }

  public static final int MESSAGETYPE_FIELD_NUMBER = 4;
  private int messageType_;
  /**
   * <code>int32 messageType = 4;</code>
   */
  public int getMessageType() {
    return messageType_;
  }

  public static final int MESSAGEID_FIELD_NUMBER = 5;
  private int messageID_;
  /**
   * <code>int32 messageID = 5;</code>
   */
  public int getMessageID() {
    return messageID_;
  }

  public static final int TIMESTAMP_FIELD_NUMBER = 6;
  private long timestamp_;
  /**
   * <code>int64 timestamp = 6;</code>
   */
  public long getTimestamp() {
    return timestamp_;
  }

  public static final int MSG_FIELD_NUMBER = 7;
  private volatile java.lang.Object msg_;
  /**
   * <code>string msg = 7;</code>
   */
  public java.lang.String getMsg() {
    java.lang.Object ref = msg_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      msg_ = s;
      return s;
    }
  }
  /**
   * <code>string msg = 7;</code>
   */
  public com.google.protobuf.ByteString
      getMsgBytes() {
    java.lang.Object ref = msg_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      msg_ = b;
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
    if (senderID_ != 0) {
      output.writeInt32(1, senderID_);
    }
    if (!getSenderAddressBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, senderAddress_);
    }
    if (senderPort_ != 0) {
      output.writeInt32(3, senderPort_);
    }
    if (messageType_ != 0) {
      output.writeInt32(4, messageType_);
    }
    if (messageID_ != 0) {
      output.writeInt32(5, messageID_);
    }
    if (timestamp_ != 0L) {
      output.writeInt64(6, timestamp_);
    }
    if (!getMsgBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 7, msg_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (senderID_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, senderID_);
    }
    if (!getSenderAddressBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, senderAddress_);
    }
    if (senderPort_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, senderPort_);
    }
    if (messageType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, messageType_);
    }
    if (messageID_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, messageID_);
    }
    if (timestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(6, timestamp_);
    }
    if (!getMsgBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(7, msg_);
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
    if (!(obj instanceof kad.generated.Message)) {
      return super.equals(obj);
    }
    kad.generated.Message other = (kad.generated.Message) obj;

    boolean result = true;
    result = result && (getSenderID()
        == other.getSenderID());
    result = result && getSenderAddress()
        .equals(other.getSenderAddress());
    result = result && (getSenderPort()
        == other.getSenderPort());
    result = result && (getMessageType()
        == other.getMessageType());
    result = result && (getMessageID()
        == other.getMessageID());
    result = result && (getTimestamp()
        == other.getTimestamp());
    result = result && getMsg()
        .equals(other.getMsg());
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
    hash = (37 * hash) + SENDERID_FIELD_NUMBER;
    hash = (53 * hash) + getSenderID();
    hash = (37 * hash) + SENDERADDRESS_FIELD_NUMBER;
    hash = (53 * hash) + getSenderAddress().hashCode();
    hash = (37 * hash) + SENDERPORT_FIELD_NUMBER;
    hash = (53 * hash) + getSenderPort();
    hash = (37 * hash) + MESSAGETYPE_FIELD_NUMBER;
    hash = (53 * hash) + getMessageType();
    hash = (37 * hash) + MESSAGEID_FIELD_NUMBER;
    hash = (53 * hash) + getMessageID();
    hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTimestamp());
    hash = (37 * hash) + MSG_FIELD_NUMBER;
    hash = (53 * hash) + getMsg().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static kad.generated.Message parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kad.generated.Message parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kad.generated.Message parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kad.generated.Message parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kad.generated.Message parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kad.generated.Message parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kad.generated.Message parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static kad.generated.Message parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static kad.generated.Message parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static kad.generated.Message parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static kad.generated.Message parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static kad.generated.Message parseFrom(
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
  public static Builder newBuilder(kad.generated.Message prototype) {
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
   * Protobuf type {@code KademliaProto.Message}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:KademliaProto.Message)
      kad.generated.MessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return kad.generated.KademliaProto.internal_static_KademliaProto_Message_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return kad.generated.KademliaProto.internal_static_KademliaProto_Message_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              kad.generated.Message.class, kad.generated.Message.Builder.class);
    }

    // Construct using kad.generated.Message.newBuilder()
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
      senderID_ = 0;

      senderAddress_ = "";

      senderPort_ = 0;

      messageType_ = 0;

      messageID_ = 0;

      timestamp_ = 0L;

      msg_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return kad.generated.KademliaProto.internal_static_KademliaProto_Message_descriptor;
    }

    @java.lang.Override
    public kad.generated.Message getDefaultInstanceForType() {
      return kad.generated.Message.getDefaultInstance();
    }

    @java.lang.Override
    public kad.generated.Message build() {
      kad.generated.Message result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public kad.generated.Message buildPartial() {
      kad.generated.Message result = new kad.generated.Message(this);
      result.senderID_ = senderID_;
      result.senderAddress_ = senderAddress_;
      result.senderPort_ = senderPort_;
      result.messageType_ = messageType_;
      result.messageID_ = messageID_;
      result.timestamp_ = timestamp_;
      result.msg_ = msg_;
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
      if (other instanceof kad.generated.Message) {
        return mergeFrom((kad.generated.Message)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(kad.generated.Message other) {
      if (other == kad.generated.Message.getDefaultInstance()) return this;
      if (other.getSenderID() != 0) {
        setSenderID(other.getSenderID());
      }
      if (!other.getSenderAddress().isEmpty()) {
        senderAddress_ = other.senderAddress_;
        onChanged();
      }
      if (other.getSenderPort() != 0) {
        setSenderPort(other.getSenderPort());
      }
      if (other.getMessageType() != 0) {
        setMessageType(other.getMessageType());
      }
      if (other.getMessageID() != 0) {
        setMessageID(other.getMessageID());
      }
      if (other.getTimestamp() != 0L) {
        setTimestamp(other.getTimestamp());
      }
      if (!other.getMsg().isEmpty()) {
        msg_ = other.msg_;
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
      kad.generated.Message parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (kad.generated.Message) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int senderID_ ;
    /**
     * <code>int32 senderID = 1;</code>
     */
    public int getSenderID() {
      return senderID_;
    }
    /**
     * <code>int32 senderID = 1;</code>
     */
    public Builder setSenderID(int value) {
      
      senderID_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 senderID = 1;</code>
     */
    public Builder clearSenderID() {
      
      senderID_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object senderAddress_ = "";
    /**
     * <code>string senderAddress = 2;</code>
     */
    public java.lang.String getSenderAddress() {
      java.lang.Object ref = senderAddress_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        senderAddress_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string senderAddress = 2;</code>
     */
    public com.google.protobuf.ByteString
        getSenderAddressBytes() {
      java.lang.Object ref = senderAddress_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        senderAddress_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string senderAddress = 2;</code>
     */
    public Builder setSenderAddress(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      senderAddress_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string senderAddress = 2;</code>
     */
    public Builder clearSenderAddress() {
      
      senderAddress_ = getDefaultInstance().getSenderAddress();
      onChanged();
      return this;
    }
    /**
     * <code>string senderAddress = 2;</code>
     */
    public Builder setSenderAddressBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      senderAddress_ = value;
      onChanged();
      return this;
    }

    private int senderPort_ ;
    /**
     * <code>int32 senderPort = 3;</code>
     */
    public int getSenderPort() {
      return senderPort_;
    }
    /**
     * <code>int32 senderPort = 3;</code>
     */
    public Builder setSenderPort(int value) {
      
      senderPort_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 senderPort = 3;</code>
     */
    public Builder clearSenderPort() {
      
      senderPort_ = 0;
      onChanged();
      return this;
    }

    private int messageType_ ;
    /**
     * <code>int32 messageType = 4;</code>
     */
    public int getMessageType() {
      return messageType_;
    }
    /**
     * <code>int32 messageType = 4;</code>
     */
    public Builder setMessageType(int value) {
      
      messageType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 messageType = 4;</code>
     */
    public Builder clearMessageType() {
      
      messageType_ = 0;
      onChanged();
      return this;
    }

    private int messageID_ ;
    /**
     * <code>int32 messageID = 5;</code>
     */
    public int getMessageID() {
      return messageID_;
    }
    /**
     * <code>int32 messageID = 5;</code>
     */
    public Builder setMessageID(int value) {
      
      messageID_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 messageID = 5;</code>
     */
    public Builder clearMessageID() {
      
      messageID_ = 0;
      onChanged();
      return this;
    }

    private long timestamp_ ;
    /**
     * <code>int64 timestamp = 6;</code>
     */
    public long getTimestamp() {
      return timestamp_;
    }
    /**
     * <code>int64 timestamp = 6;</code>
     */
    public Builder setTimestamp(long value) {
      
      timestamp_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 timestamp = 6;</code>
     */
    public Builder clearTimestamp() {
      
      timestamp_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object msg_ = "";
    /**
     * <code>string msg = 7;</code>
     */
    public java.lang.String getMsg() {
      java.lang.Object ref = msg_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        msg_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string msg = 7;</code>
     */
    public com.google.protobuf.ByteString
        getMsgBytes() {
      java.lang.Object ref = msg_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        msg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string msg = 7;</code>
     */
    public Builder setMsg(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      msg_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string msg = 7;</code>
     */
    public Builder clearMsg() {
      
      msg_ = getDefaultInstance().getMsg();
      onChanged();
      return this;
    }
    /**
     * <code>string msg = 7;</code>
     */
    public Builder setMsgBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      msg_ = value;
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


    // @@protoc_insertion_point(builder_scope:KademliaProto.Message)
  }

  // @@protoc_insertion_point(class_scope:KademliaProto.Message)
  private static final kad.generated.Message DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new kad.generated.Message();
  }

  public static kad.generated.Message getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Message>
      PARSER = new com.google.protobuf.AbstractParser<Message>() {
    @java.lang.Override
    public Message parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Message(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Message> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Message> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public kad.generated.Message getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

