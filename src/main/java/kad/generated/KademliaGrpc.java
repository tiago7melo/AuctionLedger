package kad.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: kademlia.proto")
public final class KademliaGrpc {

  private KademliaGrpc() {}

  public static final String SERVICE_NAME = "KademliaProto.Kademlia";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<kad.generated.Ping,
      kad.generated.Ping> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ping",
      requestType = kad.generated.Ping.class,
      responseType = kad.generated.Ping.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kad.generated.Ping,
      kad.generated.Ping> getPingMethod() {
    io.grpc.MethodDescriptor<kad.generated.Ping, kad.generated.Ping> getPingMethod;
    if ((getPingMethod = KademliaGrpc.getPingMethod) == null) {
      synchronized (KademliaGrpc.class) {
        if ((getPingMethod = KademliaGrpc.getPingMethod) == null) {
          KademliaGrpc.getPingMethod = getPingMethod = 
              io.grpc.MethodDescriptor.<kad.generated.Ping, kad.generated.Ping>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "KademliaProto.Kademlia", "ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.Ping.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.Ping.getDefaultInstance()))
                  .setSchemaDescriptor(new KademliaMethodDescriptorSupplier("ping"))
                  .build();
          }
        }
     }
     return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kad.generated.StorableValue,
      kad.generated.RequestStatus> getStoreMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "store",
      requestType = kad.generated.StorableValue.class,
      responseType = kad.generated.RequestStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kad.generated.StorableValue,
      kad.generated.RequestStatus> getStoreMethod() {
    io.grpc.MethodDescriptor<kad.generated.StorableValue, kad.generated.RequestStatus> getStoreMethod;
    if ((getStoreMethod = KademliaGrpc.getStoreMethod) == null) {
      synchronized (KademliaGrpc.class) {
        if ((getStoreMethod = KademliaGrpc.getStoreMethod) == null) {
          KademliaGrpc.getStoreMethod = getStoreMethod = 
              io.grpc.MethodDescriptor.<kad.generated.StorableValue, kad.generated.RequestStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "KademliaProto.Kademlia", "store"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.StorableValue.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.RequestStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new KademliaMethodDescriptorSupplier("store"))
                  .build();
          }
        }
     }
     return getStoreMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kad.generated.KadID,
      kad.generated.NodeInfo> getFindNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findNode",
      requestType = kad.generated.KadID.class,
      responseType = kad.generated.NodeInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kad.generated.KadID,
      kad.generated.NodeInfo> getFindNodeMethod() {
    io.grpc.MethodDescriptor<kad.generated.KadID, kad.generated.NodeInfo> getFindNodeMethod;
    if ((getFindNodeMethod = KademliaGrpc.getFindNodeMethod) == null) {
      synchronized (KademliaGrpc.class) {
        if ((getFindNodeMethod = KademliaGrpc.getFindNodeMethod) == null) {
          KademliaGrpc.getFindNodeMethod = getFindNodeMethod = 
              io.grpc.MethodDescriptor.<kad.generated.KadID, kad.generated.NodeInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "KademliaProto.Kademlia", "findNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.KadID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.NodeInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new KademliaMethodDescriptorSupplier("findNode"))
                  .build();
          }
        }
     }
     return getFindNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kad.generated.Value,
      kad.generated.foundValue> getFindValueMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findValue",
      requestType = kad.generated.Value.class,
      responseType = kad.generated.foundValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kad.generated.Value,
      kad.generated.foundValue> getFindValueMethod() {
    io.grpc.MethodDescriptor<kad.generated.Value, kad.generated.foundValue> getFindValueMethod;
    if ((getFindValueMethod = KademliaGrpc.getFindValueMethod) == null) {
      synchronized (KademliaGrpc.class) {
        if ((getFindValueMethod = KademliaGrpc.getFindValueMethod) == null) {
          KademliaGrpc.getFindValueMethod = getFindValueMethod = 
              io.grpc.MethodDescriptor.<kad.generated.Value, kad.generated.foundValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "KademliaProto.Kademlia", "findValue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.Value.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.foundValue.getDefaultInstance()))
                  .setSchemaDescriptor(new KademliaMethodDescriptorSupplier("findValue"))
                  .build();
          }
        }
     }
     return getFindValueMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kad.generated.Message,
      kad.generated.RequestStatus> getBroadcastMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "broadcastMessage",
      requestType = kad.generated.Message.class,
      responseType = kad.generated.RequestStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kad.generated.Message,
      kad.generated.RequestStatus> getBroadcastMessageMethod() {
    io.grpc.MethodDescriptor<kad.generated.Message, kad.generated.RequestStatus> getBroadcastMessageMethod;
    if ((getBroadcastMessageMethod = KademliaGrpc.getBroadcastMessageMethod) == null) {
      synchronized (KademliaGrpc.class) {
        if ((getBroadcastMessageMethod = KademliaGrpc.getBroadcastMessageMethod) == null) {
          KademliaGrpc.getBroadcastMessageMethod = getBroadcastMessageMethod = 
              io.grpc.MethodDescriptor.<kad.generated.Message, kad.generated.RequestStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "KademliaProto.Kademlia", "broadcastMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.RequestStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new KademliaMethodDescriptorSupplier("broadcastMessage"))
                  .build();
          }
        }
     }
     return getBroadcastMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kad.generated.Message,
      kad.generated.Message> getMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "message",
      requestType = kad.generated.Message.class,
      responseType = kad.generated.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kad.generated.Message,
      kad.generated.Message> getMessageMethod() {
    io.grpc.MethodDescriptor<kad.generated.Message, kad.generated.Message> getMessageMethod;
    if ((getMessageMethod = KademliaGrpc.getMessageMethod) == null) {
      synchronized (KademliaGrpc.class) {
        if ((getMessageMethod = KademliaGrpc.getMessageMethod) == null) {
          KademliaGrpc.getMessageMethod = getMessageMethod = 
              io.grpc.MethodDescriptor.<kad.generated.Message, kad.generated.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "KademliaProto.Kademlia", "message"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.Message.getDefaultInstance()))
                  .setSchemaDescriptor(new KademliaMethodDescriptorSupplier("message"))
                  .build();
          }
        }
     }
     return getMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kad.generated.NodeInfo,
      kad.generated.SecurityChallenge> getJoinRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "joinRequest",
      requestType = kad.generated.NodeInfo.class,
      responseType = kad.generated.SecurityChallenge.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<kad.generated.NodeInfo,
      kad.generated.SecurityChallenge> getJoinRequestMethod() {
    io.grpc.MethodDescriptor<kad.generated.NodeInfo, kad.generated.SecurityChallenge> getJoinRequestMethod;
    if ((getJoinRequestMethod = KademliaGrpc.getJoinRequestMethod) == null) {
      synchronized (KademliaGrpc.class) {
        if ((getJoinRequestMethod = KademliaGrpc.getJoinRequestMethod) == null) {
          KademliaGrpc.getJoinRequestMethod = getJoinRequestMethod = 
              io.grpc.MethodDescriptor.<kad.generated.NodeInfo, kad.generated.SecurityChallenge>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "KademliaProto.Kademlia", "joinRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.NodeInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.SecurityChallenge.getDefaultInstance()))
                  .setSchemaDescriptor(new KademliaMethodDescriptorSupplier("joinRequest"))
                  .build();
          }
        }
     }
     return getJoinRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kad.generated.SecurityChallenge,
      kad.generated.NodeInfo> getJoinValidateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "joinValidate",
      requestType = kad.generated.SecurityChallenge.class,
      responseType = kad.generated.NodeInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<kad.generated.SecurityChallenge,
      kad.generated.NodeInfo> getJoinValidateMethod() {
    io.grpc.MethodDescriptor<kad.generated.SecurityChallenge, kad.generated.NodeInfo> getJoinValidateMethod;
    if ((getJoinValidateMethod = KademliaGrpc.getJoinValidateMethod) == null) {
      synchronized (KademliaGrpc.class) {
        if ((getJoinValidateMethod = KademliaGrpc.getJoinValidateMethod) == null) {
          KademliaGrpc.getJoinValidateMethod = getJoinValidateMethod = 
              io.grpc.MethodDescriptor.<kad.generated.SecurityChallenge, kad.generated.NodeInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "KademliaProto.Kademlia", "joinValidate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.SecurityChallenge.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.NodeInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new KademliaMethodDescriptorSupplier("joinValidate"))
                  .build();
          }
        }
     }
     return getJoinValidateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kad.generated.NodeInfo,
      kad.generated.NodeInfo> getJoinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "join",
      requestType = kad.generated.NodeInfo.class,
      responseType = kad.generated.NodeInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<kad.generated.NodeInfo,
      kad.generated.NodeInfo> getJoinMethod() {
    io.grpc.MethodDescriptor<kad.generated.NodeInfo, kad.generated.NodeInfo> getJoinMethod;
    if ((getJoinMethod = KademliaGrpc.getJoinMethod) == null) {
      synchronized (KademliaGrpc.class) {
        if ((getJoinMethod = KademliaGrpc.getJoinMethod) == null) {
          KademliaGrpc.getJoinMethod = getJoinMethod = 
              io.grpc.MethodDescriptor.<kad.generated.NodeInfo, kad.generated.NodeInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "KademliaProto.Kademlia", "join"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.NodeInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.NodeInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new KademliaMethodDescriptorSupplier("join"))
                  .build();
          }
        }
     }
     return getJoinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kad.generated.Ping,
      kad.generated.foundValue> getGetBlockChainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBlockChain",
      requestType = kad.generated.Ping.class,
      responseType = kad.generated.foundValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<kad.generated.Ping,
      kad.generated.foundValue> getGetBlockChainMethod() {
    io.grpc.MethodDescriptor<kad.generated.Ping, kad.generated.foundValue> getGetBlockChainMethod;
    if ((getGetBlockChainMethod = KademliaGrpc.getGetBlockChainMethod) == null) {
      synchronized (KademliaGrpc.class) {
        if ((getGetBlockChainMethod = KademliaGrpc.getGetBlockChainMethod) == null) {
          KademliaGrpc.getGetBlockChainMethod = getGetBlockChainMethod = 
              io.grpc.MethodDescriptor.<kad.generated.Ping, kad.generated.foundValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "KademliaProto.Kademlia", "getBlockChain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.Ping.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.foundValue.getDefaultInstance()))
                  .setSchemaDescriptor(new KademliaMethodDescriptorSupplier("getBlockChain"))
                  .build();
          }
        }
     }
     return getGetBlockChainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<kad.generated.Ping,
      kad.generated.foundValue> getGetTransactionPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getTransactionPool",
      requestType = kad.generated.Ping.class,
      responseType = kad.generated.foundValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<kad.generated.Ping,
      kad.generated.foundValue> getGetTransactionPoolMethod() {
    io.grpc.MethodDescriptor<kad.generated.Ping, kad.generated.foundValue> getGetTransactionPoolMethod;
    if ((getGetTransactionPoolMethod = KademliaGrpc.getGetTransactionPoolMethod) == null) {
      synchronized (KademliaGrpc.class) {
        if ((getGetTransactionPoolMethod = KademliaGrpc.getGetTransactionPoolMethod) == null) {
          KademliaGrpc.getGetTransactionPoolMethod = getGetTransactionPoolMethod = 
              io.grpc.MethodDescriptor.<kad.generated.Ping, kad.generated.foundValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "KademliaProto.Kademlia", "getTransactionPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.Ping.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  kad.generated.foundValue.getDefaultInstance()))
                  .setSchemaDescriptor(new KademliaMethodDescriptorSupplier("getTransactionPool"))
                  .build();
          }
        }
     }
     return getGetTransactionPoolMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KademliaStub newStub(io.grpc.Channel channel) {
    return new KademliaStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KademliaBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new KademliaBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KademliaFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new KademliaFutureStub(channel);
  }

  /**
   */
  public static abstract class KademliaImplBase implements io.grpc.BindableService {

    /**
     */
    public void ping(kad.generated.Ping request,
        io.grpc.stub.StreamObserver<kad.generated.Ping> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    public void store(kad.generated.StorableValue request,
        io.grpc.stub.StreamObserver<kad.generated.RequestStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getStoreMethod(), responseObserver);
    }

    /**
     */
    public void findNode(kad.generated.KadID request,
        io.grpc.stub.StreamObserver<kad.generated.NodeInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getFindNodeMethod(), responseObserver);
    }

    /**
     */
    public void findValue(kad.generated.Value request,
        io.grpc.stub.StreamObserver<kad.generated.foundValue> responseObserver) {
      asyncUnimplementedUnaryCall(getFindValueMethod(), responseObserver);
    }

    /**
     */
    public void broadcastMessage(kad.generated.Message request,
        io.grpc.stub.StreamObserver<kad.generated.RequestStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getBroadcastMessageMethod(), responseObserver);
    }

    /**
     */
    public void message(kad.generated.Message request,
        io.grpc.stub.StreamObserver<kad.generated.Message> responseObserver) {
      asyncUnimplementedUnaryCall(getMessageMethod(), responseObserver);
    }

    /**
     * <pre>
     * join with proof of work challenge
     * </pre>
     */
    public void joinRequest(kad.generated.NodeInfo request,
        io.grpc.stub.StreamObserver<kad.generated.SecurityChallenge> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinRequestMethod(), responseObserver);
    }

    /**
     */
    public void joinValidate(kad.generated.SecurityChallenge request,
        io.grpc.stub.StreamObserver<kad.generated.NodeInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinValidateMethod(), responseObserver);
    }

    /**
     * <pre>
     * simplejoin
     * </pre>
     */
    public void join(kad.generated.NodeInfo request,
        io.grpc.stub.StreamObserver<kad.generated.NodeInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinMethod(), responseObserver);
    }

    /**
     */
    public void getBlockChain(kad.generated.Ping request,
        io.grpc.stub.StreamObserver<kad.generated.foundValue> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockChainMethod(), responseObserver);
    }

    /**
     */
    public void getTransactionPool(kad.generated.Ping request,
        io.grpc.stub.StreamObserver<kad.generated.foundValue> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTransactionPoolMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kad.generated.Ping,
                kad.generated.Ping>(
                  this, METHODID_PING)))
          .addMethod(
            getStoreMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kad.generated.StorableValue,
                kad.generated.RequestStatus>(
                  this, METHODID_STORE)))
          .addMethod(
            getFindNodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kad.generated.KadID,
                kad.generated.NodeInfo>(
                  this, METHODID_FIND_NODE)))
          .addMethod(
            getFindValueMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kad.generated.Value,
                kad.generated.foundValue>(
                  this, METHODID_FIND_VALUE)))
          .addMethod(
            getBroadcastMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kad.generated.Message,
                kad.generated.RequestStatus>(
                  this, METHODID_BROADCAST_MESSAGE)))
          .addMethod(
            getMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kad.generated.Message,
                kad.generated.Message>(
                  this, METHODID_MESSAGE)))
          .addMethod(
            getJoinRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                kad.generated.NodeInfo,
                kad.generated.SecurityChallenge>(
                  this, METHODID_JOIN_REQUEST)))
          .addMethod(
            getJoinValidateMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                kad.generated.SecurityChallenge,
                kad.generated.NodeInfo>(
                  this, METHODID_JOIN_VALIDATE)))
          .addMethod(
            getJoinMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                kad.generated.NodeInfo,
                kad.generated.NodeInfo>(
                  this, METHODID_JOIN)))
          .addMethod(
            getGetBlockChainMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                kad.generated.Ping,
                kad.generated.foundValue>(
                  this, METHODID_GET_BLOCK_CHAIN)))
          .addMethod(
            getGetTransactionPoolMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                kad.generated.Ping,
                kad.generated.foundValue>(
                  this, METHODID_GET_TRANSACTION_POOL)))
          .build();
    }
  }

  /**
   */
  public static final class KademliaStub extends io.grpc.stub.AbstractStub<KademliaStub> {
    private KademliaStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KademliaStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KademliaStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KademliaStub(channel, callOptions);
    }

    /**
     */
    public void ping(kad.generated.Ping request,
        io.grpc.stub.StreamObserver<kad.generated.Ping> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void store(kad.generated.StorableValue request,
        io.grpc.stub.StreamObserver<kad.generated.RequestStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStoreMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findNode(kad.generated.KadID request,
        io.grpc.stub.StreamObserver<kad.generated.NodeInfo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findValue(kad.generated.Value request,
        io.grpc.stub.StreamObserver<kad.generated.foundValue> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindValueMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void broadcastMessage(kad.generated.Message request,
        io.grpc.stub.StreamObserver<kad.generated.RequestStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBroadcastMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void message(kad.generated.Message request,
        io.grpc.stub.StreamObserver<kad.generated.Message> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * join with proof of work challenge
     * </pre>
     */
    public void joinRequest(kad.generated.NodeInfo request,
        io.grpc.stub.StreamObserver<kad.generated.SecurityChallenge> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void joinValidate(kad.generated.SecurityChallenge request,
        io.grpc.stub.StreamObserver<kad.generated.NodeInfo> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getJoinValidateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * simplejoin
     * </pre>
     */
    public void join(kad.generated.NodeInfo request,
        io.grpc.stub.StreamObserver<kad.generated.NodeInfo> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getJoinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBlockChain(kad.generated.Ping request,
        io.grpc.stub.StreamObserver<kad.generated.foundValue> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetBlockChainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTransactionPool(kad.generated.Ping request,
        io.grpc.stub.StreamObserver<kad.generated.foundValue> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetTransactionPoolMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class KademliaBlockingStub extends io.grpc.stub.AbstractStub<KademliaBlockingStub> {
    private KademliaBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KademliaBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KademliaBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KademliaBlockingStub(channel, callOptions);
    }

    /**
     */
    public kad.generated.Ping ping(kad.generated.Ping request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public kad.generated.RequestStatus store(kad.generated.StorableValue request) {
      return blockingUnaryCall(
          getChannel(), getStoreMethod(), getCallOptions(), request);
    }

    /**
     */
    public kad.generated.NodeInfo findNode(kad.generated.KadID request) {
      return blockingUnaryCall(
          getChannel(), getFindNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public kad.generated.foundValue findValue(kad.generated.Value request) {
      return blockingUnaryCall(
          getChannel(), getFindValueMethod(), getCallOptions(), request);
    }

    /**
     */
    public kad.generated.RequestStatus broadcastMessage(kad.generated.Message request) {
      return blockingUnaryCall(
          getChannel(), getBroadcastMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public kad.generated.Message message(kad.generated.Message request) {
      return blockingUnaryCall(
          getChannel(), getMessageMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * join with proof of work challenge
     * </pre>
     */
    public kad.generated.SecurityChallenge joinRequest(kad.generated.NodeInfo request) {
      return blockingUnaryCall(
          getChannel(), getJoinRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<kad.generated.NodeInfo> joinValidate(
        kad.generated.SecurityChallenge request) {
      return blockingServerStreamingCall(
          getChannel(), getJoinValidateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * simplejoin
     * </pre>
     */
    public java.util.Iterator<kad.generated.NodeInfo> join(
        kad.generated.NodeInfo request) {
      return blockingServerStreamingCall(
          getChannel(), getJoinMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<kad.generated.foundValue> getBlockChain(
        kad.generated.Ping request) {
      return blockingServerStreamingCall(
          getChannel(), getGetBlockChainMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<kad.generated.foundValue> getTransactionPool(
        kad.generated.Ping request) {
      return blockingServerStreamingCall(
          getChannel(), getGetTransactionPoolMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class KademliaFutureStub extends io.grpc.stub.AbstractStub<KademliaFutureStub> {
    private KademliaFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private KademliaFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KademliaFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new KademliaFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kad.generated.Ping> ping(
        kad.generated.Ping request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kad.generated.RequestStatus> store(
        kad.generated.StorableValue request) {
      return futureUnaryCall(
          getChannel().newCall(getStoreMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kad.generated.NodeInfo> findNode(
        kad.generated.KadID request) {
      return futureUnaryCall(
          getChannel().newCall(getFindNodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kad.generated.foundValue> findValue(
        kad.generated.Value request) {
      return futureUnaryCall(
          getChannel().newCall(getFindValueMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kad.generated.RequestStatus> broadcastMessage(
        kad.generated.Message request) {
      return futureUnaryCall(
          getChannel().newCall(getBroadcastMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kad.generated.Message> message(
        kad.generated.Message request) {
      return futureUnaryCall(
          getChannel().newCall(getMessageMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * join with proof of work challenge
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<kad.generated.SecurityChallenge> joinRequest(
        kad.generated.NodeInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinRequestMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PING = 0;
  private static final int METHODID_STORE = 1;
  private static final int METHODID_FIND_NODE = 2;
  private static final int METHODID_FIND_VALUE = 3;
  private static final int METHODID_BROADCAST_MESSAGE = 4;
  private static final int METHODID_MESSAGE = 5;
  private static final int METHODID_JOIN_REQUEST = 6;
  private static final int METHODID_JOIN_VALIDATE = 7;
  private static final int METHODID_JOIN = 8;
  private static final int METHODID_GET_BLOCK_CHAIN = 9;
  private static final int METHODID_GET_TRANSACTION_POOL = 10;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final KademliaImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(KademliaImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PING:
          serviceImpl.ping((kad.generated.Ping) request,
              (io.grpc.stub.StreamObserver<kad.generated.Ping>) responseObserver);
          break;
        case METHODID_STORE:
          serviceImpl.store((kad.generated.StorableValue) request,
              (io.grpc.stub.StreamObserver<kad.generated.RequestStatus>) responseObserver);
          break;
        case METHODID_FIND_NODE:
          serviceImpl.findNode((kad.generated.KadID) request,
              (io.grpc.stub.StreamObserver<kad.generated.NodeInfo>) responseObserver);
          break;
        case METHODID_FIND_VALUE:
          serviceImpl.findValue((kad.generated.Value) request,
              (io.grpc.stub.StreamObserver<kad.generated.foundValue>) responseObserver);
          break;
        case METHODID_BROADCAST_MESSAGE:
          serviceImpl.broadcastMessage((kad.generated.Message) request,
              (io.grpc.stub.StreamObserver<kad.generated.RequestStatus>) responseObserver);
          break;
        case METHODID_MESSAGE:
          serviceImpl.message((kad.generated.Message) request,
              (io.grpc.stub.StreamObserver<kad.generated.Message>) responseObserver);
          break;
        case METHODID_JOIN_REQUEST:
          serviceImpl.joinRequest((kad.generated.NodeInfo) request,
              (io.grpc.stub.StreamObserver<kad.generated.SecurityChallenge>) responseObserver);
          break;
        case METHODID_JOIN_VALIDATE:
          serviceImpl.joinValidate((kad.generated.SecurityChallenge) request,
              (io.grpc.stub.StreamObserver<kad.generated.NodeInfo>) responseObserver);
          break;
        case METHODID_JOIN:
          serviceImpl.join((kad.generated.NodeInfo) request,
              (io.grpc.stub.StreamObserver<kad.generated.NodeInfo>) responseObserver);
          break;
        case METHODID_GET_BLOCK_CHAIN:
          serviceImpl.getBlockChain((kad.generated.Ping) request,
              (io.grpc.stub.StreamObserver<kad.generated.foundValue>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION_POOL:
          serviceImpl.getTransactionPool((kad.generated.Ping) request,
              (io.grpc.stub.StreamObserver<kad.generated.foundValue>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class KademliaBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KademliaBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return kad.generated.KademliaProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Kademlia");
    }
  }

  private static final class KademliaFileDescriptorSupplier
      extends KademliaBaseDescriptorSupplier {
    KademliaFileDescriptorSupplier() {}
  }

  private static final class KademliaMethodDescriptorSupplier
      extends KademliaBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    KademliaMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (KademliaGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KademliaFileDescriptorSupplier())
              .addMethod(getPingMethod())
              .addMethod(getStoreMethod())
              .addMethod(getFindNodeMethod())
              .addMethod(getFindValueMethod())
              .addMethod(getBroadcastMessageMethod())
              .addMethod(getMessageMethod())
              .addMethod(getJoinRequestMethod())
              .addMethod(getJoinValidateMethod())
              .addMethod(getJoinMethod())
              .addMethod(getGetBlockChainMethod())
              .addMethod(getGetTransactionPoolMethod())
              .build();
        }
      }
    }
    return result;
  }
}
