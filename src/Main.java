import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Marubatu game = new Marubatu();

		boolean player = true;
		game.getGameDisk();
		Scanner sc = new Scanner(System.in);
		while(game.getLoop() == true) {
			int num = 0;
			if(player == false) {
				num = game.cpuNumber();
			}else {
				System.out.print("[1~9]のどれかを入力してください : ");//置き場所入力
				num = sc.nextInt();
			}
			game.placePiece(num, player);//おけるか確認，おけるならそのまま置く．ダメなら再度入力処理あり
			game.checkGame();
			player = game.changePlayer(player);//plyer変更 trueなら人 / falseならCPU
		}
		sc.close();
		System.out.println("ゲームを終了します．");

	}

}
