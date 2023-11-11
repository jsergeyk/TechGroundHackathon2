package com.hackathon.util;

import com.hackathon.entity.BatchInit;
import com.hackathon.entity.RouteHelpInfrastructure;
import com.hackathon.persistence.repositories.BatchInitRepository;
import com.hackathon.service.InfrastructureService;
import com.hackathon.service.RouteHelpInfrastructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MyCommandLineRunner implements CommandLineRunner {
    @Value("${app.initializer.version}")
    private Long initializerVersion;
    private final BatchInitRepository batchInitRepository;
    private final RouteHelpInfrastructureService routeHelpInfrastructureService;
    private final InfrastructureService infrastructureService;

    @Override
    public void run(String... args) {
        Long lastVersion = batchInitRepository.findLastVersion();
        if (Objects.equals(lastVersion, initializerVersion)) {
            return;
        }
        if (lastVersion == null) {
            lastVersion = 0L;
        }
        for (long i = ++lastVersion; i <= initializerVersion; i++) {
//            saveInfrastructuresFromCsv();
            saveRouteHelpInfrastructuresFromCsv("src/main/resources/routeHelpInfrastructures.csv");
        }
        batchInitRepository.save(new BatchInit(initializerVersion));
    }

//    public void saveInfrastructuresFromCsv(MultipartFile file) {
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//            List<YourEntity> entities = reader.lines()
//                    .map(this::mapToEntity)
//                    .collect(Collectors.toList());
//
//            .saveAll(entities);
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Обробляйте помилки, якщо необхідно
//        }
//    }

    public void saveRouteHelpInfrastructuresFromCsv(String fileName) {
        File file = new File(fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<RouteHelpInfrastructure> entities = reader.lines()
                    .map(this::mapToRouteHelpInfrastructure)
                    .collect(Collectors.toList());

            routeHelpInfrastructureService.saveAll(entities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    private Infrastructure mapToInfrastructure(String line) {
//        // Логіка для мапінгу рядка з CSV на об'єкт вашої сутності
//        // Наприклад, використовуйте роздільник (зазвичай кому) для розділення полів
//        // Повертайте створений об'єкт
//    }

    private RouteHelpInfrastructure mapToRouteHelpInfrastructure(String line) {
        String[] routeHelpInfrastructureFields = line.split("\\s+\\|");
        return RouteHelpInfrastructure.builder()
                .name(routeHelpInfrastructureFields[0])
                .description(routeHelpInfrastructureFields[1])
                .latitude(Double.parseDouble(routeHelpInfrastructureFields[2]))
                .longitude(Double.parseDouble(routeHelpInfrastructureFields[3]))
                .build();
    }
}