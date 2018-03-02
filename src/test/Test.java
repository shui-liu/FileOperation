package test;

public class Test {
	public static void main(String[] args) {
		FileUtil fu = new FileUtil();

		System.out.println(new String(fu
				.getBytesFromFile("F:/Code/eclipse/workspace/FileOperation/test/test_get/test_getBytesFromFile.txt")));

		fu.read("F:/Code/eclipse/workspace/FileOperation/test/test_rw/test_read.txt");

		fu.write("F:/Code/eclipse/workspace/FileOperation/test/test_rw/test_write.txt");
		fu.read("F:/Code/eclipse/workspace/FileOperation/test/test_rw/test_write.txt");

		fu.readOnlyText("F:/Code/eclipse/workspace/FileOperation/test/test_rw/test_readOnlyText.txt");

		fu.writeOnlyText("F:/Code/eclipse/workspace/FileOperation/test/test_rw/test_writeOnlyText.txt");
		fu.readOnlyText("F:/Code/eclipse/workspace/FileOperation/test/test_rw/test_writeOnlyText.txt");

		fu.copyFileOnlyText("F:/Code/eclipse/workspace/FileOperation/test/test_copy/test_copyFileOnlyText/src/src.txt",
				"F:/Code/eclipse/workspace/FileOperation/test/test_copy/test_copyFileOnlyText/dest/dest.txt");
		fu.readOnlyText("F:/Code/eclipse/workspace/FileOperation/test/test_copy/test_copyFileOnlyText/dest/dest.txt");

		System.out.println("1.文件拷贝测试：");
		System.out.println("(1)拷贝前：");
		fu.printFile("F:/Code/eclipse/workspace/FileOperation/test/test_copy/test_copyFile");
		fu.copyFile("F:/Code/eclipse/workspace/FileOperation/test/test_copy/test_copyFile/src/src.png",
				"F:/Code/eclipse/workspace/FileOperation/test/test_copy/test_copyFile/dest/dest.png");
		System.out.println("(2)拷贝后：");
		fu.printFile("F:/Code/eclipse/workspace/FileOperation/test/test_copy/test_copyFile");

		System.out.println("2.文件夹拷贝测试：");
		System.out.println("(1)拷贝前：");
		fu.printFile("F:/Code/eclipse/workspace/FileOperation/test/test_copy/test_copyDir");
		fu.copyDir("F:/Code/eclipse/workspace/FileOperation/test/test_copy/test_copyDir/src",
				"F:/Code/eclipse/workspace/FileOperation/test/test_copy/test_copyDir/dest");
		System.out.println("(2)拷贝后：");
		fu.printFile("F:/Code/eclipse/workspace/FileOperation/test/test_copy/test_copyDir");

		System.out.println("3.文件分割测试：");
		System.out.println("(1)分割前：");
		fu.printFile("F:/Code/eclipse/workspace/FileOperation/test/test_splitFile");
		fu.split("F:/Code/eclipse/workspace/FileOperation/test/test_splitFile/test_splitFile.txt", 10000);
		System.out.println("(2)分割后：");
		fu.printFile("F:/Code/eclipse/workspace/FileOperation/test/test_splitFile");
	}
}