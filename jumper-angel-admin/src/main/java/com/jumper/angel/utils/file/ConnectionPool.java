package com.jumper.angel.utils.file;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;

import com.jumper.angel.utils.ConfigProUtils;

public class ConnectionPool
{
  private int size = 5;

  private ConcurrentHashMap<StorageClient1, Object> busyConnectionPool = null;

  private ArrayBlockingQueue<StorageClient1> idleConnectionPool = null;

  private Object obj = new Object();

  private static ConnectionPool instance = new ConnectionPool();

  private ConnectionPool()
  {
    this.busyConnectionPool = new ConcurrentHashMap();
    this.idleConnectionPool = new ArrayBlockingQueue(this.size);
    init(this.size);
  }

  public static ConnectionPool getPoolInstance()
  {
    return instance;
  }

  private void init(int size)
  {
    initClientGlobal();
    TrackerServer trackerServer = null;
    try {
      TrackerClient trackerClient = new TrackerClient();
      trackerServer = trackerClient.getConnection();
      for (int i = 0; i < size; i++) {
        StorageServer storageServer = null;
        StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);

        this.idleConnectionPool.add(client1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (trackerServer != null)
        try {
          trackerServer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
  }

  public StorageClient1 checkout(int waitTimes)
    throws InterruptedException
  {
    StorageClient1 client1 = (StorageClient1)this.idleConnectionPool.poll(waitTimes, TimeUnit.SECONDS);
    this.busyConnectionPool.put(client1, this.obj);
    return client1;
  }

  public void checkin(StorageClient1 client1)
  {
    if (this.busyConnectionPool.remove(client1) != null)
      this.idleConnectionPool.add(client1);
  }

  public void drop(StorageClient1 client1)
  {
    if (this.busyConnectionPool.remove(client1) != null) {
      TrackerServer trackerServer = null;
      try {
        TrackerClient trackerClient = new TrackerClient();

        trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient1 newClient1 = new StorageClient1(trackerServer, storageServer);
        this.idleConnectionPool.add(newClient1);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (trackerServer != null)
          try {
            trackerServer.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
      }
    }
  }

  private void initClientGlobal()
  {
    InetSocketAddress[] trackerServers = new InetSocketAddress[1];
    trackerServers[0] = new InetSocketAddress(ConfigProUtils.get("FASTDFS_ADDRESS"), Integer.valueOf(ConfigProUtils.get("FASTDFS_PORT")).intValue());

    ClientGlobal.setG_tracker_group(new TrackerGroup(trackerServers));

    ClientGlobal.setG_connect_timeout(2000);
    ClientGlobal.setG_network_timeout(30000);
    ClientGlobal.setG_anti_steal_token(false);
    ClientGlobal.setG_charset("UTF-8");
    ClientGlobal.setG_secret_key(null);
  }
}