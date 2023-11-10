package com.priestdev.chumag.service;

import com.priestdev.chumag.entity.*;
import com.priestdev.chumag.entity.Pojo.*;
import com.priestdev.chumag.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReportingService {

    private final FirstTimerRepo firstTimerRepo;
    private final SecondTimerRepo secondTimerRepo;
    private final ZonalLeaderRepo zonalLeaderRepo;
    private final CellLeaderRepo cellLeaderRepo;
    private final ZonalLeaderReportRepo zonalLeaderReportRepo;
    private final NewConvertRepo newConvertRepo;
    private final MembersRepository membersRepo;

    private static final int weeklyTarget = 4;


    public ResponseEntity<StandardResponse> generateWeeklyReport(){
        try {
            LocalDate date = LocalDate.now();
            int year = LocalDate.now().getYear();
            int monthInt = date.getDayOfMonth();

            List<FirstTimer> firstTimers = firstTimerRepo.findByVisitMonth(monthInt);
            List<SecondTimer> secondTimers = secondTimerRepo.findByVisitMonth(monthInt);
            List<ZonalLeader> zonalLeaders = zonalLeaderRepo.findAll();
            List<CellLeader> cellLeaders = cellLeaderRepo.findAll();

            List<CellReporting> cellReports = new ArrayList<>();
            List<CellWeeklyReport> weeklyFandS = new ArrayList<>();

            List<LocalDate> sundays = findSundays(date.getYear(), date.getMonthValue());


            for(LocalDate localDate: sundays){
                for(CellLeader leaders: cellLeaders) {
                    CellWeeklyReport fandS = new CellWeeklyReport();
                    fandS.setSunday(localDate);
                    for (FirstTimer timer : firstTimers) {
                        if (leaders.getId().compareTo(timer.getCellId()) == 0 || localDate.isEqual(timer.getFirstVisitDate())) {
                            fandS.setFirstTimers(fandS.getFirstTimers() + 1);
                        }

                    }
                    for (SecondTimer timer : secondTimers) {
                        if (timer.getCellId().compareTo(leaders.getId()) == 0 || localDate.isEqual(timer.getVisitationDate())) {
                            fandS.setFirstTimers(fandS.getFirstTimers() + 1);
                        }
                    }
                    fandS.setTotalFirstAndSecondTimers(fandS.getFirstTimers() + fandS.getSecondTimers());
                    fandS.setCellId(leaders.getId());
                    weeklyFandS.add(fandS);
                }
            }

            for(CellLeader leader: cellLeaders) {
                CellReporting cellReporting = new CellReporting();
                int monthlyTarget = findSundays(LocalDate.now().getYear(), LocalDate.now().getMonthValue()).size();
                monthlyTarget = monthlyTarget * 4;
                cellReporting.setMonthlyTarget(monthlyTarget);
                cellReporting.setWeeklyTarget(weeklyTarget);
                cellReporting.setCellLeader(leader);
                for (FirstTimer timer : firstTimers) {
                    if (timer.getCellAddress().equalsIgnoreCase(leader.getCellAddress())
                            && Objects.equals(timer.getCellId(), leader.getId())){
                        cellReporting.setMonthlyTotalFirstTimers(cellReporting.getMonthlyTotalFirstTimers() + 1);
                    }

                }
                for (SecondTimer second : secondTimers) {
                    if (second.getCellName().equalsIgnoreCase(leader.getFirstName())
                            && Objects.equals(second.getCellId(), leader.getId())){
                        cellReporting.setMonthlyTotalSecondTimers(cellReporting.getMonthlyTotalSecondTimers() + 1);
                    }
                }
                int monthly = 0;
                for (CellWeeklyReport fandS: weeklyFandS){
                    if(fandS.getCellId().compareTo(leader.getId()) == 0){
                        cellReporting.getWeeklyFandS().add(fandS);
                        cellReporting.setWeeklyTotalFirstAndSecondTimers(cellReporting.getWeeklyTotalFirstAndSecondTimers() + fandS.getTotalFirstAndSecondTimers());
                        cellReporting.setWeeklyTotalFirstTimers(cellReporting.getWeeklyTotalFirstTimers() + fandS.getFirstTimers());
                        cellReporting.setWeeklyTotalSecondTimers(cellReporting.getWeeklyTotalSecondTimers() + fandS.getSecondTimers());
                        monthly = monthly + fandS.getTotalFirstAndSecondTimers();
                    }
                }
                cellReporting.setMonthlyTotalFirstAndSecondTimer(monthly);
                cellReports.add(cellReporting);
            }

            List<Reporting> sundayReports = new ArrayList<>();
            int counter = findSundays(year, monthInt).size();
            int sundaySize = 0;
            do {
                Reporting report = new Reporting();
                report.setSunday(sundays.get(sundaySize));
                for (ZonalLeader leader : zonalLeaders) {
                    ZoneReporting reporting = new ZoneReporting();
                    reporting.setZonalLeader(leader);
                    for (CellReporting cellReporting : cellReports) {
                        if (cellReporting.getCellLeader().getZoneId().compareTo(leader.getId()) == 0) {
                            reporting.getCellLeaders().add(cellReporting);
                            reporting.setNumberOfCells(reporting.getNumberOfCells() + 1);
                            reporting.setTotalWeeklyFirstTimer(reporting.getTotalWeeklyFirstTimer() + cellReporting.getWeeklyTotalFirstTimers());
                            reporting.setTotalWeeklySecondTimer(reporting.getTotalWeeklySecondTimer() + cellReporting.getWeeklyTotalSecondTimers());
                            reporting.setTotalMonthlyFirstTimer(reporting.getTotalMonthlyFirstTimer() + cellReporting.getMonthlyTotalFirstTimers());
                            reporting.setTotalMonthlySecondTimer(reporting.getTotalMonthlySecondTimer() + cellReporting.getMonthlyTotalSecondTimers());
                            reporting.setTotalWeeklyFirstAndSecondTimer(reporting.getTotalWeeklyFirstAndSecondTimer() + cellReporting.getWeeklyTotalFirstAndSecondTimers());
                            reporting.setTotalMonthlyFirstAndSecondTimer(reporting.getTotalMonthlyFirstAndSecondTimer() + cellReporting.getMonthlyTotalFirstAndSecondTimer());
                        }
                    }
                    report.getZoneReports().add(reporting);
                }
                sundayReports.add(report);
                sundaySize = sundaySize + 1;
            } while (sundaySize < counter);

            return StandardResponse.sendHttpResponse(true, "Successful", sundayReports);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> generateWeeklyReportByMonth(String month, int year){
        try {

            int monthInt = Month.valueOf(month.toUpperCase()).getValue();

            List<FirstTimer> firstTimers = firstTimerRepo.findByVisitMonth(monthInt);
            List<SecondTimer> secondTimers = secondTimerRepo.findByVisitMonth(monthInt);
            List<ZonalLeader> zonalLeaders = zonalLeaderRepo.findAll();
            List<CellLeader> cellLeaders = cellLeaderRepo.findAll();

            List<CellReporting> cellReports = new ArrayList<>();
            List<CellWeeklyReport> weeklyFandS = new ArrayList<>();

            List<LocalDate> sundays = findSundays(year, monthInt);


            for(LocalDate localDate: sundays){
                for(CellLeader leaders: cellLeaders) {
                    CellWeeklyReport fandS = new CellWeeklyReport();
                    fandS.setSunday(localDate);
                    for (FirstTimer timer : firstTimers) {
                        if (leaders.getId().compareTo(timer.getCellId()) == 0 || localDate.isEqual(timer.getFirstVisitDate())) {
                            fandS.setFirstTimers(fandS.getFirstTimers() + 1);
                        }

                    }
                    for (SecondTimer timer : secondTimers) {
                        if (timer.getCellId().compareTo(leaders.getId()) == 0 || localDate.isEqual(timer.getVisitationDate())) {
                            fandS.setFirstTimers(fandS.getFirstTimers() + 1);
                        }
                    }
                    fandS.setTotalFirstAndSecondTimers(fandS.getFirstTimers() + fandS.getSecondTimers());
                    fandS.setCellId(leaders.getId());
                    weeklyFandS.add(fandS);
                }
            }

            for(CellLeader leader: cellLeaders) {
                CellReporting cellReporting = new CellReporting();
                int monthlyTarget = findSundays(LocalDate.now().getYear(), LocalDate.now().getMonthValue()).size();
                monthlyTarget = monthlyTarget * 4;
                cellReporting.setMonthlyTarget(monthlyTarget);
                cellReporting.setWeeklyTarget(weeklyTarget);
                cellReporting.setCellLeader(leader);
                for (FirstTimer timer : firstTimers) {
                    if (timer.getCellAddress().equalsIgnoreCase(leader.getCellAddress())
                            && Objects.equals(timer.getCellId(), leader.getId())){
                        cellReporting.setMonthlyTotalFirstTimers(cellReporting.getMonthlyTotalFirstTimers() + 1);
                    }

                }
                for (SecondTimer second : secondTimers) {
                    if (second.getCellName().equalsIgnoreCase(leader.getFirstName())
                            && Objects.equals(second.getCellId(), leader.getId())){
                        cellReporting.setMonthlyTotalSecondTimers(cellReporting.getMonthlyTotalSecondTimers() + 1);
                    }
                }
                //I AM HOPING!
                int monthly = 0;
                for (CellWeeklyReport fandS: weeklyFandS){
                    if(fandS.getCellId().compareTo(leader.getId()) == 0){
                        cellReporting.getWeeklyFandS().add(fandS);
                        cellReporting.setWeeklyTotalFirstAndSecondTimers(cellReporting.getWeeklyTotalFirstAndSecondTimers() + fandS.getTotalFirstAndSecondTimers());
                        cellReporting.setWeeklyTotalFirstTimers(cellReporting.getWeeklyTotalFirstTimers() + fandS.getFirstTimers());
                        cellReporting.setWeeklyTotalSecondTimers(cellReporting.getWeeklyTotalSecondTimers() + fandS.getSecondTimers());
                        monthly = monthly + fandS.getTotalFirstAndSecondTimers();
                    }
                }
                cellReporting.setMonthlyTotalFirstAndSecondTimer(monthly);
                cellReports.add(cellReporting);
            }

            List<Reporting> sundayReports = new ArrayList<>();
            int counter = findSundays(year, monthInt).size();
            int sundaySize = 0;
            do {
                Reporting report = new Reporting();
                report.setSunday(sundays.get(sundaySize));
                for (ZonalLeader leader : zonalLeaders) {
                    ZoneReporting reporting = new ZoneReporting();
                    reporting.setZonalLeader(leader);
                    for (CellReporting cellReporting : cellReports) {
                        if (cellReporting.getCellLeader().getZoneId().compareTo(leader.getId()) == 0) {
                            reporting.getCellLeaders().add(cellReporting);
                            reporting.setNumberOfCells(reporting.getNumberOfCells() + 1);
                            reporting.setTotalWeeklyFirstTimer(reporting.getTotalWeeklyFirstTimer() + cellReporting.getWeeklyTotalFirstTimers());
                            reporting.setTotalWeeklySecondTimer(reporting.getTotalWeeklySecondTimer() + cellReporting.getWeeklyTotalSecondTimers());
                            reporting.setTotalMonthlyFirstTimer(reporting.getTotalMonthlyFirstTimer() + cellReporting.getMonthlyTotalFirstTimers());
                            reporting.setTotalMonthlySecondTimer(reporting.getTotalMonthlySecondTimer() + cellReporting.getMonthlyTotalSecondTimers());
                            reporting.setTotalWeeklyFirstAndSecondTimer(reporting.getTotalWeeklyFirstAndSecondTimer() + cellReporting.getWeeklyTotalFirstAndSecondTimers());
                            reporting.setTotalMonthlyFirstAndSecondTimer(reporting.getTotalMonthlyFirstAndSecondTimer() + cellReporting.getMonthlyTotalFirstAndSecondTimer());
                        }
                    }
                    report.getZoneReports().add(reporting);
                }
                sundayReports.add(report);
                sundaySize = sundaySize + 1;
            } while (sundaySize < counter);

            return StandardResponse.sendHttpResponse(true, "Successful", sundayReports);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> generateMonthlyReportByYear(int year){
        try {

            List<FirstTimer> firstTimers = firstTimerRepo.findAll();
            List<SecondTimer> secondTimers = secondTimerRepo.findAll();
            List<ZonalLeader> zonalLeaders = zonalLeaderRepo.findAll();
            List<CellLeader> cellLeaders = cellLeaderRepo.findAll();

            List<CellReporting> cellReports = new ArrayList<>();
            List<CellMonthlyReport> monthlyFandS = new ArrayList<>();



            for(int month = 1; month<13; month++){
                for(CellLeader leaders: cellLeaders) {
                    CellMonthlyReport fandS = new CellMonthlyReport();
                    fandS.setMonthInt(month);
                    for (FirstTimer timer : firstTimers) {
                        if (leaders.getId().compareTo(timer.getCellId()) == 0 || month == timer.getVisitMonth()) {
                            fandS.setFirstTimers(fandS.getFirstTimers() + 1);
                        }

                    }
                    for (SecondTimer timer : secondTimers) {
                        if (timer.getCellId().compareTo(leaders.getId()) == 0 || month == timer.getVisitMonth()) {
                            fandS.setFirstTimers(fandS.getFirstTimers() + 1);
                        }
                    }
                    fandS.setTotalFirstAndSecondTimers(fandS.getFirstTimers() + fandS.getSecondTimers());
                    fandS.setCellId(leaders.getId());
                    monthlyFandS.add(fandS);
                }
            }

            for(CellLeader leader: cellLeaders) {
                CellReporting cellReporting = new CellReporting();
                int yearlyTarget = 0;
                int monthlyTarget = findSundays(LocalDate.now().getYear(), LocalDate.now().getMonthValue()).size();
                yearlyTarget = monthlyTarget * 4 * 12;
                cellReporting.setYearlyTarget(yearlyTarget);
                cellReporting.setCellLeader(leader);
                for (FirstTimer timer : firstTimers) {
                    if (timer.getCellAddress().equalsIgnoreCase(leader.getCellAddress())
                            && Objects.equals(timer.getCellId(), leader.getId())){
                        cellReporting.setYearlyTotalFirstTimers(cellReporting.getYearlyTotalFirstTimers() + 1);
                    }

                }
                for (SecondTimer second : secondTimers) {
                    if (second.getCellName().equalsIgnoreCase(leader.getFirstName())
                            && Objects.equals(second.getCellId(), leader.getId())){
                        cellReporting.setYearlyTotalSecondTimers(cellReporting.getYearlyTotalSecondTimers() + 1);
                    }
                }
                //I AM HOPING!
                int yearly = 0;
                for (CellMonthlyReport fandS: monthlyFandS){
                    if(fandS.getCellId().compareTo(leader.getId()) == 0){
                        cellReporting.getMonthlyFandS().add(fandS);
                        cellReporting.setMonthlyTotalFirstAndSecondTimer(cellReporting.getMonthlyTotalFirstAndSecondTimer() + fandS.getTotalFirstAndSecondTimers());
                        cellReporting.setMonthlyTotalFirstTimers(cellReporting.getMonthlyTotalFirstTimers() + fandS.getFirstTimers());
                        cellReporting.setMonthlyTotalSecondTimers(cellReporting.getMonthlyTotalSecondTimers() + fandS.getSecondTimers());
                        yearly = yearly + fandS.getTotalFirstAndSecondTimers();
                    }
                }
                cellReporting.setYearlyTotalFirstAndSecondTimer(yearly);
                cellReports.add(cellReporting);
            }

            List<Reporting> monthlyReports = new ArrayList<>();
            int counter = 13;
            int monthSize = 0;
            do {
                Reporting report = new Reporting();
                report.setMonth(monthSize);
                //convert from int to month value
                for (ZonalLeader leader : zonalLeaders) {
                    ZoneReporting reporting = new ZoneReporting();
                    reporting.setZonalLeader(leader);
                    for (CellReporting cellReporting : cellReports) {
                        if (cellReporting.getCellLeader().getZoneId().compareTo(leader.getId()) == 0) {
                            reporting.getCellLeaders().add(cellReporting);
                            reporting.setNumberOfCells(reporting.getNumberOfCells() + 1);
                            reporting.setTotalYearlyFirstTimer(reporting.getTotalYearlyFirstTimer() + cellReporting.getYearlyTotalFirstTimers());
                            reporting.setTotalYearlySecondTimer(reporting.getTotalYearlySecondTimer() + cellReporting.getYearlyTotalSecondTimers());
                            reporting.setTotalMonthlyFirstTimer(reporting.getTotalMonthlyFirstTimer() + cellReporting.getMonthlyTotalFirstTimers());
                            reporting.setTotalMonthlySecondTimer(reporting.getTotalMonthlySecondTimer() + cellReporting.getMonthlyTotalSecondTimers());
                            reporting.setTotalYearlyFirstAndSecondTimer(reporting.getTotalYearlyFirstAndSecondTimer() + cellReporting.getYearlyTotalFirstAndSecondTimers());
                            reporting.setTotalMonthlyFirstAndSecondTimer(reporting.getTotalMonthlyFirstAndSecondTimer() + cellReporting.getMonthlyTotalFirstAndSecondTimer());
                        }
                    }
                    report.getZoneReports().add(reporting);
                }
                monthlyReports.add(report);
                monthSize = monthSize + 1;
            } while (monthSize < counter);

            return StandardResponse.sendHttpResponse(true, "Successful", monthlyReports);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> generateWeeklyFollowUpReport(String monthly){
        try {
            int month = Month.valueOf(monthly.toUpperCase()).getValue();
            List<ZonalLeaderReport> reportList = zonalLeaderReportRepo.findByReportMonth(month);
            List<LocalDate> saturdays = findSaturdays(LocalDate.now().getYear(), month);

            List<FollowUpReport> followUpReports = new ArrayList<>();

            for(LocalDate date : saturdays){
                FollowUpReport followUpReport = new FollowUpReport();

                for(ZonalLeaderReport report : reportList){
                    if(report.getReportDate().isEqual(date)){
                        followUpReport.setLeaderFirstName(report.getFirstName());
                        followUpReport.setLeaderLastName(report.getLastName());

                        followUpReport.setTotalMonthlyCalls(followUpReport.getTotalMonthlyCalls() + report.getCalls());
                        followUpReport.setTotalMonthlyCoupons(followUpReport.getTotalMonthlyCoupons() + report.getCoupons());

                        followUpReport.getReportList().add(report);
                    }
                }
                followUpReports.add(followUpReport);
            }

            return StandardResponse.sendHttpResponse(true, "Successful", followUpReports);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> generateMonthlyNewConvertsReport(int year){
        try {
            List<NewConverts> convertsList = newConvertRepo.findAll();
            List<Members> membersList = membersRepo.findAll();
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public List<LocalDate> findSundays(int year, int month) {
        List<LocalDate> sundays = new ArrayList<>();
        LocalDate date = LocalDate.of(year, month, 1);

        // Loop through the days of the month
        while (date.getMonthValue() == month) {
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                sundays.add(date);
            }
            date = date.plusDays(1);
        }

        return sundays;
    }

    public List<LocalDate> findSaturdays(int year, int month) {
        List<LocalDate> sundays = new ArrayList<>();
        LocalDate date = LocalDate.of(year, month, 1);

        // Loop through the days of the month
        while (date.getMonthValue() == month) {
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                sundays.add(date);
            }
            date = date.plusDays(1);
        }

        return sundays;
    }

    public ResponseEntity<StandardResponse> updateCellReport(Long zoneId, int coupons, int calls) {
        try {
            ZonalLeader leader = zonalLeaderRepo.findById(zoneId).get();

            LocalDate date = LocalDate.now();
            ZonalLeaderReport report = new ZonalLeaderReport();
            report.setCoupons(coupons);
            report.setCalls(calls);
            report.setZoneId(zoneId);
            report.setFirstName(leader.getFirstName());
            report.setLastName(leader.getLastName());
            report.setReportDate(date);
            report.setReportMonth(date.getMonthValue());

            return StandardResponse.sendHttpResponse(true, "Successfully updated", zonalLeaderReportRepo.save(report));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not update data");
        }
    }
}
