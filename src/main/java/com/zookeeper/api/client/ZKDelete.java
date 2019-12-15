package com.zookeeper.api.client;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.AsyncCallback.VoidCallback;
import org.apache.zookeeper.KeeperException;

public class ZKDelete {
   private static ZooKeeper zk;
   private static ZooKeeperConnection conn;

   public static void sync(String path) throws KeeperException,InterruptedException {
      zk.sync(path, new VoidCallback() {
		
		@Override
		public void processResult(int rc, String path, Object ctx) {
			System.out.println("同步的结果是：rc=" + rc + " path=" + path  
                    + ",context=" + ctx);
		}
	}, null);
   }

   public static void main(String[] args) throws InterruptedException,KeeperException {
      String path = "/MyFirstZnode"; 
		
      try {
         conn = new ZooKeeperConnection();
         zk = conn.connect("192.168.10.233");
         sync(path); 
      } catch(Exception e) {
         System.out.println(e.getMessage()); 
      }
   }
}