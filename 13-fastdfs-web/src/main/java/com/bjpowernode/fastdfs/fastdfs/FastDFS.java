package com.bjpowernode.fastdfs.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * ClassName:FastDFS
 * package:com.bjpowernode.fastdfs
 * Descrption:
 *
 * @Date:2018/7/30 16:30
 * @Author:724班
 */
public class FastDFS {

    public static  final String CONF_FILE = "fastdfs_client.conf";

    public static TrackerServer trackerServer = null;

    public static StorageServer storageServer = null;

    /**
     * 获取storage的客户端对象
     *
     * @return
     */
    public static StorageClient getStorageClient() {
        StorageClient storageClient = null;
        try {
            //1、初始化FastDFS的连接信息
            ClientGlobal.init(CONF_FILE);

            //2、创建一个Tracker的客户端对象
            TrackerClient trackerClient = new TrackerClient();

            //3、通过Tracker的客户端对象，获取一个Tracker的服务器对象
            trackerServer = trackerClient.getConnection();

            //4、通过Tracker的客户端对象 和 Tracker的服务器对象，获取一个Storage服务器对象
            storageServer = trackerClient.getStoreStorage(trackerServer);

            //5、通过 Tracker的服务器对象 和 Storage服务器对象，获取一个Storage的客户端对象
            storageClient = new StorageClient(trackerServer, storageServer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return storageClient;
    }

    /**
     * 关闭FastDFS
     *
     */
    public static void closeFastDFS() {
        try {
            if (null != storageServer) {
                storageServer.close();
            }
            if (null != trackerServer) {
                trackerServer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}