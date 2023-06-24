package client.service;


import client.entity.Logs;
import client.exeptions.CustomException;
import client.exeptions.UserNotFoundException;
import org.springframework.data.repository.query.Param;


import java.sql.Date;
import java.util.List;

public interface LogsService {
    List<Logs> getAll() throws UserNotFoundException;

    Logs chooseTariff(String phoneNumb, String tariffName) throws CustomException;

    Logs access(String phoneNumb) throws CustomException;

    List<Logs> getAllClientFromStartDataToFinishData(Date startData, Date finishData) throws CustomException;
}
