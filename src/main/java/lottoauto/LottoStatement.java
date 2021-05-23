package lottoauto;

import java.util.*;

public class LottoStatement {
    private final Map<LottoRule, Integer> hitCountMap = new LinkedHashMap<>();

    public LottoStatement() {
        hitCountMap.put(LottoRule.THREE_HIT, 0);
        hitCountMap.put(LottoRule.FOUR_HIT, 0);
        hitCountMap.put(LottoRule.FIVE_HIT, 0);
        hitCountMap.put(LottoRule.SIX_HIT, 0);
    }

    public void judge(HitCount hitCount) {
        Set<LottoRule> keySet = hitCountMap.keySet();
        for (LottoRule lottoRule : keySet) {
            isEqualsHitCount(hitCount, lottoRule);
        }
    }

    private void isEqualsHitCount(HitCount hitCount, LottoRule lottoRule) {
        if (lottoRule.isEqualsHitCount(hitCount)) {
            int count = hitCountMap.get(lottoRule);
            hitCountMap.put(lottoRule, count + 1);
        }
    }

    public Integer accumulatedHitCount(LottoRule lottoRule) {
        return hitCountMap.get(lottoRule);
    }



    @Override
    public String toString() {
        String result = "";

        Set<LottoRule> keySet = hitCountMap.keySet();
        for (LottoRule lottoRule : keySet) {
            result += lottoRule.toString() + hitCountMap.get(lottoRule) +"개\n";
        }

        return result;
    }

    public Money calcProceeds() {
        int money = 0;

        Set<LottoRule> keySet = hitCountMap.keySet();
        for (LottoRule lottoRule : keySet) {
            money += lottoRule.calcProceed(hitCountMap.get(lottoRule));
        }

        return new Money(money);
    }
}
