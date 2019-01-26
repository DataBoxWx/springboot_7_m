package com.bjpowernode.p2p.admin.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * FastDFS文件上传
 * 
 * @author yanglijun
 *
 */
public class FastdfsClient {

	/**
	 * FastDFS上传文件
	 * 
	 * @param fileByte
	 * @param fileExtendsName
	 * @return
	 */
	public static String[] updateFileToFastdfs (byte[] fileByte, String fileExtendsName) {
		TrackerClient trackerClient = null;
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;
		StorageClient storageClient = null;
		String[] arry = null;
		try {
			//使用fastdfs上传文件
			ClientGlobal.init("fastdfs_client.conf");
			
			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageServer = trackerClient.getStoreStorage(trackerServer);
			
			storageClient = new StorageClient(trackerServer, storageServer);
			//文件上传
			arry = storageClient.upload_file(fileByte, fileExtendsName, null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		} finally {
			try {
				storageServer.close();
				trackerServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arry;
	}
}