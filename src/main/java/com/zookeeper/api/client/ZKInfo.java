package com.zookeeper.api.client;


import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class ZKInfo {
   private static ZooKeeper zk;
   private static ZooKeeperConnection conn;

   public static void main(String[] args) throws InterruptedException,KeeperException {
      try {
         conn = new ZooKeeperConnection();
         zk = conn.connect("192.168.10.233");
         long sessionId = zk.getSessionId();
         System.out.println(sessionId);
      } catch(Exception e) {
         System.out.println(e.getMessage()); // Catches error messages
      }finally {
      	conn.close();
  	  }
   }
}
