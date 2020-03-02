package Lotto.views;

import Lotto.domain.*;

import java.util.Objects;

public class OutputView {
    private static final String PURCHASED_LOTTO_MESSAGE = "수동으로 %d장, 자동으로 %d장을 구매했습니다.\n";
    private static final String STATISTICS_FORMAT = "%d개 일치 %s%d원 - %d개%s";
    private static final String SECOND_RANK_ADDITIONAL_MESSAGE = "보너스 볼 일치 ";
    private static final String EMPTY_STRING = "";
    private static final String NEW_LINE = "\n";
    private static final String TOTAL_PROFIT_MESSAGE = "상금 총액은 %d원입니다.\n";
    private static final String EARNING_RATE_MESSAGE = "총 수익률은 %.3f %%입니다.\n";

    public static void showPurchasedLottoCount(LottoCount manualLottoCount, LottoCount autoLottoCount) {
        System.out.printf(PURCHASED_LOTTO_MESSAGE, manualLottoCount.getLottoCount(), autoLottoCount.getLottoCount());
    }

    public static void showAllLottos(Lottos manualLottos, Lottos autoLottos) {
        if (manualLottos != null) {
            System.out.println(manualLottos.getLottosInOneLine());
        }
        if (autoLottos != null) {
            System.out.println(autoLottos.getLottosInOneLine());
        }
        System.out.println();
    }

    public static void showStatistics(Ranks ranks) {
        System.out.println("<<당첨 결과>>");
        ranks.getRanks().keySet()
                .stream()
                .filter(Objects::nonNull)
                .sorted()
                .forEach(rank -> System.out.printf(STATISTICS_FORMAT, rank.getHitCount(), printBonus(rank),
                        rank.getRankReward(), ranks.getRanks().get(rank), NEW_LINE));
    }

    private static String printBonus(Rank rank) {
        if (rank == Rank.SECOND) {
            return SECOND_RANK_ADDITIONAL_MESSAGE;
        }
        return EMPTY_STRING;
    }

    public static void showEarningRate(Ranks ranks, PurchaseAmount purchaseAmount) {
        EarningRate earningRate = new EarningRate(ranks, purchaseAmount);
        System.out.printf(TOTAL_PROFIT_MESSAGE, earningRate.getProfitAmount());
        System.out.printf(EARNING_RATE_MESSAGE, earningRate.getEarningRate());
    }
}
