package kad.kademlia.grpc;

import java.io.IOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.InetSocketAddress;

import java.util.concurrent.TimeUnit;

import blockchain.BlockchainHandler;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import kad.kademlia.Storage;
import kad.kademlia.network.Node;
import ledger.Settings;

public class KademliaServer {
	private Server server;
	private final int port;
	private boolean running;
	private Node localNode;
	private Storage storage;
	private KademliaOperation operations;
	
	ExecutorService executor;
	
	public KademliaServer(Node localNode, Storage storage, KademliaOperation operations, BlockchainHandler blockchainNode) {
		this.localNode = localNode;
		this.port = this.localNode.getHost().getPort();
		this.storage = storage;
		this.operations = operations;

		KademliaService service = new KademliaService(localNode,storage,operations, blockchainNode);
		this.server = ServerBuilder.forPort(this.port).addService(service).build();

		this.running = false;
		
		
	}

	public void start() {
		if (this.running) {
			return;
		}
		try {

			
			this.server.start();
			System.out.println("Server started, listening on port " + this.port);
			this.server.awaitTermination();

			this.running = true;
		} catch (IOException | InterruptedException  e1) { 
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may have been reset by its JVM shutdown
				// hook.
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				KademliaServer.this.stop();
				System.err.println("*** server shut down");
			}
		});
	}

	public boolean isRunning() {
		return this.server.isShutdown();
	}

	public void stop() {
		if (this.server != null) {
			try {
				this.server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
				this.running = false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
