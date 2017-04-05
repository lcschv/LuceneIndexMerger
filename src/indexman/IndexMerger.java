package indexman;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.ConcurrentMergeScheduler;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.en.EnglishAnalyzer;

public class IndexMerger {
	private String indexesPath;
	
	private final Path mergedIndexesPath;
	private File INDEXES_DIR;
	private Directory MERGEDINDEX_DIR;
	private IndexWriter writer;
	private Directory indexes [];
	
	public IndexMerger(String indexesPath, String mergedIndexesPath){
		this.indexesPath = indexesPath;
		this.mergedIndexesPath = Paths.get(mergedIndexesPath);
		
		INDEXES_DIR = new File(this.indexesPath);
		System.out.println(indexesPath);
		preparingIndex();
		readingDirectories();
		MergeIndex();
	}
	
	public void preparingIndex(){
		
		try {
			if (!Files.exists(this.mergedIndexesPath)) {
			      Files.createDirectories(this.mergedIndexesPath);
			    }
			final Directory MERGEDINDEX_DIR = FSDirectory.open(this.mergedIndexesPath);
			final EnglishAnalyzer analyzer = new EnglishAnalyzer();
			final IndexWriterConfig config = new IndexWriterConfig(new EnglishAnalyzer());
			
			//Configuring IndexWriterConfig
			config.setSimilarity(new BM25Similarity());
		    config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		    config.setRAMBufferSizeMB(2048);
		    config.setUseCompoundFile(false);
		    config.setMergeScheduler(new ConcurrentMergeScheduler());
		    writer = new IndexWriter(MERGEDINDEX_DIR, config);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void readingDirectories(){
		indexes = new Directory[INDEXES_DIR.list().length];
		String[] directories = INDEXES_DIR.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});
		System.out.println(Arrays.toString(directories));
		for (int i = 0; i < directories.length; i++) {
			System.out.println("Adding:" + INDEXES_DIR +"/"+ directories[i]);
			try {
				indexes[i] = FSDirectory.open(Paths.get(INDEXES_DIR +"/"+ directories[i]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void MergeIndex(){
		System.out.println("Merging indexses ...");
		try {
			writer.addIndexes(indexes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Optimizing index...");
		try {
			writer.forceMerge(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("done");
	}
	
}
