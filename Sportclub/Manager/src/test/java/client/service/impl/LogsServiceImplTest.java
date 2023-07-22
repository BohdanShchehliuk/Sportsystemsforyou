package client.service.impl;

import client.ManagerApplication;
import client.entity.Logs;
import client.exeptions.UserNotFoundException;
import client.reposetory.LogsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = ManagerApplication.class)
class LogsServiceImplTest {
    @Autowired
    public LogsRepository logsRepository;
    @Autowired
    LogsServiceImpl logsService;

    @Test
    void getAllClientFromStartDataToFinishData() throws UserNotFoundException {
        Date startData, finishData;
        startData = new Date(2024, 01, 25);
        finishData = new Date(2026, 01, 25);

        List<Logs> respond = logsRepository.getAllClientFromStartDataToFinishData(startData, finishData);

        List<Logs> logsList = logsService
                .getAll()
                .stream()
                .filter(logs -> logs.getStartDay()
                        .after(startData)
                        && logs.getEndDay()
                        .before(finishData))
                .collect(Collectors.toList());
        assertEquals(logsList, respond);
    }
}