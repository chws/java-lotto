package lotto;

import static lotto.controller.LottoManager.*;

import java.util.List;

import lotto.controller.RankCalculator;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningNumber;
import lotto.utils.NumberParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		Money budget = new Money(InputView.inputAsMoney());
		int purchasedLottoAmount = calculateLottoAmount(budget);
		OutputView.showPurchasedLottoCount(purchasedLottoAmount);

		List<Lotto> purchasedLotto = generateLottoByAmount(purchasedLottoAmount);
		OutputView.showPurchasedLottoNumbers(purchasedLotto);

		List<Rank> resultRanks = RankCalculator.calculateMultiple(purchasedLotto, produceWinningNumber());
		OutputView.showResult(budget, resultRanks);
	}

	private static WinningNumber produceWinningNumber() {
		List<Integer> inputWinningNumbers = NumberParser.winningNumberParse(InputView.inputAsWinningNumbers());
		Lotto winningNumberLotto = new Lotto(inputWinningNumbers);
		BonusNumber bonusNumber = new BonusNumber(NumberParser.parseNumber(InputView.inputAsBonusNumber()));

		return new WinningNumber(winningNumberLotto, bonusNumber);
	}
}
