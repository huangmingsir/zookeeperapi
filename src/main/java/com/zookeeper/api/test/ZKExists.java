package com.zookeeper.api.test;


import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;

public class ZKExists {
   private static ZooKeeper zk;
   private static ZooKeeperConnection conn;

   // Method to check existence of znode and its status, if znode is available.
   public static Stat znode_exists(String path) throws
      KeeperException,InterruptedException {
      return zk.exists(path, true);
   }

   public static void main(String[] args) throws InterruptedException,KeeperException {
      String path = "/SecondZnode"; // Assign znode to the specified path
			
      try {
         conn = new ZooKeeperConnection();
         zk = conn.connect("192.168.10.233");
         Stat stat = znode_exists(path); // Stat checks the path of the znode
				
         if(stat != null) {
            System.out.println("Node exists and the node version is " +
            stat.getVersion());
         } else {
            System.out.println("Node does not exists");
         }
				
      } catch(Exception e) {
         System.out.println(e.getMessage()); // Catches error messages
      }
   }
}
