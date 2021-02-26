import java.util.Random;
import java.util.Scanner;

public class Marubatu {
	boolean loop = true;
	Scanner sc = new Scanner(System.in);
	private char[][] gameDisk = {{' ' , '|' , ' ' , '|' , ' '},
			 {'-' , '+' , '-' , '+' , '-'},
			 {' ' , '|' , ' ' , '|' , ' '},
			 {'-' , '+' , '-' , '+' , '-'},
			 {' ' , '|' , ' ' , '|' , ' '}};
	//盤面確認
	void getGameDisk(){//表示のみ
		for(char[] n : gameDisk) {
			for(char p : n) {
				System.out.print(p);
			}
			System.out.println();
			}
		System.out.println();
	}

	//値から対応した番号を取得 PlacePieceから
	int getNumber(int num){
		int result = -1;
		switch(num) {
		case 1:
		case 4:
		case 7:
			result = 0;
			break;
		case 2:
		case 5:
		case 8:
			result = 2;
			break;
		case 3:
		case 6:
		case 9:
			result = 4;
			break;
		default:
				System.out.println("存在しない盤面です．");
		}
		return result;
	}

	//置き場所の確認
	void placePiece(int num,boolean player){//true -> 先行，○ / false ->  後行，X
		int column = getNumber(num);
		if(player == true) {
			System.out.println("Player turn");
		}else {
			System.out.println("CPU turn");
		}

		if(num <= 3) {
			if(gameDisk[0][column] == ' ') {
				setPiece(0,column,player);
			}else {
				printError(player);
			}
		}else if(num <= 6) {
			if(gameDisk[2][column] == ' ') {
			setPiece(2,column,player);
			}else {
				printError(player);
			}
		}else {
			if(gameDisk[4][column] == ' ') {
				setPiece(4,column,player);
			}else {
				printError(player);
			}
		}
		int count = 25;
		for(int i = 0; i < 5;i++) {
			for(int j = 0;j < 5;j++) {
				if(gameDisk[i][j] != ' ') {
					count -= 1;
				}
			}
		}
		if(count == 0) {
			setLoop(false);
			printWinner('a');
		}
	}

	//実際に設置 / PlacePieceから
	void setPiece(int row , int column ,boolean player) {
		if(player == true) {
			gameDisk[row][column] = 'O';
			getGameDisk();
		}
		else {
			gameDisk[row][column] = 'X';
			getGameDisk();
		}
	}

	//置けない場合に表示/PlacePiece
	void printError(boolean player) {
		System.out.println("すでに設置済みの場所です");
		if(player == true) {//入力が人間の時
			System.out.print("再度入力してください : ");
			int num = sc.nextInt();
			placePiece(num,player);//再度値を入力させておけるか確認する．
		}else {//CPUが再入力する時
			int num = cpuNumber();
			placePiece(num,player);
		}
	}

	//勝利条件確認
	boolean checkGame() {

		for(int i = 0; i < 5;i+=2) {//縦横
			checkWinner(gameDisk[i][0],gameDisk[i][2],gameDisk[i][4]);
			checkWinner(gameDisk[0][i],gameDisk[2][i],gameDisk[4][i]);
		}
		//斜め
		checkWinner(gameDisk[0][0],gameDisk[2][2],gameDisk[4][4]);
		checkWinner(gameDisk[0][4],gameDisk[2][2],gameDisk[4][0]);

		return getLoop();
	}



	void checkWinner(char first, char second , char third) {
		if(first == 'O' && second == 'O' && third == 'O') {
			printWinner('O');
		}else if(first == 'X' && second == 'X' && third == 'X') {
			printWinner('X');
		}
	}

	void printWinner(char winner){
		boolean result = false;
		if(winner == 'O') {
			System.out.println("おめでとうございます．あなたの勝利です．");
		}else if(winner == 'X'){
			System.out.println("コンピュータの勝利です．");
		}else {
			System.out.println("引き分けです．");
		}
		setLoop(result);
	}
	void setLoop(boolean loop) {
		this.loop = loop;
	}
	boolean getLoop() {
		return this.loop;
	}

	boolean changePlayer(boolean player) {
		boolean nextPlayer = !player;
		return nextPlayer;
	}

	int cpuNumber() {
		int num = 0;
		Random random = new Random();
		num = random.nextInt(9) + 1;
		return num;
	}

}
