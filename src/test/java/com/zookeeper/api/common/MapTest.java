package com.zookeeper.api.common;

public class MapTest {

	public static void main(String[] args) {
		System.out.println(tableSizeFor(17));

	}
	
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 100) ? 100 : n + 1;
    }

}
