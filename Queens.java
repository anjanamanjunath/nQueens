 
import java.util.Arrays;

public class Queens {
	
	public static void main (String[] args) {
		int B[][];
		int n; 
		int cntr = 0; 

		if (args.length == 0) {
			System.out.println("Usage: Queens [-v] number");
			System.out.println("Option: -v  verbose output, print all solutions");
		}
		else {

			for (int i = 0; i < args.length; i++) {
				for (char ch : args[i].toCharArray()) {
					if (Character.isDigit(ch)) {
						cntr++; 
					}
				}
			}

			if (cntr == 0) {
				System.out.println("Usage: Queens [-v] number");
				System.out.println("Option: -v  verbose output, print all solutions");
			}
			else {
				String command = ""; 
				for (int i = 0; i < args.length; i++) {
					command += args[i];
				}

				if (command.length() > 1) {
					if (command.charAt(1) == 118){
						n = Integer.parseInt(command.substring(2))+1;


						if (n-1 == 2 || n-1 == 3) {
							System.out.println((n-1)+"-Queens has 0 solutions");
						}
						else {


							B = new int[n][n]; 
							for (int x = 1; x < B.length; x++) {
								for (int y = 1; y < B[x].length; y++) {
									B[x][y] = 0; 	
								}
							}

							int solutions = findSolutions(B, 1, "verbose"); 
							if (solutions == 1) {
								System.out.println((n-1)+"-Queens has "+solutions+" solution");
							}
							else if (solutions > 1) {
								System.out.println((n-1)+"-Queens has "+solutions+" solutions");
							} 
						}

					}
					else {
						n = Integer.parseInt(command)+1;

						if (n-1 == 2 || n-1 == 3) {
							System.out.println((n-1)+"-Queens has 0 solutions");
						}

						else {
							B = new int[n][n]; 
							for (int x = 1; x < B.length; x++) {
								for (int y = 1; y < B[x].length; y++) {
									B[x][y] = 0; 			
								}
							}
							int solutions = findSolutions(B, 1, ""); 
							if (solutions == 1) {
								System.out.println((n-1)+"-Queens has "+solutions+" solution");
							}
							else if (solutions > 1) {
								System.out.println((n-1)+"-Queens has "+solutions+" solutions");
							}
						}
					}
				}
				else {


					n = Integer.parseInt(command)+1;

					if (n-1 == 2 || n-1 == 3) {
						System.out.println((n-1)+"-Queens has 0 solutions");
					}

					else {
						B= new int[n][n]; 
						for (int x = 1; x < B.length; x++) {
							for (int y = 1; y < B[x].length; y++) {
								B[x][y] = 0; 			
							}
						}
						int solutions = findSolutions(B, 1, ""); 
						if (solutions == 1) {
							System.out.println((n-1)+"-Queens has "+solutions+" solution");
						}
						else if (solutions > 1) {
							System.out.println((n-1)+"-Queens has "+solutions+" solutions");
						}
					}
				}
			}
		}
	}

	static void placeQueen(int[][] B, int i, int j) {
		B[i][j] = 1;
		B[i][0] = j; 

		for (int x = 1; x < B.length; x++) {
			if (x != j) {
				B[i][x]--; 
			}
		}

		for (int y = 1; y < B[i].length; y++) {
			if (y != i) {
				B[y][j]--; 
			}
		}


		for (int x = 1; x < B.length; x++) {
			for (int y = 1; y < B[x].length; y++) {
				int run = i - x; 
				int rise = j - y; 


				if (run != 0) {	
					if (run == rise || (-1)*run == rise || (-1)*rise == run) {
						B[x][y]--; 
					}
				}
			}
		}


	}

	static void removeQueen(int[][] B, int i, int j) {
		B[i][j] = 0; 
		B[i][0] = 0;

		for (int x = 1; x < B.length; x++) {
			if (x != j) {
				B[i][x]++; 
			}
		}

		for (int y = 1; y < B[i].length; y++) {
			if (y != i) {
				B[y][j]++; 
			}
		}

		for (int x = 1; x < B.length; x++) {
			for (int y =1; y < B[x].length; y++) {
				int run = i - x; 
				int rise = j - y; 

				if (run != 0) {	
					if (run == rise || (-1)*run == rise || (-1)*rise == run) {
						B[x][y]++; 
					}
				}
			}
		}	

	}

	static void printBoard(int[][] B) {
		String solution = "("; 

		for (int j = 1; j < B.length; j++) {
			solution += B[j][0]+", ";
		}

		if (solution.length() > 2) {
			solution = solution.substring(0, solution.length()-2);
		}
		solution += ")";

		System.out.println(solution);

	}

	static int findSolutions(int[][] B, int i, String mode) {
		int sum = 0; 
		if (i > B.length-1) {
			if (mode == "verbose") {

				printBoard(B);
			}
			return 1; 
		}

		else {
			for (int j = 1; j < B[i].length; j++) {
				if (B[i][j] == 0) {
					placeQueen(B, i, j); 
					sum += findSolutions(B, i+1, mode);
					removeQueen(B, i, j);
				}
			}
			return sum;
		}


	}

}

