package com.zookeeper.api.client;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKGetData {

	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;

	public static Stat znode_exists(String path) throws KeeperException, InterruptedException {
		return zk.exists(path, true);
	}

	public static void main(String[] args) throws InterruptedException, KeeperException {
		String path = "/MyFirstZnode";
		final CountDownLatch connectedSignal = new CountDownLatch(1);

		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("192.168.10.233:2181");
			Stat stat = znode_exists(path);

			if (stat != null) {
				byte[] b = zk.getData(path, new Watcher() {

					public void process(WatchedEvent we) {

						if (we.getType() == Event.EventType.None) {
							switch (we.getState()) {
							case Expired:
								System.out.println("会话过期，自动推出");
								connectedSignal.countDown();
								break;
							default:
								break;
							}
						} else {
							// String path = "/SecondZnode";
							try {
								byte[] bn = zk.getData(path, false, null);
								String data = new String(bn, "UTF-8");
								System.out.println("监听器中获取的值：" + data);
								connectedSignal.countDown();
							} catch (Exception ex) {
								System.out.println(ex.getMessage());
							}
						}
					}
				}, null);

				String data = new String(b, "UTF-8");
				System.out.println("正常获取的值：" + data);
				connectedSignal.await();

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
