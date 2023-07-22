package client.reposetory;


import client.entity.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogsRepository extends JpaRepository<Logs, Integer> {
    Logs findLogsByClientId(int client);

    //     l.2023-02-25 >2022-01-25  2326-01-25 < l.2223-08-25
    @Query("from Logs l where l.startDay  < :startData AND l.endDay > :finishData")
    List<Logs> getAllClientFromStartDataToFinishData(@Param("startData") Date startData,
                                                     @Param("finishData") Date finishData);


}
