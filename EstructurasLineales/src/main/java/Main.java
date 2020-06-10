
public class Main {

	public static void main(String[] args) {
		Integer list[] = {2,3,3,4,5,5,6,7,8,60,88,90,100,110};
		
		IndexService<Integer> serv = new IndexServiceWithDuplicates<Integer>(list);
		
		
		serv.Range(2, 110,false, false);
		

	}

}
