package com.zookeeper.api.client;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZKCreate {
	// create static instance for zookeeper class.
	private static ZooKeeper zk;

	// create static instance for ZooKeeperConnection class.
	private static ZooKeeperConnection conn;

	// Method to create znode in zookeeper ensemble
	public static void create(String path, byte[] data) throws KeeperException, InterruptedException {
		// String create = zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
		// CreateMode.EPHEMERAL_SEQUENTIAL);
		String create = zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(create);
	}

	public static void main(String[] args) throws InterruptedException {

		// znode path
		String path = "/MyFirstZnode/sonnode2"; // Assign path to znode

		// data in byte array
		byte[] data = "My first zookeeper node".getBytes(); // Declare data

		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("192.168.10.233");
			create(path, data); // Create the data to the specified path
		} catch (Exception e) {
			System.out.println(e.getMessage()); // Catch error message
		} finally {
			conn.close();
		}
	}
}
