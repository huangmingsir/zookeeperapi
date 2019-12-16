package com.zookeeper.api.client;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperConnection {

	   // declare zookeeper instance to access ZooKeeper ensemble
	   private ZooKeeper zoo;
	   final CountDownLatch connectedSignal = new CountDownLatch(1);

	   // Method to connect zookeeper ensemble.
	   public ZooKeeper connect(String host) throws IOException,InterruptedException {
		
	      zoo = new ZooKeeper(host,5000,new Watcher() {
			
	         public void process(WatchedEvent we) {

	            if (we.getState() == KeeperState.SyncConnected) {
	            	System.out.println("完成一次version、zxid的同步");
	               connectedSignal.countDown();
	            }
	         }
	      });
		  //这里为什么要用connectedSignal.await();的目的是因为new ZooKeeper也创建了一个连接线程，调用它的线程在进行zookeeper操作时，为了能正常保持连接完成操作，就
	      //需要阻塞此连接线程让操作完成后再调用connectedSignal.countDown();释放连接线程。
	      connectedSignal.await();
	      return zoo;
	   }

	   // Method to disconnect from zookeeper server
	   public void close() throws InterruptedException {
	      zoo.close();
	   }
}
