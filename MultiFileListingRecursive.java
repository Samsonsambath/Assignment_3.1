package hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileStatus;

public class MultiFileListingRecursive {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Atleast enter two argument");
			System.exit(1);
		}
		try {
			Configuration conf = new Configuration();
			for (String arg : args) {
				Path path = new Path(arg);
				recDirectory(path, conf);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	static void recDirectory(Path path, Configuration conf) throws IOException {
		FileSystem filesystem = FileSystem.get(path.toUri(), conf);
		FileStatus[] filestatuslocal = filesystem.listStatus(path);
		for (FileStatus fstat : filestatuslocal) {
			if (fstat.isDirectory()) {
				System.out.println("Directory: " + fstat.getPath());
				recDirectory(fstat.getPath(), conf);
			} else if (fstat.isFile()) {
				System.out.println("File: " + fstat.getPath());
			} else if (fstat.isSymlink()) {
				System.out.println("Symlink: " + fstat.getPath());
			}
		}
	}
}
