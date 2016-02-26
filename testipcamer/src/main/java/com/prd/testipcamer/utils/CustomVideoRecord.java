package com.prd.testipcamer.utils;

import android.os.Environment;
import android.util.Log;

import com.prd.testipcamer.PlayActivity;
import com.prd.testipcamer.bean.VideoRecordBean;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * �本地视频录像方法
 * 注意：本地视频录像出来的格式是 H264格式 如需要用第三方播放器播放 需要支持H264格式
 * **/
public class CustomVideoRecord implements PlayActivity.VideoRecorder {

	private LinkedList<VideoRecordBean> dataBuff = null;
	private boolean startRecording = false;
	private String strDID;
	private FileOutputStream outStream;
	private Thread mThread;
	private int videoEnd = 0;
	private int videoSumTime = 0;
	private int sum = 0;
	private long startTime = 0;
	private String videopath;
	private int sumFrame = 0;
	private int type = 0;// ��������1:yuv 2:jpg
	private boolean isBuffOut = false;
	private boolean isFirstH264 = true;
	private DatabaseUtil dbUtil;

	public CustomVideoRecord(PlayActivity playActivity, String did) {
		playActivity.setVideoRecord(this);
		dbUtil = new DatabaseUtil(playActivity);
		strDID = did;
	}
	

