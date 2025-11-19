import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[][] lottoNum;
        int lottoCount;

        LottoGenerator lottGen = new LottoGenerator();
        LottoResultChecker lottChk = new LottoResultChecker();
        Scanner input = new Scanner(System.in);


        // 1. 로또 구입
        System.out.println("금액을 입력해주세요");

        while (true) {

            try {
                lottoCount = input.nextInt() / 1000;
                break;

            } catch (InputMismatchException e) {
                System.out.println("숫자로 입력해주세요.");
                input.next();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        if (lottoCount < 1) {
            System.out.println("1000원 미만입니다. 구입할 수 없습니다.");
            return;
        }


        // 2. 번호 추첨
        System.out.println("모드 선택(1 : 수동, 2: 자동)");
        int modeStatus;

        // 2-1. 모드 선택
        while (true) {

            try {
                modeStatus = input.nextInt();

                if (modeStatus != 1 && modeStatus != 2) {
                    System.out.println("다시 입력해주세요.\n 모드 선택(1 : 수동, 2: 자동)");
                } else { break;}

            } catch (InputMismatchException e) {
                System.out.println("다시 입력해주세요.\n 모드 선택(1 : 수동, 2: 자동)");
                input.next();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // 2-2. 모드 실행
        switch (modeStatus) {
            case 1 -> {
                System.out.println("수동으로 번호를 입력합니다.");
                System.out.println("번호를 입력하세요 (1~45)");
                lottoNum = lottGen.inputGeneratelotto(lottoCount);
            }
            case 2 -> {
                System.out.println("자동으로 번호를 추첨합니다.");

                lottoNum = lottGen.autoGeneratelotto(lottoCount);
            }
            default -> lottoNum = new int[lottoCount][6];
        }


        // 3. 결과 확인
        System.out.println("============================");

        int[] winningNumber = lottGen.generateNumber();
        System.out.println("이번 주 당첨번호");
        System.out.println(Arrays.toString(winningNumber));
        System.out.println();

        int bonusnumber = lottGen.bonusNumber(winningNumber);
        System.out.println("이번 주 보너스번호");
        System.out.println(bonusnumber);
        System.out.println();

        System.out.println("이번주 구입한 로또 번호");
        System.out.println(Arrays.deepToString(lottoNum));
        System.out.println();

        String[][] results = lottChk.getResult(winningNumber, lottoNum, bonusnumber);

        for (int i = 0; i < results.length; i++) {
            System.out.println((i + 1) + "번 로또 결과");
            System.out.println("맞은개수 : " + results[i][0]);
            System.out.println("보너스 당첨 여부 : " + results[i][1]);
            System.out.println("등수 : " + results[i][2]);
            System.out.println();
        }

        System.out.println("============================");

        input.close();
    }
}
