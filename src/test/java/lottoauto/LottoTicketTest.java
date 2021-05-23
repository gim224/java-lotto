package lottoauto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketTest {

    LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        List<LottoNumber> lottoNumbersList = Stream.<LottoNumber>builder()
                .add(new LottoNumber(1))
                .add(new LottoNumber(2))
                .add(new LottoNumber(3))
                .add(new LottoNumber(4))
                .add(new LottoNumber(5))
                .add(new LottoNumber(6))
                .build()
                .collect(Collectors.toList());
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumbersList);
        lottoTicket = new LottoTicket(lottoNumbers);
    }

    @DisplayName("티켓의 번호가 당첨번호와 몇개나 맞는지 테스트")
    @Test
    void calcHitCount() {
        LottoNumbers winningNumbers = new LottoNumbers(Stream.<LottoNumber>builder()
                .add(new LottoNumber(1)).add(new LottoNumber(2)).add(new LottoNumber(3))
                .add(new LottoNumber(4)).add(new LottoNumber(5)).add(new LottoNumber(6))
                .build()
                .collect(Collectors.toList()));
        lottoTicket.calcHitCount(winningNumbers);
        assertThat(lottoTicket.hitCount()).isEqualTo(new HitCount(6));

        winningNumbers = new LottoNumbers(Stream.<LottoNumber>builder()
                .add(new LottoNumber(1)).add(new LottoNumber(2)).add(new LottoNumber(3))
                .add(new LottoNumber(4)).add(new LottoNumber(5)).add(new LottoNumber(10))
                .build()
                .collect(Collectors.toList()));
        lottoTicket.calcHitCount(winningNumbers);
        assertThat(lottoTicket.hitCount()).isEqualTo(new HitCount(5));

        winningNumbers = new LottoNumbers(Stream.<LottoNumber>builder()
                .add(new LottoNumber(1)).add(new LottoNumber(2)).add(new LottoNumber(3))
                .add(new LottoNumber(4)).add(new LottoNumber(9)).add(new LottoNumber(10))
                .build()
                .collect(Collectors.toList()));
        lottoTicket.calcHitCount(winningNumbers);
        assertThat(lottoTicket.hitCount()).isEqualTo(new HitCount(4));

        winningNumbers = new LottoNumbers(Stream.<LottoNumber>builder()
                .add(new LottoNumber(21)).add(new LottoNumber(22)).add(new LottoNumber(23))
                .add(new LottoNumber(24)).add(new LottoNumber(25)).add(new LottoNumber(26))
                .build()
                .collect(Collectors.toList()));
        lottoTicket.calcHitCount(winningNumbers);
        assertThat(lottoTicket.hitCount()).isEqualTo(new HitCount(0));
    }
}
