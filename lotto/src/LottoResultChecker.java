public class LottoResultChecker {

    public LottoResultChecker() {}

    // 당첨비교
    public String[][] getResult(int[] winningNumber, int[][] lottoNumber, int bonus) {

        String[][] results = new String[lottoNumber.length][3];

        for (int i = 0; i < lottoNumber.length; i++) {
            results[i][1] = "무";

            int[] currentlotto = lottoNumber[i];
            int matchCount = 0;
            boolean matchBonus = false;

            for (int winNum : winningNumber) {
                for (int lotNum : currentlotto) {

                    if (winNum == lotNum) {
                        matchCount++;
                    } else if (bonus == lotNum) {
                        matchBonus = true;
                        results[i][1] = "유";
                    }
                }
            }

            results[i][0] = matchCount + "개";

            switch (matchCount) {
                case 6 -> results[i][2] = "1등";
                case 5 -> results[i][2] = matchBonus ? "2등" : "3등";
                case 4 -> results[i][2] = "4등";
                case 3 -> results[i][2] = "5등";
                default -> results[i][2] = "꽝";
            }
        }
        return results;
    }
}
