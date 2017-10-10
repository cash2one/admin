package com.jumper.angel.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import com.jumper.angel.utils.ConfigProUtils;

public class FastdfsUpload
{
  private static final Logger logger = Logger.getLogger(FastdfsUpload.class);

  public static String getConfigPath() {
    try {
      String classPath = new File(FastdfsUpload.class.getResource("/").getFile()).getCanonicalPath();
      String configFilePath = classPath + File.separator + "fdfs_client.conf";
      return configFilePath;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static TrackerClient getTracker() {
    try {
      String configFilePath = getConfigPath();
      if (StringUtils.isEmpty(configFilePath)) {
        System.err.println("the fdfs_client.conf config file is not found!!!");
      }
      ClientGlobal.init(configFilePath);

      TrackerClient trackerClient = new TrackerClient();
      return trackerClient;
    } catch (Exception e) {
      e.printStackTrace();
    }return null;
  }

  public static String upoladFile(String uploadFileName, InputStream inputStream)
  {
    String upPath = null;
    StorageClient1 client1 = null;
    try {
      byte[] file_buff = null;
      if (inputStream != null) {
        int len = inputStream.available();
        file_buff = new byte[len];
        inputStream.read(file_buff);
      }
      String fileExtName = uploadFileName;

      client1 = ConnectionPool.getPoolInstance().checkout(10);
      upPath = client1.upload_file1(file_buff, fileExtName, null);
      ConnectionPool.getPoolInstance().checkin(client1);
      inputStream.close();

      if (null != inputStream)
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
    catch (InterruptedException e)
    {
      if (null != inputStream)
        try {
          inputStream.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
    }
    catch (Exception e)
    {
      ConnectionPool.getPoolInstance().drop(client1);
      e.printStackTrace();

      if (null != inputStream)
        try {
          inputStream.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
    }
    finally
    {
      if (null != inputStream)
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
    return ConfigProUtils.get("BASE_FILE_URL") + "/" + upPath;
  }

  public static String upoladPrefixFile(String fileName, InputStream inputStream, String master_filename, String prefixName)
  {
    if (StringUtils.isEmpty(master_filename)) {
      return "the resource path master_filename can`t be empty!";
    }
    String group_remote_file = master_filename.substring(1, master_filename.length());
    int index = group_remote_file.indexOf("/");
    String group_name = group_remote_file.substring(0, index);
    String remote_filename = group_remote_file.substring(index + 1, group_remote_file.length());
    String file_ext_name = fileName.split("\\.")[1];
    try
    {
      String configFilePath = getConfigPath();
      if (StringUtils.isEmpty(configFilePath)) {
        System.err.println("the fdfs_client.conf config file is not found!!!");
        return "";
      }
      ClientGlobal.init(configFilePath);

      TrackerClient trackerClient = new TrackerClient();
      TrackerServer trackerServer = trackerClient.getConnection();
      StorageServer storageServer = null;
      StorageClient storageClient = new StorageClient(trackerServer, storageServer);

      System.out.println("fileLength:" + String.valueOf(inputStream.available() / 1000));

      byte[] file_buff = null;
      if (inputStream != null) {
        int len = inputStream.available();
        file_buff = new byte[len];
        inputStream.read(file_buff);
      }

      StorageServer[] storageServers = trackerClient.getStoreStorages(trackerServer, group_name);
      if (storageServers == null)
        System.err.println("get store storage servers fail, error code: " + storageClient.getErrorCode());
      else {
        for (int k = 0; k < storageServers.length; k++) {
          System.out.println(storageServers[k].getInetSocketAddress().getAddress().getHostAddress() + ":" + storageServers[k].getInetSocketAddress().getPort());
        }
      }

      String[] results = storageClient.upload_file(group_name, remote_filename, prefixName, file_buff, file_ext_name, null);

      if (results == null) {
        System.err.println("upload file fail, error code: " + storageClient.getErrorCode());
        return "";
      }

      String result_group_name = results[0];
      String result_remote_filename = results[1];
      System.out.println("result_group_name: " + result_group_name + ", result_remote_filename: " + result_remote_filename);

      ServerInfo[] servers = trackerClient.getFetchStorages(trackerServer, group_name, remote_filename);
      if (servers == null) {
        System.err.println("get storage servers fail, error code: " + trackerClient.getErrorCode());
      }
      return "/" + result_group_name + "/" + result_remote_filename;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public static void getFile() {
    String configFilePath = getConfigPath();
    if (StringUtils.isEmpty(configFilePath))
      System.err.println("the fdfs_client.conf config file is not found!!!");
    try
    {
      ClientGlobal.init(configFilePath);
      TrackerClient trackerClient = new TrackerClient();
      TrackerServer trackerServer = trackerClient.getConnection();
      StorageServer storageServer = null;
      StorageClient storageClient = new StorageClient(trackerServer, storageServer);

      String group_name = "group1";
      String remote_filename = "M00/00/00/CgAEuFUWdKOAVtEhAABQs6PtSR4209.jpg";
      FileInfo fi = storageClient.get_file_info(group_name, remote_filename);
      String sourceIpAddr = fi.getSourceIpAddr();
      long size = fi.getFileSize();
      System.out.println("ip:" + sourceIpAddr + ",size:" + size);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static boolean deleteFile(String filePath) {
    String configFilePath = getConfigPath();
    if (StringUtils.isEmpty(configFilePath)) {
      System.err.println("the fdfs_client.conf config file is not found!!!");
    }

    int flag = filePath.indexOf("/");
    String groupName = filePath.substring(0, flag);
    String fileName = filePath.substring(flag + 1, filePath.length());

    if ((StringUtils.isEmpty(groupName)) || (StringUtils.isEmpty(fileName))) {
      logger.error("groupName or fileName is empty!!!");
      return false;
    }
    try
    {
      ClientGlobal.init(configFilePath);

      TrackerClient trackerClient = new TrackerClient();
      TrackerServer trackerServer = trackerClient.getConnection();

      StorageServer storageServer = null;
      StorageClient storageClient = new StorageClient(trackerServer, storageServer);

      storageClient.delete_file(groupName, fileName);
      logger.info("delete file success!fileName is " + fileName);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public static void main(String[] args) {
    try {
      File file = new File("F:\\20161108\\1.jpg");
      InputStream inputStream = new FileInputStream(file);
      String path = upoladFile("1.jpg", inputStream);
      System.out.println(path);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}