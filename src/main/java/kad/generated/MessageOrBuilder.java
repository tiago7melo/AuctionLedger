// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kademlia.proto

package kad.generated;

public interface MessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:KademliaProto.Message)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 senderID = 1;</code>
   */
  int getSenderID();

  /**
   * <code>string senderAddress = 2;</code>
   */
  java.lang.String getSenderAddress();
  /**
   * <code>string senderAddress = 2;</code>
   */
  com.google.protobuf.ByteString
      getSenderAddressBytes();

  /**
   * <code>int32 senderPort = 3;</code>
   */
  int getSenderPort();

  /**
   * <code>int32 messageType = 4;</code>
   */
  int getMessageType();

  /**
   * <code>int32 messageID = 5;</code>
   */
  int getMessageID();

  /**
   * <code>int64 timestamp = 6;</code>
   */
  long getTimestamp();

  /**
   * <code>string msg = 7;</code>
   */
  java.lang.String getMsg();
  /**
   * <code>string msg = 7;</code>
   */
  com.google.protobuf.ByteString
      getMsgBytes();
}