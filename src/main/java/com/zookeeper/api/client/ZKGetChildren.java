package com.zookeeper.api.client;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKGetChildren {
	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;

	public static Stat znode_exists(String path) throws KeeperException, InterruptedException {
		return zk.exists(path, true);
	}

	public static void main(String[] args) throws InterruptedException, KeeperException {
		String path = "/";

		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("192.168.10.233");
			Stat stat = znode_exists(path);

			if (stat != null) {
				List<String> children = zk.getChildren(path, false);
				for (int i = 0; i < children.size(); i++)
					System.out.println("children's :" + children.get(i));
			} else {
				System.out.println("Node does not exists");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conn.close();
		}

	}

}