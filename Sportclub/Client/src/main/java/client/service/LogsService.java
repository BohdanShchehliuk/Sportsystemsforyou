package client.service;

import client.entity.Logs;
import client.exeptions.CustomException;

public interface LogsService {
    Logs chooseTariff(String phoneNumb, String tariffName) throws CustomException;

    Logs postponeTariff(String phoneNumb, String tariffName, int month) throws CustomException;

    Logs access(String phoneNumb) throws CustomException;
}
