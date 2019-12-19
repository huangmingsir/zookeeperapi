package com.zookeeper.api.client;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.KeeperException;

public class ZKDelete {
	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;

	public static void delete(String path) throws KeeperException, InterruptedException {
		zk.delete(path, 1);
	}

	public static void main(String[] args) throws InterruptedException, KeeperException {
		String path = "/MyFirstZnode";

		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("192.168.10.233");
			delete(path);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conn.close();
		}
	}
}