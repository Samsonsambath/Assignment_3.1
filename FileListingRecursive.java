package hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileStatus;

public class FileListingRecursive {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Atleast enter one argument");
			System.exit(1);
		}
		try {
			Path path = new Path(args[0]);

			Configuration conf = new Configuration();
			recDirectory(path, conf);
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
