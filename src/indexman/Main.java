package indexman;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IndexMerger indexmerger;
		if(args.length != 2){
			String INDEXES_DIR  = "/mnt/lucas/Indexes";
			String MERGED_DIR = "/mnt/lucas/Merged";
			System.out.println("You are running using a IDE, the paths of your indexes is:");
			System.out.println("'"+INDEXES_DIR+"'");
			System.out.println("The Merged indexes will be in the folder:");
			System.out.println("'"+MERGED_DIR+"'");
			indexmerger = new IndexMerger(INDEXES_DIR, MERGED_DIR);
			
		} else{
			String INDEXES_DIR  = args[0];
			String MERGED_DIR    = args[1];
			indexmerger = new IndexMerger(INDEXES_DIR, MERGED_DIR);
		}
	}

}
