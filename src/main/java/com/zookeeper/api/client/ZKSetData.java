package com.zookeeper.api.client;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class ZKSetData {
	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;

	public static void update(String path, byte[] data) throws KeeperException, InterruptedException {
		zk.setData(path, data, zk.exists(path, true).getVersion());
	}

	public static void main(String[] args) throws InterruptedException, KeeperException {
		String path = "/MyFirstZnode";
		byte[] data = "Success2".getBytes(); 

		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("192.168.10.233");
			update(path, data); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conn.close();
		}
	}
}