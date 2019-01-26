package com.bjpowernode.fastdfs;

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

    public static void main(String[] args) {

        TrackerServer trackerServer = null;

        StorageServer storageServer = null;
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
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            //有了上面的5步，接下来就可以操作FastDFS文件系统了

            //a、文件上传
            String[] strArray = storageClient.upload_file("c:/dev/aa.txt", "txt", null);
            for (String str : strArray) {
                System.out.println("上传结果：" + str);
            }

            //b、文件下载，返回值为0表示下载成功，其他值都是失败，其他值可能有多种情况，-22、-9
            int down = storageClient.download_file(strArray[0], strArray[1], "c:/dev/bb.txt");
            System.out.println("下载结果：" + down);

            //c、文件删除，返回值为0表示删除成功，其他值都是失败，其他值可能有多种情况，-22、-9
            int delete = storageClient.delete_file(strArray[0], strArray[1]);
            System.out.println("删除结果：" + delete);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
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
}