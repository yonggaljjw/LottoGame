//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
import java.util.*;

public class Main {

    // 로또번호 생성
    public static int[] generateNumber() {
        boolean[] chkNumber = new boolean[46];
        int[] lotto = new int[6];

        int cnt = 0;
        while (cnt < 6) {
            int num = (int) (Math.random() * 45) + 1;

            if (!chkNumber[num]) {
                lotto[cnt] = num;
                chkNumber[num] = true;
                cnt += 1;
            }
        }
        Arrays.sort(lotto);

        return lotto;
    }


    // 보너스 번호
    public static int bonusNumber(int[] winningNumber) {

        int bonusNumber;
        boolean isDuplicate;

        do {
            // 1. 1부터 45 사이의 난수 생성
            bonusNumber = (int) (Math.random() * 45) + 1;
            isDuplicate = false;

            // 2. 당첨번호 6개와 중복되는지 검사
            for (int winNum : winningNumber) {
                if (bonusNumber == winNum) {
                    isDuplicate = true; // 중복됨
                    break;
                }
            }
        } while (isDuplicate);
        return bonusNumber;
    }

    // 자동추첨
    public static int[][] autoGeneratelotto(int k) {
        int[][] lottoList = new int[k][6];

        for (int i = 0; i < k; i++) {
            lottoList[i] = generateNumber();
        }

        return lottoList;
    }

    // 수동추첨
    public static int[][] inputGeneratelotto(Scanner input, int k) {
        int[][] lottoList = new int[k][6];


        for (int i = 0; i < k; i++) {
            boolean[] chkNumber = new boolean[46];
            int[] lotto = new int[6];
            int index = 0;
            while (index < 6) {
                int num = input.nextInt();

                if (num < 1 || num > 45) {
                    System.out.println("번호를 다시 입력하세요 (1~45)");
                } else if (chkNumber[num]) {
                    System.out.println("중복된 번호입니다.");
                } else {
                    lotto[index] = num;
                    chkNumber[num] = true;
                    index += 1;
                }
            };
            Arrays.sort(lotto);
            lottoList[i] = lotto;
        }
        return lottoList;
    }

    // 당첨비교
    public static String[][] getResult(int[] winningNumber, int[][] lottoNumber, int bonus) {

        String[][] results = new String[lottoNumber.length][3];

        for (int i = 0; i < lottoNumber.length; i++) {
            results[i][1] = "무";

            int[] currentlotto = lottoNumber[i];
            int matchCount = 0;
            boolean matchBonus = false;

            for (int idx = 0; idx < 6; idx++) {
                int winNum = winningNumber[idx];

                for (int id = 0; id < 6; id++) {
                    int lotNum = currentlotto[id];
                    if (winNum == lotNum) {
                        matchCount += 1;

                    } else if (bonus == lotNum) {
                        // 보너스 체크
                        matchBonus = true;
                        results[i][1] = "유";
                    }
                }
            }

            results[i][0] = matchCount + "개";

            switch (matchCount) {
                case 6:
                    results[i][2] = "1등";
                    break;
                case 5:
                    if (matchBonus) {
                        results[i][2] = "2등";
                    } else {
                        results[i][2] = "3등";
                    }
                    break;
                case 4:
                    results[i][2] = "4등";
                    break;
                case 3:
                    results[i][2] = "5등";
                    break;
                default:
                    results[i][2] = "꽝";
            }

        } return results;
    }

    public static void main(String[] args) {


        // 로또 구입하기
        Scanner input = new Scanner(System.in);
        System.out.println("금액을 입력해주세요");

        int lottoCount = input.nextInt() / 1000;

        if (lottoCount < 1) {
            System.out.println("1000원 미만입니다. 구입할 수 없습니다.");
            return;}

        System.out.println("모드 선택(1 : 수동, 2: 자동)");
        int modeStatus = input.nextInt();

        while (true) {
            if (modeStatus != 1 && modeStatus != 2) {
                System.out.println("다시 입력해주세요.\n 모드 선택(1 : 수동, 2: 자동");
                modeStatus = input.nextInt();
            } else {
                break;
            }
        }


        // 로또 뽑기
        int[][] lottoNum = new int[lottoCount][6];
        switch (modeStatus) {
            case 1:
                System.out.println("수동으로 번호를 입력합니다.");
                System.out.println("번호를 입력하세요 (1~45)");
                lottoNum = inputGeneratelotto(input, lottoCount);
                break;
            case 2:
                System.out.println(("자동으로 번호를 추첨합니다."));
                lottoNum = autoGeneratelotto(lottoCount);
                break;
        }

        System.out.println("============================");
        // 당첨번호랑 보너스 번호
        int[] winningNumber = generateNumber();
        System.out.println("이번 주 당첨번호");
        System.out.println(Arrays.toString(winningNumber));
        System.out.println();

        int bonusnumber = bonusNumber(winningNumber);
        System.out.println("이번 주 보너스번호");
        System.out.println(bonusnumber);
        System.out.println();

        // 구매한 로또 번호들
        System.out.println("이번주 구입한 로또 번호");
        System.out.println(Arrays.deepToString(lottoNum));
        System.out.println();

        // 등수 출력
        String[][] results = getResult(winningNumber, lottoNum, bonusnumber);

        for (int i = 0; i < results.length; i++) {

            System.out.println((i+1)+"번 로또 결과");
            System.out.println("맞은개수 : " + results[i][0]);
            System.out.println("보너스 당첨 여부 : " + results[i][1]);
            System.out.println("등수 : " + results[i][2]);
            System.out.println();
        }
        System.out.println("============================");
        }
}