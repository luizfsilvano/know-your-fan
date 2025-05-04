package com.furia.knowyourfan.infrastructure.config;

import com.furia.knowyourfan.domain.model.StreamerLiveStatus;
import com.furia.knowyourfan.domain.repository.StreamerLiveStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.furia.knowyourfan.domain.model.FuriaEvent;
import com.furia.knowyourfan.domain.repository.FuriaEventRepository;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final FuriaEventRepository furiaEventRepository;
    private final StreamerLiveStatusRepository repository;

    @Bean
    public CommandLineRunner seedStreamers() {
        return args -> {
            if (repository.count() == 0) {
                List<StreamerLiveStatus> streamers = List.of(
                        StreamerLiveStatus.builder().name("KSCERATO").link("https://twitch.tv/kscerato").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("yuurih").link("https://twitch.tv/yuurih").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("guerri").link("https://twitch.tv/guerri").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("gabssf").link("https://twitch.tv/gabssf").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("paulanobre").link("https://twitch.tv/paulanobre").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("IVDMALUCO").link("https://twitch.tv/ivdmaluco").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("kaah").link("https://twitch.tv/kaah").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("brino").link("https://twitch.tv/brino").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("mount").link("https://twitch.tv/mount").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("XAROLA_").link("https://twitch.tv/xarola_").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("mwzera").link("https://twitch.tv/mwzera").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("khalil_fps").link("https://twitch.tv/khalil_fps").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("otsukaxd").link("https://twitch.tv/otsukaxd").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("sofiaespanha").link("https://twitch.tv/sofiaespanha").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("jxmo").link("https://twitch.tv/jxmo").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("furiatv").link("https://twitch.tv/furiatv").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("fittipaldibrothers").link("https://twitch.tv/fittipaldibrothers").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("breeze_fps").link("https://twitch.tv/breeze_fps").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("immadness").link("https://twitch.tv/immadness").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("dgzinxl99").link("https://twitch.tv/dgzinxl99").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("pokizgames").link("https://twitch.tv/pokizgames").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("ikee").link("https://twitch.tv/ikee").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("chelok1ng").link("https://twitch.tv/chelok1ng").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("qckv").link("https://twitch.tv/qckv").updatedAt(LocalDateTime.now()).build(),
                        StreamerLiveStatus.builder().name("raf1nhafps").link("https://twitch.tv/raf1nhafps").updatedAt(LocalDateTime.now()).build()
                );


                repository.saveAll(streamers);
                System.out.println("✅ Streamers seeded!");
            }
        };
    }

    @Bean
    public CommandLineRunner seedEvents() {
        return args -> {
            if (furiaEventRepository.count() == 0) {
                List<FuriaEvent> events = List.of(
                        FuriaEvent.builder()
                                .title("IEM Dallas – FURIA vs MIBR")
                                .dateTime(LocalDateTime.now().plusDays(1).withHour(18).withMinute(0))
                                .location("Online")
                                .description("Primeira rodada do IEM Dallas. Acompanhe no canal da ESL.")
                                .build(),
                        FuriaEvent.builder()
                                .title("Twitch Rivals com FURIA Academy")
                                .dateTime(LocalDateTime.now().plusDays(4).withHour(20).withMinute(30))
                                .location("Twitch")
                                .description("Participação especial da FURIA Academy no Twitch Rivals.")
                                .build(),
                        FuriaEvent.builder()
                                .title("CS2 Qualifier Open Bracket")
                                .dateTime(LocalDateTime.now().plusDays(10).withHour(15).withMinute(0))
                                .location("Presencial - São Paulo")
                                .description("Etapa classificatória para torneio regional de CS2.")
                                .build()
                );

                furiaEventRepository.saveAll(events);
                System.out.println("✅ FURIA events seeded!");
            }
        };
    }
}
