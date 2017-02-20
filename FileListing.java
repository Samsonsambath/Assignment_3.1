package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileStatus;

public class FileListing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 1) {
			System.out.println("Atleast enter one argument");
			System.exit(1);
		}
		Path path = new Path(args[0]);
		try {
			Configuration conf = new Configuration();
			FileSystem filesystem = FileSystem.get(path.toUri(), conf);
			FileStatus[] filestatus = filesystem.listStatus(path);

			for (FileStatus fstat : filestatus) {
				if (fstat.isDirectory()) {
					System.out.println("Directory: " + fstat.getPath());
				} else if (fstat.isFile()) {
					System.out.println("File: " + fstat.getPath());
				} else if (fstat.isSymlink()) {
					System.out.println("Symlink: " + fstat.getPath());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