	public void startRecordVideo(int type) {
		synchronized (this) {
			try {
				this.type = type;
				startRecording = true;
				isFirstH264 = true;
				startTime = (new Date()).getTime() / 1000;
				sumFrame = 0;
				sum = 0;
				dataBuff = new LinkedList<VideoRecordBean>();
				Log.d("tag", "start record video");
				mThread = new Thread(new myRunnable());
				mThread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void stopRecordVideo() {
		synchronized (this) {
			startRecording = false;
			long time = (new Date()).getTime() / 1000;
			videoSumTime = (int) (time - startTime);
		}
	}

	@Override
	public void VideoRecordData(int t, byte[] videodata, int width, int height,
			int tspan) {
		Log.i("tag", "------------------");
		if (startRecording) {
			if (dataBuff.size() <= 10) {
				Log.d("tag", "dataBuff<10");
				VideoRecordBean bean = new VideoRecordBean();
				switch (type) {
				case 1: {// h264
					if (isFirstH264) {// ��ʼ¼�񣬵�һ֡������i֡
						if (t != 0) {
							Log.d("tag", "第一帧不是i帧");
							return;
						} else {
							isFirstH264 = false;
							Log.d("tag", "第一帧是i帧");
						}

					}
					if (isBuffOut) {
						isBuffOut = false;
						if (t != 0) {//如果不是i帧,则丢掉
							return;
						}
					}
					Log.i("tag", "------------------111111111");
					bean.setPicture(videodata);
					bean.setType(t);
					bean.setTspan(tspan);
					bean.setWidth(width);
					dataBuff.add(bean);
				}
					break;
				case 2: {// jpg
					bean.setPicture(videodata);
					bean.setWidth(width);
					bean.setHeight(height);
					bean.setTspan(tspan);
					dataBuff.addLast(bean);
				}
					break;
				default:
					break;
				}

			} else {
				isBuffOut = true;
				Log.d("tag", "dataBuff>10");
			}
		}
	}

	private class myRunnable implements Runnable {

		@Override
		public void run() {

			try {
				File div = new File(Environment.getExternalStorageDirectory(),
						"ipcamera/video");
				if (!div.exists()) {
					div.mkdirs();
				}
				String strDate = getStrDate();
				String p = strDate + "_" + strDID;
				File file = new File(div, p);
				Log.d("tag",
						"start record video  fileName:"
								+ file.getAbsolutePath());
				videopath = file.getAbsolutePath();
				outStream = new FileOutputStream(new File(videopath), true);
				Log.d("tag", "���� type:" + type);
				switch (type) {
				case 1:// fileHeader 1:h264����
					byte[] header = intToByte(1);
					outStream.write(header);
					break;
				case 2: // fileHeader 2:jpg����
					Log.d("tag", "jpg����");
					byte[] header2 = intToByte(2);
					outStream.write(header2);
					break;
				default:
					break;
				}
				Log.d("tag", "outStream:" + outStream);
				while (true && startRecording) {
					if (dataBuff.size() > 0) {
						sumFrame++;
						Log.d("tag", "�ܹ�¼��" + sumFrame + " ֡    ���ļ���д��һ֡");
						VideoRecordBean bean = dataBuff.poll();
						if (type == 1) {// h264����
							int size = bean.getWidth();
							int t = bean.getType();
							int tspan = bean.getTspan();
							byte[] sizebyte = intToByte(size);
							byte[] typebyte = intToByte(t);
							byte[] picture = bean.getPicture();
							byte[] time = intToByte(tspan);
							Log.i("tag", "------------------2222222222222");
							outStream.write(sizebyte);
							outStream.write(typebyte);
							outStream.write(time);
							outStream.write(picture);
							sum += tspan;
						} else {// jpg����
							byte[] picture = bean.getPicture();
							byte[] length = intToByte(picture.length);
							int tspan = bean.getTspan();
							byte[] time = intToByte(tspan);
							Log.d("tag", "¼��ʱ��֡ʱ��� Tspan: " + tspan);
							sum += tspan;
							outStream.write(length);
							outStream.write(time);
							outStream.write(picture);
						}
						outStream.flush();
					} else {
						Log.d("tag", "没有图片 暂停1秒");
						Thread.sleep(1000);
					}
				}
				Log.d("tag", "录制总时间 videoSumTime:" + videoSumTime);
				Log.d("tag", "一共总的帧数：sumFrame = " + sumFrame);
				byte[] endBytes = intToByte(videoEnd);
				byte[] sumTimeBytes = intToByte(sum);
				outStream.write(endBytes);
				outStream.write(sumTimeBytes);
				outStream.flush();
				// 清除缓存
				dataBuff.clear();
				dataBuff = null;
				// 向数据库中写入录像文件记录
				Log.d("tag", "向数据库中写入录像文件记录");
				dbUtil.open();
				dbUtil.createVideoOrPic(strDID, videopath,
						DatabaseUtil.TYPE_VIDEO, "2323");
				dbUtil.close();
				if (outStream != null) {
					outStream.close();
					outStream = null;
				}
			} catch (Exception e) {
				Log.d("tag", "保存录像文件异常:" + e.getMessage());
				e.printStackTrace();
			}
		}
	};

	/**
	 * ��intת����byte����
	 * **/
	public static byte[] intToByte(int number) {
		int temp = number;
		byte[] b = new byte[4];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(temp & 0xff).byteValue();// �����λ���������λ
			temp = temp >> 8;// ������8λ
		}
		return b;
	}

	/**
	 * ��byte����ת����int
	 * **/
	public static int byteToInt(byte[] b) {
		int s = 0;
		int s0 = b[0] & 0xff;// ���λ
		int s1 = b[1] & 0xff;
		int s2 = b[2] & 0xff;
		int s3 = b[3] & 0xff;
		s3 <<= 24;
		s2 <<= 16;
		s1 <<= 8;
		s = s0 | s1 | s2 | s3;
		return s;
	}

	/**
	 * ��longת����byte[]
	 * */
	public static byte[] longToByte(long number) {
		long temp = number;
		byte[] b = new byte[8];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Long(temp & 0xff).byteValue();// �����λ���������λ
			temp = temp >> 8;// ������8λ
		}
		return b;

	}

	/**
	 * ��byte[]ת����long
	 * */
	public static long byteToLong(byte[] b) {
		long s = 0;
		long s0 = b[0] & 0xff;// ���λ
		long s1 = b[1] & 0xff;
		long s2 = b[2] & 0xff;
		long s3 = b[3] & 0xff;
		long s4 = b[4] & 0xff;// ���λ
		long s5 = b[5] & 0xff;
		long s6 = b[6] & 0xff;
		long s7 = b[7] & 0xff; // s0����
		s1 <<= 8;
		s2 <<= 16;
		s3 <<= 24;
		s4 <<= 8 * 4;
		s5 <<= 8 * 5;
		s6 <<= 8 * 6;
		s7 <<= 8 * 7;
		s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
		return s;
	}

	private String getStrDate() {
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
		String strDate = f.format(d);
		Log.d("tag", "record strDate:" + strDate);
		return strDate;
	}
}