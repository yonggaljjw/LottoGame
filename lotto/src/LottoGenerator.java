import java.util.*;

public class LottoGenerator {

    public LottoGenerator() {}

    // 무작위 번호 6개 뽑기
    public int[] generateNumber() {
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

    // 보너스 번호 뽑기
    public int bonusNumber(int[] winningNumber) {
        int bonusNumber;
        boolean isDuplicate;

        do {
            bonusNumber = (int) (Math.random() * 45) + 1;
            isDuplicate = false;

            for (int winNum : winningNumber) {
                if (bonusNumber == winNum) {
                    isDuplicate = true;
                    break;
                }
            }
        } while (isDuplicate);

        return bonusNumber;
    }

    // 자동 추첨
    public int[][] autoGeneratelotto(int k) {
        int[][] lottoList = new int[k][6];
        for (int i = 0; i < k; i++) {
            lottoList[i] = this.generateNumber();
        }
        return lottoList;
    }

    // 수동추첨
    public int[][] inputGeneratelotto(int k) {

        Scanner input = new Scanner(System.in);

        int[][] lottoList = new int[k][6];

        for (int i = 0; i < k; i++) {
            System.out.println((i+1)+"번 로또를 추첨합니다.");
            boolean[] chkNumber = new boolean[46];
            int[] lotto = new int[6];
            int index = 0;
            String curNum = "";

            while (index < 6) {
                System.out.println((i+1)+"번 로또 : "+ curNum);
                int num;

                try {
                    num = input.nextInt();
                    if (num < 1 || num > 45) {
                        System.out.println("번호를 다시 입력하세요 (1~45)");
                    } else if (chkNumber[num]) {
                        System.out.println("중복된 번호입니다.");
                    } else {
                        lotto[index] = num;
                        chkNumber[num] = true;
                        index++;
                        curNum = curNum + " " + num;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("숫자만을 적어주세요");
                    input.next();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
            Arrays.sort(lotto);
            lottoList[i] = lotto;
        }

        return lottoList;
    }
}