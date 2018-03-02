package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

public class FileUtil {
	// 读取到字节数组
	public byte[] getBytesFromFile(String srcpath) {
		File src = new File(srcpath);
		return getBytesFromFile(src);
	}

	public byte[] getBytesFromFile(File src) {
		byte[] dest = null;
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(src));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] flush = new byte[1024];
			int len = 0;
			while (-1 != (len = is.read(flush))) {
				bos.write(flush, 0, len);
			}
			bos.flush();
			dest = bos.toByteArray();
			bos.close();
			is.close();

		} catch (IOException e) {
			System.out.println("获取字节数组失败");
			e.printStackTrace();
		}
		return dest;

	}

	// 读取
	public void read(String srcpath) {
		File src = new File(srcpath);
		read(src);
	}

	public void read(File src) {
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(src));
			byte[] flush = new byte[1024];
			int len = 0;
			while (-1 != (len = is.read(flush))) {
				String info = new String(flush, 0, len);
				System.out.print(info);
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			System.out.println("源文件不存在");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("读取文件失败");
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					System.out.println("关闭字节输入流失败");
					e.printStackTrace();
				}
			}
		}
	}

	// 纯文本读取
	public void readOnlyText(String srcpath) {
		readOnlyText(srcpath, "GBK");
	}

	public void readOnlyText(String srcpath, String code) {
		File src = new File(srcpath);
		readOnlyText(src, code);
	}

	public void readOnlyText(File src) {
		readOnlyText(src, "GBK");
	}

	public void readOnlyText(File src, String code) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(src), code));
			String info = null;
			;
			while (null != (info = reader.readLine())) {
				System.out.print(info);
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			System.out.println("源纯文本文件不存在");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("纯文本文件读取失败");
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println("关闭字符输入流失败");
					e.printStackTrace();
				}
			}
		}
	}

	// 写入
	public void write(String destpath) {
		File dest = new File(destpath);
		write(dest);
	}

	public void write(File dest) {
		OutputStream os = null;
		try {
			os = new BufferedOutputStream(new FileOutputStream(dest));
			String str = "写入文件成功！";
			byte[] data = str.getBytes();
			os.write(data, 0, data.length);
			os.flush();
		} catch (FileNotFoundException e) {
			System.out.println("该文件不存在");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("写入文件失败");
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					System.out.println("关闭字节输出流失败");
					e.printStackTrace();
				}
			}
		}
	}

	// 纯文本写入
	public void writeOnlyText(String destpath) {
		writeOnlyText(destpath, "GBK");
	}

	public void writeOnlyText(String destpath, String code) {
		File dest = new File(destpath);
		writeOnlyText(dest, code);
	}

	public void writeOnlyText(File dest) {
		writeOnlyText(dest, "GBK");
	}

	public void writeOnlyText(File dest, String code) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest), code));
			String str = "写入纯文本文件成功！";
			writer.write(str);
			// writer.append("");
			writer.flush();
		} catch (FileNotFoundException e) {
			System.out.println("该纯文本文件不存在");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("写入纯文本文件失败");
			e.printStackTrace();
		} finally {
			try {
				if (null != writer) {
					writer.close();
				}
			} catch (IOException e) {
				System.out.println("关闭字符输出流失败");
				e.printStackTrace();
			}
		}
	}

	// 文件拷贝
	public void copyFile(String srcpath, String destpath) {
		File src = new File(srcpath);
		File dest = new File(destpath);
		try {
			copyFile(src, dest);
		} catch (IOException e) {
			System.out.println("拷贝文件失败");
			e.printStackTrace();
		}
	}

	public void copyFile(File src, File dest) throws IOException {
		if (!src.isFile()) {
			throw new IOException("仅能拷贝文件");
		}
		if (dest.isDirectory()) {
			throw new IOException("不能建立同名文件");
		}
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(src));
			OutputStream os = new BufferedOutputStream(new FileOutputStream(dest));
			byte[] flush = new byte[1024];
			int len = 0;
			while (-1 != (len = is.read(flush))) {
				os.write(flush, 0, len);
			}
			os.flush();
			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			System.out.println("该文件不存在");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件拷贝失败");
			e.printStackTrace();
		}

	}

	// 纯文本文件拷贝
	public void copyFileOnlyText(String srcpath, String destpath) {
		File src = new File(srcpath);
		File dest = new File(destpath);
		try {
			copyFile(src, dest);
		} catch (IOException e) {
			System.out.println("纯文本文件拷贝失败");
			e.printStackTrace();
		}
	}

	public void copyFileOnlyText(File src, File dest) throws IOException {
		if (!src.isFile()) {
			throw new IOException("仅能拷贝文件");
		}
		if (dest.isDirectory()) {
			throw new IOException("不能建立同名文件");
		}
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(src));
			writer = new BufferedWriter(new FileWriter(dest));
			/*
			 * char[] flush = new char[1024]; int len = 0;
			 * while(-1!=(len=reader.read(flush))){ writer.write(flush, 0, len); }
			 */
			String line = null;
			while (null != (line = reader.readLine())) {
				writer.write(line);
			}
			System.out.println();
			writer.flush();
			writer.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("该纯文本文件不存在");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("纯文本文件拷贝失败");
			e.printStackTrace();
		}
	}

	// 文件夹拷贝
	public void copyDir(String srcpath, String destpath) {
		File src = new File(srcpath);
		File dest = new File(destpath);
		copyDir(src, dest);
	}

	public void copyDir(File src, File dest) {
		if (src.isDirectory()) {
			dest = new File(dest, src.getName());
		}
		copyDirDetail(src, dest);
	}

	public void copyDirDetail(File src, File dest) {
		if (src.isFile()) {
			try {
				copyFile(src, dest);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (src.isDirectory()) {
			dest.mkdirs();
			for (File sub : src.listFiles()) {
				copyDirDetail(sub, new File(dest, sub.getName()));
			}
		}
	}

	// 文件分割
	public void split(String srcPath, long blockSize) {
		File src = null;
		if (null == srcPath || !(src = new File(srcPath)).exists() || src.isDirectory()) {
			return;
		}
		blockSize = Math.min(blockSize, src.length());
		int count = (int) (Math.ceil(src.length() * 1.0 / blockSize));
		long pos = 0;
		for (int i = 1; i <= count; i++) {
			if (i == count) {
				blockSize = src.length() - pos;
			}
			File block = null;
			block = new File(src.getParent() + "/" + "part" + i + ".txt");
			RandomAccessFile raf = null;
			BufferedOutputStream bos = null;
			try {
				raf = new RandomAccessFile(src, "r");
				bos = new BufferedOutputStream(new FileOutputStream(block));
				raf.seek(pos);
				byte[] flush = new byte[1024];
				int len = 0;
				long size = blockSize;
				while (-1 != (len = raf.read(flush))) {
					if (size >= len) {
						bos.write(flush, 0, len);
						size -= len;
					} else {
						bos.write(flush, 0, (int) size);
						break;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bos.close();
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			pos += blockSize;
		}
	}

	// 文件夹层次图
	public void printFile(String srcpath) {
		File src = new File(srcpath);
		printFile(src, 0);
	}

	public void printFile(File src) {
		printFile(src, 0);
	}

	public void printFile(File src, int level) {
		if (src == null || !src.exists()) {
			return;
		}
		for (int i = 0; i < level * 2; i++) {
			System.out.print(" ");
		}
		System.out.println("--" + src.getName());
		if (src.isDirectory()) {
			level++;
			for (File sub : src.listFiles()) {
				printFile(sub, level);
			}
		}
	}
}