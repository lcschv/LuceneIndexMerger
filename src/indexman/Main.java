package indexman;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IndexMerger indexmerger;
		if(args.length != 2){
			System.out.println("Usage: java -jar IndexMerger.jar " +
					           "existing_indexes_dir merged_index_dir");
			System.out.println(" existing_indexes_dir: A directory where the " +
					             "indexes that have to merged exist");
			System.out.println("   e.g. indexes/");
			System.out.println("   e.g.         index1");
			System.out.println("   e.g.         index2");
			System.out.println("   e.g.         index3");
			System.out.println(" merged_index_dir: A directory where the merged " +
					                               "index will be stored");
			System.out.println("   e.g. merged_indexes");
			String INDEXES_DIR  = "/mnt/lucas/Indexes";
			String MERGED_DIR = "/mnt/lucas/Merged";
			System.out.println("Chamei!");
			indexmerger = new IndexMerger(INDEXES_DIR, MERGED_DIR);
		}

//		File INDEXES_DIR  = new File(args[0]);
//		File INDEX_DIR    = new File(args[1]);
		
//		File INDEX_DIR    = new File(args[1]);
	}

}
