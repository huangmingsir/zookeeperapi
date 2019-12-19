package com.zookeeper.api.client;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZKCreate {
	private static ZooKeeper zk;

	private static ZooKeeperConnection conn;

	public static void create(String path, byte[] data) throws KeeperException, InterruptedException {
		// String create = zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
		// CreateMode.EPHEMERAL_SEQUENTIAL);
		String create = zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println(create);
	}

	public static void main(String[] args) throws InterruptedException {

		String path = "/MyFirstZnode/sonnode4";

		byte[] data = "My first zookeeper node4".getBytes();

		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("192.168.10.233");
			create(path, data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conn.close();
		}
	}
}
