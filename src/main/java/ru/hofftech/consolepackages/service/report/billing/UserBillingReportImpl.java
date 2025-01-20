package ru.hofftech.consolepackages.service.report.billing;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.model.entity.BillingOrder;
import ru.hofftech.consolepackages.datastorage.model.entity.OperationType;
import ru.hofftech.consolepackages.datastorage.repository.BillingOrderRepository;
import ru.hofftech.consolepackages.service.report.PlaneStringReport;
import ru.hofftech.consolepackages.service.report.billing.model.BillOrderGroup;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Implementation of {@link UserBillingReportEngine} that generates a report of billing orders for a user by period.
 * <p>
 * This implementation uses a repository to retrieve billing orders for a user by period.
 * The report is generated by grouping the billing orders by date and operation type.
 * </p>
 */
@RequiredArgsConstructor
public class UserBillingReportImpl implements UserBillingReportEngine {

    private final BillingOrderRepository repository;

    /**
     * Generates a report of billing orders for a user within a specified period.
     *
     * @param userId   the ID of the user for whom to generate the report
     * @param fromDate the start date of the period
     * @param toDate   the end date of the period
     * @return a {@link PlaneStringReport} containing the billing orders grouped by date and operation type
     */
    @Override
    public PlaneStringReport generateByPeriod(String userId, Date fromDate, Date toDate) {
        List<BillingOrder> orders = repository.receiveForUserByPeriod(userId, fromDate, toDate);

        if (orders.isEmpty()) {
            return new PlaneStringReport();
        }

        var result = new PlaneStringReport();

        var groupedOrders = orders
                .stream()
                .collect(groupingBy(
                        bo -> new BillOrderGroup(bo.getDateWithoutTimeUsingFormat(), bo.getOperationType()),
                        toList()));

        for (var entry : groupedOrders.entrySet()) {
            result.addReportString(generateByOrder(entry.getKey(), entry.getValue()));
        }

        return result;
    }

    private String generateByOrder(BillOrderGroup orderGroup, List<BillingOrder> orders) {

        var summary = orders.stream()
                .collect(Collectors.summarizingDouble(bo -> bo.getAmount().doubleValue()))
                .getSum();

        var packageQtySum = orders.stream()
                .mapToInt(BillingOrder::getPackageQty)
                .sum();

        var truckIdCount = orders.stream()
                .map(BillingOrder::getTruckId)
                .distinct()
                .count();

        var formatter = new SimpleDateFormat("yyyy.MM.dd");

        return String.format(
                "%s; %s; %s машин; %s посылок; %s рублей",
                formatter.format(orderGroup.orderDate()),
                OperationType.returnLabel(orderGroup.operationType()),
                truckIdCount,
                packageQtySum,
                summary);
    }
}
